package util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Executa um processo externo (xelatex, pdflatex, pdftocairo, pdfunite) <b>com prazo</b>.
 *
 * <p><b>Por que existe.</b> {@code Process.waitFor()} sem argumento espera para sempre. Um
 * {@code pdflatex} pendurado — arquivo corrompido, disco cheio, um {@code \read} que ficou
 * aguardando entrada apesar do {@code -interaction=nonstopmode} — travava a thread para sempre, e
 * as consequências não eram locais:</p>
 *
 * <ul>
 *   <li>na geração de posts e de avaliações, a thread presa nunca devolvia o {@code AtomicBoolean}
 *       {@code processando} de {@code FilaGeracaoPostService}/{@code FilaGeracaoAvaliacaoService};
 *       a fila inteira parava <b>calada</b> e todo pedido novo ficava em AGUARDANDO para sempre;</li>
 *   <li>nas listas geradas sob demanda, a thread presa era a da requisição HTTP.</li>
 * </ul>
 *
 * <p>É o mesmo remédio dos tempos limite de SMTP da fila de e-mail: sem prazo, a falha não aparece
 * como falha — aparece como um serviço que simplesmente parou de responder.</p>
 */
public final class ProcessoExterno
{
	/** Prazo de uma execução. Generoso de propósito: serve para pegar o processo pendurado. */
	public static final long TIMEOUT_PADRAO_MINUTOS = 5;

	/** Depois do kill, quanto se espera o SO recolher o processo antes de desistir. */
	private static final long ESPERA_APOS_KILL_SEGUNDOS = 10;

	private ProcessoExterno()
	{
	}

	public static int executar(ProcessBuilder pb, String comando) throws IOException, InterruptedException
	{
		return executar(pb, comando, TIMEOUT_PADRAO_MINUTOS);
	}

	/**
	 * Roda o processo e devolve o código de saída. Estourado o prazo, mata o processo e lança
	 * {@link IOException} — quem chama trata como qualquer outra falha de geração.
	 *
	 * <p>O código de saída <b>não</b> é interpretado aqui de propósito: o pdflatex sai com 1 em
	 * erros recuperáveis e ainda assim produz o PDF, então quem decide é quem conhece o comando.</p>
	 */
	public static int executar(ProcessBuilder pb, String comando, long timeoutMinutos)
		throws IOException, InterruptedException
	{
		Process processo = pb.start();

		try
		{
			if(processo.waitFor(timeoutMinutos, TimeUnit.MINUTES))
				return processo.exitValue();
		}
		catch(InterruptedException e)
		{
			// Não deixa o processo órfão ao desmontar a aplicação.
			matar(processo);
			throw e;
		}

		matar(processo);
		throw new IOException(comando + " não terminou em " + timeoutMinutos
			+ " min e foi encerrado à força.");
	}

	private static void matar(Process processo)
	{
		processo.destroyForcibly();
		try
		{
			processo.waitFor(ESPERA_APOS_KILL_SEGUNDOS, TimeUnit.SECONDS);
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}
}
