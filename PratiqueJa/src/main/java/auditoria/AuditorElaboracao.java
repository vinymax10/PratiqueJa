package auditoria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import matematica.ExercicioFactory;
import modelo.exercicio.Exercicio;

/**
 * Audita a ELABORAÇÃO das resoluções (não a estrutura — isso é o {@link AuditorGeradores}).
 * Gera exercícios de verdade via {@link ExercicioFactory} e inspeta o texto real de
 * {@code Exercicio.getResolucaoLinhas()} em busca de antipadrões descritos em
 * {@code formato_resolucao.md} (raiz do projeto): passos colados numa chamada só, parágrafo
 * iniciando com "=", "logo" colando duas equações, fração crua sem \dfrac, \mathbf ausente no
 * último passo, unicode grego/raiz, unidade colada sem \,\text{}.
 *
 * Auditoria estática (grep no código-fonte) super/subestima esses problemas porque a maior
 * parte da lógica de resolução mora em helpers compartilhados (prefixos Resolucao, Agrupador,
 * Problema) — só o texto realmente gerado é confiável.
 *
 * Execução: mvn -q compile exec:java -Dexec.mainClass=auditoria.AuditorElaboracao
 *
 * Relatório gravado em: revisao_geradores/RELATORIO_ELABORACAO.tsv (+ .md ranqueado)
 */
public class AuditorElaboracao
{
	static final int EXECUCOES = 15;

	static final Pattern FRACAO_CRUA = Pattern.compile("(?<!d)\\b\\d+/\\d+\\b");
	static final Pattern UNICODE_GREGO_RAIZ = Pattern.compile("[πθαβγδεσμφωΔΛ√]");
	static final Pattern UNIDADE_COLADA = Pattern.compile("\\d(km|kg|cm|m2|m3|m3|cm2|cm3)\\b");

	public static void main(String[] args) throws Exception
	{
		List<String> geradores = AuditorGeradores.descobrirDispatcherClasses();
		System.err.printf("Auditando elaboração de %d dispatchers (%d execuções cada)...%n%n",
			geradores.size(), EXECUCOES);

		Path saidaTsv = Path.of("revisao_geradores", "RELATORIO_ELABORACAO.tsv");
		Path saidaMd = Path.of("revisao_geradores", "RELATORIO_ELABORACAO.md");
		Files.createDirectories(saidaTsv.getParent());

		List<ResultadoElaboracao> resultados = new ArrayList<>();

		try(PrintWriter tsv = new PrintWriter(new FileWriter(saidaTsv.toFile())))
		{
			tsv.println("GERADOR\tPASSOS_MIN\tPASSOS_MED\tPASSOS_MAX\tPASSOS_COLADOS\tINICIA_IGUAL\t"
				+ "LOGO_SUSPEITO\tFRACAO_CRUA\tMATHBF_AUSENTE\tUNICODE_GREGO\tUNIDADE_COLADA\tEXCECOES");

			for(String classe : geradores)
			{
				ResultadoElaboracao r = auditarGerador(classe);
				resultados.add(r);
				tsv.println(r.toTsv());
			}
		}

		resultados.sort(Comparator.comparingInt(ResultadoElaboracao::score).reversed());

		try(PrintWriter md = new PrintWriter(new FileWriter(saidaMd.toFile())))
		{
			md.println("# Relatório de elaboração das resoluções (auditoria dinâmica)");
			md.println();
			md.println("Gerado por `AuditorElaboracao`. Ordenado do pior para o melhor (score = soma");
			md.println("de antipadrões detectados nas " + EXECUCOES + " execuções de amostra).");
			md.println();
			md.println("| Gerador | Score | Passos (min/med/max) | Colados | =inicial | logo? | Fração crua | \\mathbf ausente | Grego/raiz | Unidade colada |");
			md.println("|---|---|---|---|---|---|---|---|---|---|");
			for(ResultadoElaboracao r : resultados)
			{
				if(r.score() == 0) continue;
				md.printf("| %s | %d | %d/%.1f/%d | %d | %d | %d | %d | %d | %d | %d |%n",
					classeAbreviada(r.classe), r.score(), r.passosMin, r.passosMedia(), r.passosMax,
					r.passosColados, r.iniciaIgual, r.logoSuspeito, r.fracaoCrua, r.mathbfAusente,
					r.unicodeGrego, r.unidadeColada);
			}
		}

		System.err.printf("%n--- RESUMO ---%n");
		System.err.printf("Total geradores        : %d%n", resultados.size());
		System.err.printf("Com algum antipadrão    : %d%n",
			resultados.stream().filter(r -> r.score() > 0).count());
		System.err.printf("TSV                     : %s%n", saidaTsv.toAbsolutePath());
		System.err.printf("Markdown (ranqueado)    : %s%n", saidaMd.toAbsolutePath());
	}

	// ── Auditoria de um gerador ──────────────────────────────────────────────

	static ResultadoElaboracao auditarGerador(String classe)
	{
		ResultadoElaboracao r = new ResultadoElaboracao(classe);
		List<Integer> passosPorExecucao = new ArrayList<>();

		for(int i = 0; i < EXECUCOES; i++)
		{
			try
			{
				Exercicio ex = ExercicioFactory.gerar(classe, i);
				List<String> linhas = ex.getResolucaoLinhas();
				passosPorExecucao.add(linhas.size());

				boolean temMathMultiLinha = false;
				boolean temBoldEmAlgumLugar = false;

				for(int j = 0; j < linhas.size(); j++)
				{
					String linha = linhas.get(j);

					if(linha.contains("; \\(") || linha.contains("; $"))
						r.passosColados++;

					String semEspacosIniciais = linha.replaceFirst("^\\\\\\(\\s*", "").trim();
					if(semEspacosIniciais.startsWith("="))
						r.iniciaIgual++;

					if(linha.toLowerCase().contains("logo") && contarOcorrencias(linha, "=") >= 2)
						r.logoSuspeito++;

					if(FRACAO_CRUA.matcher(linha).find())
						r.fracaoCrua++;

					if(UNICODE_GREGO_RAIZ.matcher(linha).find())
						r.unicodeGrego++;

					Matcher unidadeMatcher = UNIDADE_COLADA.matcher(linha);
					if(unidadeMatcher.find() && !linha.contains("\\text{"))
						r.unidadeColada++;

					if(linha.contains("\\("))
						temMathMultiLinha = true;
					if(linha.contains("\\mathbf{") || linha.contains("\\textbf{"))
						temBoldEmAlgumLugar = true;
				}

				// Checa o conjunto da resolução (não só a última linha): uma observação final
				// sem math depois da resposta em negrito (ex.: "Os demais são divisíveis.") não
				// deve contar como "mathbf ausente" — só sinaliza se NENHUM passo tem destaque.
				if(temMathMultiLinha && !temBoldEmAlgumLugar)
					r.mathbfAusente++;
			}
			catch(Exception | LinkageError e)
			{
				r.excecoes++;
			}
		}

		if(!passosPorExecucao.isEmpty())
		{
			r.passosMin = passosPorExecucao.stream().mapToInt(Integer::intValue).min().orElse(0);
			r.passosMax = passosPorExecucao.stream().mapToInt(Integer::intValue).max().orElse(0);
			r.passosSoma = passosPorExecucao.stream().mapToInt(Integer::intValue).sum();
			r.passosExecucoesOk = passosPorExecucao.size();
		}

		return r;
	}

	static int contarOcorrencias(String texto, String alvo)
	{
		int count = 0, idx = 0;
		while((idx = texto.indexOf(alvo, idx)) != -1)
		{
			count++;
			idx += alvo.length();
		}
		return count;
	}

	static String classeAbreviada(String fqcn)
	{
		int dot = fqcn.lastIndexOf('.');
		return dot >= 0 ? fqcn.substring(dot + 1) : fqcn;
	}

	// ── DTO de resultado ─────────────────────────────────────────────────────

	static class ResultadoElaboracao
	{
		final String classe;
		int passosMin = 0;
		int passosMax = 0;
		int passosSoma = 0;
		int passosExecucoesOk = 0;
		int passosColados = 0;
		int iniciaIgual = 0;
		int logoSuspeito = 0;
		int fracaoCrua = 0;
		int mathbfAusente = 0;
		int unicodeGrego = 0;
		int unidadeColada = 0;
		int excecoes = 0;

		ResultadoElaboracao(String classe) { this.classe = classe; }

		double passosMedia()
		{
			return passosExecucoesOk == 0 ? 0.0 : (double) passosSoma / passosExecucoesOk;
		}

		int score()
		{
			return passosColados + iniciaIgual + logoSuspeito + fracaoCrua
				+ mathbfAusente + unicodeGrego + unidadeColada;
		}

		String toTsv()
		{
			return String.join("\t",
				classe,
				String.valueOf(passosMin),
				String.format("%.1f", passosMedia()),
				String.valueOf(passosMax),
				String.valueOf(passosColados),
				String.valueOf(iniciaIgual),
				String.valueOf(logoSuspeito),
				String.valueOf(fracaoCrua),
				String.valueOf(mathbfAusente),
				String.valueOf(unicodeGrego),
				String.valueOf(unidadeColada),
				String.valueOf(excecoes));
		}
	}
}
