package arquitetura;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * O defeito que estes testes impedem de voltar: <b>esperar por algo de fora sem prazo</b>.
 *
 * <p>Uma espera sem tempo limite não falha — ela para. E o que para junto costuma ser muito maior
 * que a operação em si:</p>
 *
 * <ul>
 *   <li>{@code Process.waitFor()} sem argumento: um {@code pdflatex} pendurado nunca devolvia o
 *       {@code AtomicBoolean processando} das filas de geração, e a fila inteira parava calada —
 *       todo pedido novo ficava em AGUARDANDO para sempre, sem uma linha no log;</li>
 *   <li>{@code HttpClient}/{@code HttpRequest} sem prazo: o padrão do {@code java.net.http} é
 *       esperar para sempre, nos dois lados. No webhook da Hotmart isso ainda travava, de tabela,
 *       todos os webhooks seguintes (o método do token é {@code synchronized});</li>
 *   <li>a mesma história dos tempos limite de SMTP que faltavam na fila de e-mail.</li>
 * </ul>
 *
 * <p>A varredura é no código-fonte porque é lá que a forma errada é escrita — e é lá que ela seria
 * escrita de novo.</p>
 */
@DisplayName("Nada espera por algo de fora sem prazo")
class EsperaSemPrazoTest
{
	private static final Path FONTES = Path.of("src", "main", "java");

	@Test
	@DisplayName("nenhum Process.waitFor() sem tempo limite")
	void processosTemPrazo()
	{
		List<String> achados = procurar(".waitFor()", arquivo ->
			!arquivo.endsWith("ProcessoExterno.java"));

		if(!achados.isEmpty())
			fail("waitFor() sem prazo espera para sempre: um processo pendurado trava a fila de"
				+ " geração inteira, em silêncio. Use util.ProcessoExterno.executar(pb, comando):\n  "
				+ String.join("\n  ", achados));
	}

	@Test
	@DisplayName("nenhum HttpClient.newHttpClient() — o padrão é sem tempo limite de conexão")
	void clientesHttpTemPrazoDeConexao()
	{
		List<String> achados = procurar("HttpClient.newHttpClient()", arquivo -> true);

		if(!achados.isEmpty())
			fail("HttpClient.newHttpClient() não tem tempo limite de conexão. Use"
				+ " HttpClient.newBuilder().connectTimeout(...).build():\n  "
				+ String.join("\n  ", achados));
	}

	@Test
	@DisplayName("toda HttpRequest declara .timeout(...)")
	void requisicoesHttpTemPrazoDeResposta()
	{
		List<String> semPrazo = new ArrayList<>();
		int examinados = 0;

		for(Path fonte : fontes())
		{
			String texto = ler(fonte);

			int inicio = 0;
			while((inicio = texto.indexOf("HttpRequest.newBuilder()", inicio)) >= 0)
			{
				examinados++;

				// O builder vai até o .build(); o .timeout() tem que estar nesse trecho.
				int fim = texto.indexOf(".build()", inicio);
				String trecho = fim > inicio ? texto.substring(inicio, fim) : texto.substring(inicio);

				if(!trecho.contains(".timeout("))
					semPrazo.add(FONTES.relativize(fonte) + " (linha " + linhaDe(texto, inicio) + ")");

				inicio += "HttpRequest.newBuilder()".length();
			}
		}

		assertTrue(examinados > 0 || semPrazo.isEmpty(),
			"Nenhuma HttpRequest encontrada — o teste deixou de examinar o que devia.");

		if(!semPrazo.isEmpty())
			fail("HttpRequest sem .timeout(...) espera a resposta para sempre:\n  "
				+ String.join("\n  ", semPrazo));
	}

	private List<String> procurar(String trecho, java.util.function.Predicate<String> aceitarArquivo)
	{
		List<String> achados = new ArrayList<>();

		for(Path fonte : fontes())
		{
			if(!aceitarArquivo.test(fonte.getFileName().toString()))
				continue;

			String[] linhas = ler(fonte).split("\n");
			for(int i = 0; i < linhas.length; i++)
			{
				String linha = linhas[i];
				// Ignora comentário e javadoc: estes arquivos explicam justamente a forma errada.
				String semEspaco = linha.stripLeading();
				if(semEspaco.startsWith("//") || semEspaco.startsWith("*") || semEspaco.startsWith("/*"))
					continue;

				if(linha.contains(trecho))
					achados.add(FONTES.relativize(fonte) + " (linha " + (i + 1) + "): " + linha.trim());
			}
		}

		return achados;
	}

	private int linhaDe(String texto, int posicao)
	{
		return (int) texto.substring(0, posicao).chars().filter(c -> c == '\n').count() + 1;
	}

	static List<Path> fontes()
	{
		try(Stream<Path> arquivos = Files.walk(FONTES))
		{
			return arquivos.filter(p -> p.toString().endsWith(".java")).toList();
		}
		catch(IOException e)
		{
			throw new IllegalStateException("Falha ao varrer " + FONTES, e);
		}
	}

	static String ler(Path fonte)
	{
		try
		{
			return Files.readString(fonte, StandardCharsets.UTF_8);
		}
		catch(IOException e)
		{
			throw new IllegalStateException("Falha ao ler " + fonte, e);
		}
	}
}
