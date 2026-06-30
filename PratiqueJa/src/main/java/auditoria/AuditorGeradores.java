package auditoria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import matematica.ExercicioFactory;
import matematica.GeradorExercicio;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ParagrafoExercicio;

/**
 * Audita todos os dispatchers GeradorExercicio compilados em target/classes/matematica.
 *
 * Execução: mvn -q compile exec:java -Dexec.mainClass=auditoria.AuditorGeradores
 *
 * Relatório gravado em: revisao_geradores/resultado_geradores.tsv
 */
public class AuditorGeradores
{
	static final int EXECUCOES = 50;

	public static void main(String[] args) throws Exception
	{
		List<String> geradores = descobrirDispatcherClasses();
		System.err.printf("Auditando %d dispatchers (%d execuções cada)...%n%n",
			geradores.size(), EXECUCOES);

		Path saida = Path.of("revisao_geradores", "resultado_geradores.tsv");
		Files.createDirectories(saida.getParent());

		int totalFalhas = 0;
		int totalAlertas = 0;

		try(PrintWriter tsv = new PrintWriter(new FileWriter(saida.toFile())))
		{
			tsv.println("GERADOR\tC1_NUM_ALTS\tC2_UMA_CORRETA\tC3_SEM_DUP_ALT\tC4_ENUNCIADO\tC5_RESOLUCAO\tUNIQ\tEXEC_OK\tEXCECOES\tALERTAS");

			for(String classe : geradores)
			{
				ResultadoAuditoria r = auditarGerador(classe);
				tsv.println(r.toTsv());

				if(r.temFalha()) totalFalhas++;
				if(r.temAlerta()) totalAlertas++;

				String status = r.temFalha() ? "FALHA" : r.temAlerta() ? "ALERTA" : "OK";
				System.err.printf("  [%s] %s  uniq=%d/%d  excecoes=%d%n",
					status, classeAbreviada(classe), r.uniq, r.execOk, r.excecoes);
			}
		}

		System.err.printf("%n--- RESUMO ---%n");
		System.err.printf("Total geradores  : %d%n", geradores.size());
		System.err.printf("Com FALHA        : %d%n", totalFalhas);
		System.err.printf("Com ALERTA (rand): %d%n", totalAlertas);
		System.err.printf("Relatório        : %s%n", saida.toAbsolutePath());
	}

	// ── Descoberta de dispatchers ────────────────────────────────────────────

	static List<String> descobrirDispatcherClasses() throws IOException
	{
		Path base = Path.of("target", "classes");
		Path matematicaBase = base.resolve("matematica");

		if(!Files.isDirectory(matematicaBase))
			throw new IllegalStateException(
				"target/classes/matematica não encontrado. Rode 'mvn compile' primeiro.");

		List<String> classes = new ArrayList<>();

		Files.walk(matematicaBase, 3)
			.filter(Files::isRegularFile)
			.filter(p -> p.toString().endsWith(".class"))
			.filter(p -> !p.getFileName().toString().contains("$"))
			.filter(p -> matematicaBase.relativize(p).getNameCount() == 3)
			.forEach(p ->
			{
				String rel = base.relativize(p).toString().replace("\\", "/");
				String className = rel.replace("/", ".").replace(".class", "");
				try
				{
					Class<?> clazz = Class.forName(className);
					if(GeradorExercicio.class.isAssignableFrom(clazz)
						&& !clazz.isInterface()
						&& !Modifier.isAbstract(clazz.getModifiers()))
					{
						classes.add(className);
					}
				}
				catch(Exception | LinkageError ignored)
				{
					// classe não carregável: ignorar
				}
			});

		Collections.sort(classes);
		return classes;
	}

	// ── Auditoria de um gerador ──────────────────────────────────────────────

	static ResultadoAuditoria auditarGerador(String classe)
	{
		ResultadoAuditoria r = new ResultadoAuditoria(classe);

		for(int i = 0; i < EXECUCOES; i++)
		{
			try
			{
				Exercicio ex = ExercicioFactory.gerar(classe, i);
				r.execOk++;

				List<AlternativaExercicio> alts = ex.getAlternativas();

				// CHECK 1 — número de alternativas (2 ou 4)
				if(alts.size() != 2 && alts.size() != 4)
					r.falhasAltCount++;

				// CHECK 2 — exatamente 1 correta
				long numCorretas = alts.stream().filter(AlternativaExercicio::isCorreta).count();
				if(numCorretas != 1)
					r.falhasUnicaCorreta++;

				// CHECK 3 — sem textos duplicados entre alternativas
				Set<String> textos = new HashSet<>();
				boolean hasDup = false;
				for(AlternativaExercicio alt : alts)
					if(alt.getTexto() != null && !textos.add(alt.getTexto()))
						hasDup = true;
				if(hasDup)
					r.falhasDupAlts++;

				// CHECK 4 — enunciado não-vazio
				boolean temEnunciado = ex.getParagrafos().stream()
					.anyMatch(p -> p.getTexto() != null && !p.getTexto().isBlank());
				if(!temEnunciado)
					r.falhasEnunciado++;

				// CHECK 5 — resolução não-vazia
				boolean temResolucao = ex.getResolucaoParagrafos().stream()
					.anyMatch(p -> p.getTexto() != null && !p.getTexto().isBlank());
				if(!temResolucao)
					r.falhasResolucao++;

				// Assinatura = parágrafos-texto + alternativas-sorted.
				// Parágrafos capturam variação numérica do enunciado.
				// Alternativas (sorted) capturam variação de exercícios baseados em
				// imagem cujo texto instrucional é fixo (ex.: "Encontre o valor de x:").
				String sigTextos = ex.getParagrafos().stream()
					.map(ParagrafoExercicio::getTexto)
					.filter(t -> t != null && !t.isBlank())
					.collect(Collectors.joining("|"));
				String sigAlts = ex.getAlternativas().stream()
					.map(AlternativaExercicio::getTexto)
					.filter(t -> t != null && !t.isBlank())
					.sorted()
					.collect(Collectors.joining("|"));
				String assinatura = sigTextos + "||" + sigAlts;
				if(assinatura.isBlank() || assinatura.equals("||"))
					assinatura = "[sem_conteudo_" + i + "]";
				r.assinaturas.add(assinatura);
			}
			catch(Exception | LinkageError e)
			{
				r.excecoes++;
				if(r.msgExcecao == null)
					r.msgExcecao = e.getClass().getSimpleName() + ": " + e.getMessage();
			}
		}

		r.uniq = r.assinaturas.size();
		return r;
	}

	// ── Helpers ─────────────────────────────────────────────────────────────

	static String classeAbreviada(String fqcn)
	{
		int dot = fqcn.lastIndexOf('.');
		return dot >= 0 ? fqcn.substring(dot + 1) : fqcn;
	}

	// ── DTO de resultado ─────────────────────────────────────────────────────

	static class ResultadoAuditoria
	{
		final String classe;
		int falhasAltCount = 0;
		int falhasUnicaCorreta = 0;
		int falhasDupAlts = 0;
		int falhasEnunciado = 0;
		int falhasResolucao = 0;
		int execOk = 0;
		int excecoes = 0;
		int uniq = 0;
		String msgExcecao = null;
		Set<String> assinaturas = new HashSet<>();

		ResultadoAuditoria(String classe) { this.classe = classe; }

		boolean temFalha()
		{
			return falhasAltCount > 0 || falhasUnicaCorreta > 0
				|| falhasDupAlts > 0 || falhasEnunciado > 0
				|| falhasResolucao > 0 || excecoes == EXECUCOES;
		}

		boolean temAlerta()
		{
			if(execOk == 0) return false;
			double ratio = (double) uniq / execOk;
			return ratio < 0.5;
		}

		String toTsv()
		{
			if(execOk == 0)
				return String.join("\t", classe, "-", "-", "-", "-", "-",
					"0/" + EXECUCOES, "0", String.valueOf(excecoes),
					"TODAS_EXCECOES: " + msgExcecao);

			List<String> alertas = new ArrayList<>();

			double ratio = (double) uniq / execOk;
			if(ratio < 0.1)
				alertas.add("ERRO_RAND(" + uniq + "/" + execOk + ")");
			else if(ratio < 0.5)
				alertas.add("ALERTA_RAND(" + uniq + "/" + execOk + ")");

			if(msgExcecao != null)
				alertas.add("EXC: " + msgExcecao);

			return String.join("\t",
				classe,
				falhasAltCount > 0 ? "FALHA(" + falhasAltCount + ")" : "OK",
				falhasUnicaCorreta > 0 ? "FALHA(" + falhasUnicaCorreta + ")" : "OK",
				falhasDupAlts > 0 ? "FALHA(" + falhasDupAlts + ")" : "OK",
				falhasEnunciado > 0 ? "FALHA(" + falhasEnunciado + ")" : "OK",
				falhasResolucao > 0 ? "FALHA(" + falhasResolucao + ")" : "OK",
				uniq + "/" + execOk,
				String.valueOf(execOk),
				String.valueOf(excecoes),
				alertas.isEmpty() ? "-" : String.join("; ", alertas));
		}
	}
}
