package service.seguranca;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongSupplier;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Freio contra tentativa de adivinhar senha no login.
 *
 * <p>Não existia trava nenhuma: o único custo de um chute errado era o do
 * BCrypt, e o nginx só limita vazão bruta (20 req/s por IP), o que ainda dá
 * dezenas de milhares de tentativas por hora. Este componente conta as falhas
 * numa janela deslizante e reage a elas — em memória, sem tabela nem
 * dependência nova, porque é um só nó de WildFly e o que interessa é a janela
 * recente, não o histórico.</p>
 *
 * <p><b>Duas chaves, dois remédios diferentes, e a diferença é o ponto:</b></p>
 * <ul>
 * <li><b>Por IP</b> — depois de {@link #LIVRE_IP} falhas na janela, o IP entra
 * em espera e as tentativas seguintes são <b>recusadas antes do BCrypt</b>. O
 * IP é o recurso do atacante; recusar cedo nega a ele o próprio ato de chutar,
 * e não custa CPU nossa. É seguro: um usuário legítimo não erra a senha cinco
 * vezes a partir do próprio IP a ponto de se incomodar com uma espera de
 * segundos, e ninguém consegue prender o acesso de <i>outra</i> pessoa por
 * aqui.</li>
 * <li><b>Por conta</b> — depois de {@link #LIVRE_CONTA} falhas, cada chute
 * errado ganha um <b>atraso curto e limitado</b> ({@link #ATRASO_CONTA_MAX_MS}).
 * De propósito <b>não</b> é recusa: recusar por conta deixaria qualquer um
 * trancar o login da vítima só martelando o e-mail dela de vários IPs — o
 * lockout que se quer evitar. Como o atraso só cai no caminho da falha, quem
 * digita a senha certa entra na hora; só o atacante, que erra, paga.</li>
 * </ul>
 *
 * <p>É atrito, não muralha: parar a adivinhação de <i>uma</i> conta espalhada
 * por IPs ilimitados sem trancar a conta exige CAPTCHA ou segundo fator, que
 * não temos. O que dá para fazer sem penalizar o dono — encarecer o IP e
 * atrasar a conta — está aqui; o resto fica como evolução.</p>
 *
 * <p>A espera cresce a cada falha (15s, 30s, 1min, 2min... até o teto) e some
 * sozinha quando a janela passa sem tentativa nova: nada de bloqueio
 * permanente. Tentativa recusada por espera não conta como falha nova, então a
 * espera não se realimenta indefinidamente.</p>
 */
@ApplicationScoped
public class ThrottleLogin
{
	/** Janela deslizante em que as falhas são contadas. */
	static final long JANELA_MS = 15 * 60_000L;

	/** Falhas sem punição por IP e por conta, dentro da janela. */
	static final int LIVRE_IP = 5;
	static final int LIVRE_CONTA = 5;

	/** Primeiro degrau da espera por IP; dobra a cada falha excedente. */
	static final long PASSO_COOLDOWN_MS = 15_000L;

	/** Teto da espera por IP — a partir daqui não cresce mais. */
	static final long COOLDOWN_MAX_MS = 15 * 60_000L;

	/** Teto do atraso por conta. Curto: é atrito, e prende uma thread nossa. */
	static final long ATRASO_CONTA_MAX_MS = 2_000L;

	private static final long PASSO_ATRASO_MS = 250L;

	/** Guarda de deslocamento: 1L << 20 já estoura qualquer teto. */
	private static final int EXPOENTE_MAX = 20;

	// Faxina preguiçosa do mapa: sem isto, um ataque com e-mails aleatórios
	// criaria uma entrada por e-mail e o mapa cresceria sem limite. Não há
	// thread de fundo — a limpeza pega carona nas próprias falhas.
	private static final int OPS_ENTRE_LIMPEZAS = 500;
	private static final int MAX_CHAVES = 200_000;

	private final LongSupplier agora;
	private final Map<String, Deque<Long>> falhas = new ConcurrentHashMap<>();
	private final AtomicInteger opsDesdeLimpeza = new AtomicInteger();

	/** Construtor do CDI: relógio do sistema. */
	public ThrottleLogin()
	{
		this(System::currentTimeMillis);
	}

	/** Construtor de teste: relógio controlável, sem esperar o tempo real passar. */
	ThrottleLogin(LongSupplier agora)
	{
		this.agora = agora;
	}

	/**
	 * @return {@code true} se o IP estourou o limite e ainda está no período de
	 *         espera. Chamado <b>antes</b> de olhar a senha: quando verdadeiro, a
	 *         tentativa é recusada sem rodar o BCrypt.
	 */
	public boolean bloqueadoPorIp(String ip)
	{
		return esperaRestanteMs(chaveIp(ip), LIVRE_IP) > 0;
	}

	/**
	 * Registra uma falha de login sob as duas chaves e devolve quanto atrasar
	 * <b>esta</b> resposta, em milissegundos (só a parcela da conta; o IP se
	 * defende recusando, não atrasando).
	 */
	public long registrarFalha(String conta, String ip)
	{
		long t = agora.getAsLong();

		registrar(chaveIp(ip), t);
		registrar(chaveConta(conta), t);

		talvezLimpar(t);

		return atraso(chaveConta(conta), LIVRE_CONTA);
	}

	/** Login aceito: zera o placar das duas chaves. */
	public void registrarSucesso(String conta, String ip)
	{
		falhas.remove(chaveIp(ip));
		falhas.remove(chaveConta(conta));
	}

	// -----------------------------------------------------------------
	// Internos
	// -----------------------------------------------------------------

	private void registrar(String chave, long t)
	{
		Deque<Long> dq = falhas.computeIfAbsent(chave, k -> new ArrayDeque<>());

		synchronized(dq)
		{
			dq.addLast(t);
			podar(dq, t);
		}
	}

	/** Milissegundos que faltam para o fim da espera desta chave (0 se não há). */
	private long esperaRestanteMs(String chave, int livre)
	{
		Deque<Long> dq = falhas.get(chave);

		if(dq == null)
			return 0;

		long t = agora.getAsLong();

		synchronized(dq)
		{
			podar(dq, t);

			int excedente = dq.size() - livre;
			if(excedente <= 0)
				return 0;

			long cooldown = Math.min(COOLDOWN_MAX_MS, PASSO_COOLDOWN_MS << Math.min(EXPOENTE_MAX, excedente - 1));

			return Math.max(0, (dq.peekLast() + cooldown) - t);
		}
	}

	/** Atraso a aplicar nesta falha de conta (0 até o limite livre). */
	private long atraso(String chave, int livre)
	{
		Deque<Long> dq = falhas.get(chave);

		if(dq == null)
			return 0;

		long t = agora.getAsLong();

		synchronized(dq)
		{
			podar(dq, t);

			int excedente = dq.size() - livre;
			if(excedente <= 0)
				return 0;

			return Math.min(ATRASO_CONTA_MAX_MS, PASSO_ATRASO_MS << Math.min(EXPOENTE_MAX, excedente - 1));
		}
	}

	private void podar(Deque<Long> dq, long t)
	{
		long limite = t - JANELA_MS;

		while(!dq.isEmpty() && dq.peekFirst() < limite)
			dq.removeFirst();
	}

	/**
	 * Passa o mapa varrendo entradas vencidas de tempos em tempos (ou já se
	 * estiver grande demais). Remove as filas que a poda esvaziou, devolvendo a
	 * memória de IPs e e-mails que não aparecem há mais de uma janela.
	 */
	private void talvezLimpar(long t)
	{
		if(opsDesdeLimpeza.incrementAndGet() < OPS_ENTRE_LIMPEZAS && falhas.size() < MAX_CHAVES)
			return;

		opsDesdeLimpeza.set(0);

		for(Iterator<Map.Entry<String, Deque<Long>>> it = falhas.entrySet().iterator(); it.hasNext();)
		{
			Deque<Long> dq = it.next().getValue();

			synchronized(dq)
			{
				podar(dq, t);

				if(dq.isEmpty())
					it.remove();
			}
		}
	}

	private static String chaveIp(String ip)
	{
		return "ip:" + (ip == null ? "" : ip.trim());
	}

	private static String chaveConta(String conta)
	{
		return "conta:" + (conta == null ? "" : conta.trim().toLowerCase());
	}
}
