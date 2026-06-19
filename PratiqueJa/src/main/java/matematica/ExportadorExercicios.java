package matematica;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ParagrafoExercicio;

/**
 * Utilitário (main) para gerar exercícios de um assunto e exportá-los em CSV.
 *
 * <p>Para cada um dos 3 níveis do assunto, instancia o dispatcher
 * ({@code <pacote>.<Prefixo>NivelN}), gera {@code QTD_POR_NIVEL} exercícios e grava
 * uma linha CSV por exercício (nivel, enunciado, resposta, resolução) no arquivo
 * {@code <assunto>.txt} dentro de {@link #PASTA_SAIDA}.
 *
 * <p>O tier (basico/intermediario/avancado) e o prefixo do dispatcher são
 * descobertos automaticamente varrendo as classes compiladas — basta informar o
 * nome do assunto (pasta do pacote).
 *
 * <h3>Como rodar</h3>
 * <ul>
 *   <li>Sem argumentos: usa os padrões abaixo ({@code equacoes}, 20 por nível).</li>
 *   <li>{@code main <assunto> [qtdPorNivel]} — ex.: {@code matrizes 30}.</li>
 *   <li>Também aceita o pacote completo como 1º argumento
 *       (ex.: {@code matematica.avancado.matrizes}).</li>
 * </ul>
 */
public class ExportadorExercicios
{
	private static final String PASTA_SAIDA = "C:\\Users\\maximovrm\\git\\PratiqueJa\\PratiqueJa\\exercicios";

	/** Delimitador CSV. Troque para ';' se for abrir no Excel pt-BR. */
	private static final char DELIMITADOR = ',';

	// Padrões usados quando o main roda sem argumentos (edite à vontade):
	private static final String ASSUNTO_PADRAO = "adicaonatural";
	private static final int QTD_POR_NIVEL_PADRAO = 30;

	private static final String[] TIERS = { "basico", "intermediario", "avancado" };

	public static void main(String[] args) throws Exception
	{
		String alvo = args.length > 0 ? args[0] : ASSUNTO_PADRAO;
		int qtdPorNivel = args.length > 1 ? Integer.parseInt(args[1]) : QTD_POR_NIVEL_PADRAO;

		if("todos".equalsIgnoreCase(alvo) || "all".equalsIgnoreCase(alvo))
		{
			List<String> assuntos = listarTodosAssuntos();
			System.out.println("Exportando " + assuntos.size() + " assuntos (" + qtdPorNivel + " exercícios/nível)...\n");
			int totalGeral = 0;
			for(String a : assuntos)
				totalGeral += exportar(a, qtdPorNivel);
			System.out.println("\n=== Concluído: " + totalGeral + " exercícios gerados no total. ===");
			return;
		}

		exportar(alvo, qtdPorNivel);
	}

	private static int exportar(String alvo, int qtdPorNivel) throws Exception
	{
		String pacote = alvo.contains(".") ? alvo : resolverPacote(alvo);
		if(pacote == null)
		{
			System.err.println("Assunto não encontrado: '" + alvo + "'. "
				+ "Informe o nome da pasta do assunto ou o pacote completo.");
			return 0;
		}

		String assunto = pacote.substring(pacote.lastIndexOf('.') + 1);
		String prefixo = descobrirPrefixo(pacote);
		if(prefixo == null)
		{
			System.err.println("Não foi possível descobrir o dispatcher (classe *Nivel1) em: " + pacote);
			return 0;
		}

		Path pasta = Paths.get(PASTA_SAIDA);
		Files.createDirectories(pasta);
		Path arquivo = pasta.resolve(assunto + ".txt");

		int total = 0;
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8), true))
		{
			writer.println(linha("nivel", "enunciado", "resposta", "resolucao"));

			for(int nivel = 1; nivel <= 3; nivel++)
			{
				String nomeClasse = pacote + "." + prefixo + "Nivel" + nivel;
				Class<?> classe;
				try
				{
					classe = Class.forName(nomeClasse);
				}
				catch(ClassNotFoundException naoExiste)
				{
					System.out.println("Dispatcher inexistente (pulando): " + nomeClasse);
					continue;
				}

				int gerados = 0;
				for(int i = 0; i < qtdPorNivel; i++)
				{
					try
					{
						// instância nova por exercício: o Exercicio acumula estado.
						GeradorExercicio gerador = (GeradorExercicio) classe
							.getDeclaredConstructor().newInstance();
						Exercicio exercicio = gerador.gerar();
						writer.println(linhaExercicio(nivel, exercicio));
						gerados++;
					}
					catch(Throwable falha)
					{
						System.out.println("Falha ao gerar exercício de " + nomeClasse + ": " + falha);
					}
				}
				System.out.println(nomeClasse + " → " + gerados + " exercícios");
				total += gerados;
			}
		}

		System.out.println("OK: " + total + " exercícios de '" + assunto + "' → " + arquivo);
		return total;
	}

	/** Lista todos os assuntos disponíveis varrendo os três tiers. */
	private static List<String> listarTodosAssuntos()
	{
		List<String> assuntos = new java.util.ArrayList<>();
		for(String tier : TIERS)
		{
			String pacoteTier = "matematica." + tier;
			String relativo = pacoteTier.replace('.', File.separatorChar);

			for(Path base : new Path[]{ Paths.get("target", "classes", relativo),
			                            Paths.get("src", "main", "java", relativo) })
			{
				if(!Files.isDirectory(base))
					continue;
				try(Stream<Path> dirs = Files.list(base))
				{
					dirs.filter(Files::isDirectory)
					    .map(p -> p.getFileName().toString())
					    .filter(nome -> !assuntos.contains(nome))
					    .sorted()
					    .forEach(assuntos::add);	
				}
				catch(IOException ignored) {}
				break;
			}
		}
		assuntos.sort(String::compareTo);
		return assuntos;
	}

	/** Procura o pacote do assunto nos três tiers (via classes compiladas ou fonte). */
	private static String resolverPacote(String assunto)
	{
		for(String tier : TIERS)
		{
			String pacote = "matematica." + tier + "." + assunto;
			if(diretorioDoPacote(pacote) != null)
				return pacote;
		}
		return null;
	}

	/** Descobre o prefixo do dispatcher procurando uma classe/arquivo "*Nivel1". */
	private static String descobrirPrefixo(String pacote)
	{
		Path dir = diretorioDoPacote(pacote);
		if(dir == null)
			return null;

		try(Stream<Path> arquivos = Files.list(dir))
		{
			return arquivos
				.map(p -> p.getFileName().toString())
				.map(nome -> sufixo(nome, "Nivel1.class"))
				.filter(s -> s != null)
				.findFirst()
				.orElseGet(() -> prefixoNaFonte(pacote));
		}
		catch(IOException e)
		{
			return prefixoNaFonte(pacote);
		}
	}

	private static String prefixoNaFonte(String pacote)
	{
		Path dir = Paths.get("src", "main", "java", pacote.replace('.', File.separatorChar));
		if(!Files.isDirectory(dir))
			return null;
		try(Stream<Path> arquivos = Files.list(dir))
		{
			return arquivos
				.map(p -> p.getFileName().toString())
				.map(nome -> sufixo(nome, "Nivel1.java"))
				.filter(s -> s != null)
				.findFirst()
				.orElse(null);
		}
		catch(IOException e)
		{
			return null;
		}
	}

	/** Diretório do pacote em target/classes (preferido) ou src/main/java. */
	private static Path diretorioDoPacote(String pacote)
	{
		String relativo = pacote.replace('.', File.separatorChar);
		Path compilado = Paths.get("target", "classes", relativo);
		if(Files.isDirectory(compilado))
			return compilado;
		Path fonte = Paths.get("src", "main", "java", relativo);
		return Files.isDirectory(fonte) ? fonte : null;
	}

	/** Se {@code nome} termina com {@code fim}, devolve o que vem antes; senão null. */
	private static String sufixo(String nome, String fim)
	{
		return nome.endsWith(fim) ? nome.substring(0, nome.length() - fim.length()) : null;
	}

	private static String linhaExercicio(int nivel, Exercicio exercicio)
	{
		return linha("" + nivel, enunciado(exercicio), resposta(exercicio), resolucao(exercicio));
	}

	private static String enunciado(Exercicio exercicio)
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoExercicio paragrafo : exercicio.getParagrafos())
		{
			String trecho = paragrafo.isTipoImagem()
				? "[imagem]"
				: (paragrafo.getTexto() == null ? "" : paragrafo.getTexto());
			if(!trecho.isEmpty())
			{
				if(sb.length() > 0)
					sb.append(' ');
				sb.append(trecho);
			}
		}
		return sb.toString();
	}

	private static String resposta(Exercicio exercicio)
	{
		AlternativaExercicio correta = exercicio.correta();
		return correta == null ? "" : correta.getTexto();
	}

	private static String resolucao(Exercicio exercicio)
	{
		return exercicio.getResolucao() == null ? "" : exercicio.getResolucao();
	}

	/** Monta uma linha CSV (RFC 4180) com os campos escapados. */
	private static String linha(String... campos)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < campos.length; i++)
		{
			if(i > 0)
				sb.append(DELIMITADOR);
			sb.append(campo(campos[i]));
		}
		return sb.toString();
	}

	/** Escapa um campo CSV: aspas ao redor, aspas internas duplicadas, sem quebras de linha. */
	private static String campo(String valor)
	{
		String limpo = (valor == null ? "" : valor).replace("\r", " ").replace("\n", " ").trim();
		return "\"" + limpo.replace("\"", "\"\"") + "\"";
	}
}
