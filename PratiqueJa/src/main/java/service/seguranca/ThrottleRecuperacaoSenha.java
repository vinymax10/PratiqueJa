package service.seguranca;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongSupplier;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Freio contra a bomba de e-mail no "esqueci a senha".
 *
 * <p>O {@code AutenticacaoBean.recuperarSenha} manda um e-mail toda vez que é
 * chamado com um e-mail existente, sem limite. Como o formulário é público,
 * dava para disparar quantos e-mails de redefinição se quisesse contra a caixa
 * de uma vítima — cada envio é um incômodo real e some do controle do dono.</p>
 *
 * <p>A trava é por <b>e-mail de destino</b>, que é justamente o alvo do abuso:
 * cada endereço recebe no máximo um pedido a cada {@link #COOLDOWN_MS}. Chavear
 * por IP não protegeria a caixa (o atacante troca de IP); chavear pelo destino
 * garante que a vítima não é inundada, venha o pedido de onde vier.</p>
 *
 * <p>Quem decide <b>enviar</b> é este componente; quem decide <b>o que a tela
 * responde</b> continua sendo o bean, e a resposta é sempre a mesma, exista a
 * conta ou não. Ou seja: segurar o e-mail não abre uma brecha de enumeração —
 * de fora, pedir de novo cedo demais é indistinguível de pedir para um e-mail
 * que não existe.</p>
 */
@ApplicationScoped
public class ThrottleRecuperacaoSenha
{
	/** Intervalo mínimo entre dois e-mails para o mesmo destino. */
	static final long COOLDOWN_MS = 2 * 60_000L;

	private static final int OPS_ENTRE_LIMPEZAS = 500;
	private static final int MAX_CHAVES = 200_000;

	private final LongSupplier agora;
	private final Map<String, Long> ultimoEnvio = new ConcurrentHashMap<>();
	private final AtomicInteger opsDesdeLimpeza = new AtomicInteger();

	/** Construtor do CDI: relógio do sistema. */
	public ThrottleRecuperacaoSenha()
	{
		this(System::currentTimeMillis);
	}

	/** Construtor de teste: relógio controlável. */
	ThrottleRecuperacaoSenha(LongSupplier agora)
	{
		this.agora = agora;
	}

	/**
	 * Diz se pode enviar agora para este e-mail e, em caso afirmativo, já marca
	 * o envio (leitura e marcação num átomo só, para dois pedidos simultâneos
	 * não furarem a trava).
	 *
	 * @return {@code true} no máximo uma vez a cada {@link #COOLDOWN_MS} por
	 *         destino
	 */
	public boolean podeEnviar(String email)
	{
		long t = agora.getAsLong();
		String chave = normalizar(email);

		talvezLimpar(t);

		// compute é atômico por chave. O flag diz se ESTA chamada foi a que
		// gravou — não dá para inferir isso comparando o valor final com `t`,
		// porque dois pedidos no mesmo milissegundo teriam o mesmo `t` e um
		// deles passaria indevidamente.
		boolean[] enviou = {false};

		ultimoEnvio.compute(chave, (k, anterior) ->
		{
			if(anterior == null || (t - anterior) >= COOLDOWN_MS)
			{
				enviou[0] = true;
				return t;
			}

			return anterior;
		});

		return enviou[0];
	}

	// -----------------------------------------------------------------

	private void talvezLimpar(long t)
	{
		if(opsDesdeLimpeza.incrementAndGet() < OPS_ENTRE_LIMPEZAS && ultimoEnvio.size() < MAX_CHAVES)
			return;

		opsDesdeLimpeza.set(0);

		for(Iterator<Map.Entry<String, Long>> it = ultimoEnvio.entrySet().iterator(); it.hasNext();)
		{
			if((t - it.next().getValue()) >= COOLDOWN_MS)
				it.remove();
		}
	}

	private static String normalizar(String email)
	{
		return email == null ? "" : email.trim().toLowerCase();
	}
}
