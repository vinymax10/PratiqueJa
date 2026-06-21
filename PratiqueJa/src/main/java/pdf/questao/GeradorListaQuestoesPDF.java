package pdf.questao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.questao.Alternativa;
import modelo.questao.ImagemFile;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;

/**
 * Gera um PDF de lista de questões em layout fluente de 2 colunas
 * (via {@code multicols}). Diferente do gerador de exercícios, as questões
 * têm tamanhos muito variáveis, então não usamos grade com altura fixa —
 * o TeX decide onde quebrar as colunas e as páginas.
 */
public class GeradorListaQuestoesPDF
{
	private final List<Questao> questoes;
	private final String titulo;
	private final String subtitulo;
	private final String categoria;
	private final String instrucao;
	private final boolean premium;
	private final Path pratiquejaStyDir;
	private final String xelatexExe;
	private final LayoutLista layout;
	private final boolean manterFonte;
	private final boolean comAlternativas;
	private final TipoGabarito tipoGabarito;
	private Path workDir;
	private Path texGerado;

	private GeradorListaQuestoesPDF(Builder b)
	{
		this.questoes         = b.questoes;
		this.titulo           = b.titulo;
		this.subtitulo        = b.subtitulo;
		this.categoria        = b.categoria;
		this.instrucao        = b.instrucao;
		this.premium          = b.premium;
		this.pratiquejaStyDir = b.pratiquejaStyDir;
		this.xelatexExe       = b.xelatexExe;
		this.layout           = b.layout;
		this.manterFonte      = b.manterFonte;
		this.comAlternativas  = b.comAlternativas;
		this.tipoGabarito     = b.tipoGabarito;
	}

	// ── API pública ───────────────────────────────────────────────

	public byte[] gerarBytes(Path workDir) throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(workDir);
		this.workDir = workDir;

		if (questoes.isEmpty())
			throw new IllegalStateException("Nenhuma questão informada para a lista.");

		String nomeBase = nomeArquivo();
		Path texFile = workDir.resolve(nomeBase + ".tex");
		Files.writeString(texFile, construirTex(questoes), StandardCharsets.UTF_8);
		this.texGerado = texFile;

		compilar(texFile);

		byte[] bytes = Files.readAllBytes(workDir.resolve(nomeBase + ".pdf"));
		limparAuxiliares(workDir, nomeBase);
		return bytes;
	}

	private void copiarSty(Path workDir) throws IOException
	{
		if (pratiquejaStyDir == null) return;
		Path origem = pratiquejaStyDir.resolve("pratiqueja.sty");
		if (Files.exists(origem))
			Files.copy(origem, workDir.resolve("pratiqueja.sty"), StandardCopyOption.REPLACE_EXISTING);
	}

	private void limparAuxiliares(Path workDir, String nomeBase) throws IOException
	{
		String[] exts = manterFonte
			? new String[]{".aux", ".log", ".out"}
			: new String[]{".tex", ".aux", ".log", ".out"};
		for (String ext : exts)
		{
			Path f = workDir.resolve(nomeBase + ext);
			Files.deleteIfExists(f);
		}
		Files.deleteIfExists(workDir.resolve("pratiqueja.sty"));
	}

	public Path getTexGerado()
	{
		return texGerado;
	}

	// ── Montagem do .tex ─────────────────────────────────────────

	private String construirTex(List<Questao> questoes)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(cabecalho());

		// Cabeçalho da lista (1x) + multicols com fluxo livre das questões
		sb.append(paginaHeader());
		sb.append(abrirColunas());
		for (int i = 0; i < questoes.size(); i++)
			sb.append(blocoQuestao(i + 1, questoes.get(i)));
		sb.append(fecharColunas());

		sb.append("\n\\clearpage\n\n");
		sb.append(cabecalhoGabarito());
		if (tipoGabarito == TipoGabarito.SOMENTE_ALTERNATIVAS)
		{
			sb.append(abrirColunasGabaritoCompacto());
			for (int i = 0; i < questoes.size(); i++)
				sb.append(blocoGabaritoCompacto(i + 1, questoes.get(i)));
			sb.append(fecharColunas());
		}
		else
		{
			sb.append(abrirColunas());
			for (int i = 0; i < questoes.size(); i++)
				sb.append(blocoGabarito(i + 1, questoes.get(i)));
			sb.append(fecharColunas());
		}

		sb.append("\n\\end{document}\n");
		return sb.toString();
	}

	// ── Cabeçalho do documento ────────────────────────────────────

	private String cabecalho()
	{
		return
			"% Gerado automaticamente por GeradorListaQuestoesPDF — não editar manualmente\n"
			+ "\\documentclass[12pt]{article}\n"
			+ "\\usepackage{pratiqueja}\n"
			+ "\\usepackage{multicol}\n"
			+ "\\usepackage{cancel}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n"
			// Ajustes finos da geometria para a régua do multicols ficar próxima
			// (mas sem cruzar) a linha divisória do rodapé do pratiqueja.sty.
			+ "\\addtolength{\\footskip}{0.3cm}\n\n"
			// Comandos matemáticos estruturais tolerantes a uso dentro de \text{} (modo texto):
			// algumas resoluções trazem \overline, \dfrac etc. dentro de \text, o que geraria
			// "Missing $ inserted". \ensuremath garante o modo math e é transparente quando já em math.
			+ "\\let\\PJoverline\\overline\n"
			+ "\\renewcommand{\\overline}[1]{\\ensuremath{\\PJoverline{#1}}}\n"
			+ "\\let\\PJvec\\vec\n"
			+ "\\renewcommand{\\vec}[1]{\\ensuremath{\\PJvec{#1}}}\n"
			+ "\\let\\PJoverrightarrow\\overrightarrow\n"
			+ "\\renewcommand{\\overrightarrow}[1]{\\ensuremath{\\PJoverrightarrow{#1}}}\n"
			+ "\\let\\PJdfrac\\dfrac\n"
			+ "\\renewcommand{\\dfrac}[2]{\\ensuremath{\\PJdfrac{#1}{#2}}}\n"
			+ "\\let\\PJtfrac\\tfrac\n"
			+ "\\renewcommand{\\tfrac}[2]{\\ensuremath{\\PJtfrac{#1}{#2}}}\n"
			+ "\\let\\PJfrac\\frac\n"
			+ "\\renewcommand{\\frac}[2]{\\ensuremath{\\PJfrac{#1}{#2}}}\n\n"
			+ macros()
			+ "\\setsubject{" + titulo + " · " + subtitulo + "}\n\n"
			+ "\\begin{document}\n"
			+ "\\pagenumbering{arabic}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n"
			+ "\\raggedbottom\n\n";
	}

	private String paginaHeader()
	{
		return "\\listheader{" + titulo + "}{" + subtitulo + "}{" + categoria + "}{" + instrucao + "}\n\n";
	}

	private String macros()
	{
		return
			// número da questão — badge azul
			"\\newcommand{\\numex}[1]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1}\\end{tcolorbox}\\hspace{4pt}}\n"

			// helper: imprime uma alternativa ou pula se o texto for "---"
			+ "\\newcommand{\\altline}[2]{%\n"
			+ "  \\def\\altph{---}%\n"
			+ "  \\def\\altarg{#2}%\n"
			+ "  \\ifx\\altarg\\altph\\else\n"
			+ "    \\noindent{\\bfseries\\color{darkblue}\\small(#1)}~{\\small#2}\\par\n"
			+ "  \\fi}\n"

			// alternativas em coluna — "---" são suprimidas
			+ "\\newcommand{\\alts}[5]{%\n"
			+ "  \\altline{A}{#1}%\n"
			+ "  \\altline{B}{#2}%\n"
			+ "  \\altline{C}{#3}%\n"
			+ "  \\altline{D}{#4}%\n"
			+ "  \\altline{E}{#5}}\n"

			// caput em cartão único: "01) 2023 - CESPE - MJSP"
			// arg1 = número; arg2 = meta já formatada (ou "---" para suprimir o trecho)
			+ "\\newcommand{\\qcaput}[2]{%\n"
			+ "  \\def\\qmphold{---}%\n"
			+ "  \\def\\qmparg{#2}%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=4pt,boxrule=0.4pt,"
			+ "colback=rulebg,colframe=sepcolor,"
			+ "left=5pt,right=5pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1)}"
			+ "\\ifx\\qmparg\\qmphold\\else\\hspace{0.4em}{\\footnotesize\\color{mutedtext}#2}\\fi"
			+ "\\end{tcolorbox}\\hspace{4pt}}\n"

			// questão com alternativas: 1=num 2=meta 3=enunciado 4-8=alts
			+ "\\newcommand{\\qx}[8]{%\n"
			+ "  \\noindent\\qcaput{#1}{#2}"
			+ "  {\\small\\color{bodytext}#3}\\par"
			+ "  \\vspace{6pt}"
			+ "  \\alts{#4}{#5}{#6}{#7}{#8}"
			+ "  \\vspace{0.5cm}}\n"

			// questão com imagem: 1=num 2=meta 3=enunciado 4=imagens 5-9=alts
			+ "\\newcommand{\\qxImg}[9]{%\n"
			+ "  \\noindent\\qcaput{#1}{#2}"
			+ "  {\\small\\color{bodytext}#3}\\par"
			+ "  {\\centering #4\\par}"
			+ "  \\vspace{6pt}"
			+ "  \\alts{#5}{#6}{#7}{#8}{#9}"
			+ "  \\vspace{0.5cm}}\n"

			// questão discursiva: 1=num 2=meta 3=enunciado
			+ "\\newcommand{\\qxd}[3]{%\n"
			+ "  \\noindent\\qcaput{#1}{#2}"
			+ "  {\\small\\color{bodytext}#3}\\par"
			+ "  \\vspace{0.5cm}}\n"

			// gabarito: cabeçalho [01 · B] — número azul + letra verde
			+ "\\newcommand{\\resheader}[2]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1"
			+ "\\hspace{5pt}{\\color{mutedtext}\\tiny\\textbullet}\\hspace{5pt}"
			+ "\\color{vegreen}#2}\\end{tcolorbox}\\hspace{4pt}}\n"

			// gabarito com alternativas
			+ "\\newcommand{\\res}[3]{%\n"
			+ "  \\noindent\\resheader{#1}{#2}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#3\\par}"
			+ "\\vspace{0.4cm}}\n"

			// gabarito discursivo
			+ "\\newcommand{\\resd}[2]{%\n"
			+ "  \\noindent\\numex{#1}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#2\\par}"
			+ "\\vspace{0.4cm}}\n"

			// gabarito compacto (só alternativa): badge [num · letra] inline
			+ "\\newcommand{\\resA}[2]{\\resheader{#1}{#2}\\hspace{0.5cm}}\n"

			// gabarito compacto discursivo: apenas o número
			+ "\\newcommand{\\resAd}[1]{\\numex{#1}\\hspace{0.5cm}}\n\n";
	}

	// ── Colunas fluentes (multicols) ──────────────────────────────

	private String abrirColunas()
	{
		return
			"\\setlength{\\columnsep}{1.2em}\n"
			+ "\\setlength{\\columnseprule}{1.5pt}\n"
			+ "\\renewcommand{\\columnseprulecolor}{\\color{sepcolor}}\n"
			+ "\\begin{multicols}{2}\n"
			+ "\\raggedcolumns\n"
			// Tolerância maior para que TeX quebre URLs/palavras longas e não
			// permita que conteúdo extrapole a margem direita da coluna.
			+ "\\sloppy\n"
			+ "\\emergencystretch=3em\n"
			+ "\\hyphenpenalty=200\n"
			+ "\\tolerance=2000\n";
	}

	private String fecharColunas()
	{
		return "\\end{multicols}\n";
	}

	private String blocoQuestao(int num, Questao q)
	{
		String numStr = String.format("%02d", num);
		String meta = metaQuestao(q);
		List<Alternativa> alts = q.getAlternativas();

		if (!comAlternativas || alts == null || alts.isEmpty())
			return "\\qxd{" + numStr + "}{" + meta + "}{" + enunciado(num, q) + "}\n";

		String[] textos = alternativasTexto(alts);
		String imagens = imagensCmd(num, q);

		if (!imagens.isEmpty())
			return "\\qxImg{" + numStr + "}{" + meta + "}{" + enunciadoSemImagem(q) + "}{" + imagens + "}"
				+ "{" + textos[0] + "}"
				+ "{" + textos[1] + "}"
				+ "{" + textos[2] + "}"
				+ "{" + textos[3] + "}"
				+ "{" + textos[4] + "}\n";

		return "\\qx{" + numStr + "}{" + meta + "}{" + enunciado(num, q) + "}"
			+ "{" + textos[0] + "}"
			+ "{" + textos[1] + "}"
			+ "{" + textos[2] + "}"
			+ "{" + textos[3] + "}"
			+ "{" + textos[4] + "}\n";
	}

	// ── Gabarito ─────────────────────────────────────────────────

	private String cabecalhoGabarito()
	{
		String legenda = tipoGabarito == TipoGabarito.SOMENTE_ALTERNATIVAS
			? "Gabarito — Alternativas"
			: "Gabarito — Resolução comentada";
		return "\\listheader{" + titulo + "}{" + subtitulo + "}{" + categoria + "}"
			+ "{" + legenda + "}\n\n";
	}

	private String blocoGabarito(int num, Questao q)
	{
		String numStr = String.format("%02d", num);
		String resolucao = q.getResolucao() != null ? escaparTexto(q.getResolucao()) : "";
		Alternativa correta = comAlternativas ? q.correta() : null;

		if (correta == null)
			return "\\resd{" + numStr + "}{" + resolucao + "}\n";

		return "\\res{" + numStr + "}{" + correta.getLetra() + "}{" + resolucao + "}\n";
	}

	// ── Gabarito compacto (só alternativas) ──────────────────────

	private String abrirColunasGabaritoCompacto()
	{
		return
			"\\setlength{\\columnsep}{1.2em}\n"
			+ "\\setlength{\\columnseprule}{0pt}\n"
			+ "\\begin{multicols}{4}\n"
			+ "\\setlength{\\parindent}{0pt}\n"
			+ "\\raggedright\n"
			+ "\\setlength{\\parskip}{6pt}\n"
			+ "\\sloppy\n"
			+ "\\emergencystretch=3em\n";
	}

	private String blocoGabaritoCompacto(int num, Questao q)
	{
		String numStr = String.format("%02d", num);
		List<Alternativa> alts = q.getAlternativas();
		Alternativa correta = (comAlternativas && alts != null && !alts.isEmpty()) ? q.correta() : null;

		if (correta == null)
			return "\\resAd{" + numStr + "}\\par\n";

		return "\\resA{" + numStr + "}{" + correta.getLetra() + "}\\par\n";
	}

	// ── Helpers ───────────────────────────────────────────────────

	/**
	 * Constrói a meta do caput no formato "ano - banca - orgao".
	 * Retorna "---" como sentinela quando não há nenhum dos três (o macro
	 * {@code \qcaput} suprime esse trecho e mostra apenas o número).
	 */
	private String metaQuestao(Questao q)
	{
		StringBuilder sb = new StringBuilder();
		if (q.getAno() != null && q.getAno().getNome() != null && !q.getAno().getNome().isBlank())
			appendMeta(sb, q.getAno().getNome());
		if (q.getBanca() != null && q.getBanca().getSigla() != null && !q.getBanca().getSigla().isBlank())
			appendMeta(sb, q.getBanca().getSigla());
		if (q.getOrgao() != null && q.getOrgao().getSigla() != null && !q.getOrgao().getSigla().isBlank())
			appendMeta(sb, q.getOrgao().getSigla());
		return sb.length() == 0 ? "---" : escaparTextoPuro(sb.toString());
	}

	private void appendMeta(StringBuilder sb, String valor)
	{
		if (sb.length() > 0) sb.append(" - ");
		sb.append(valor);
	}

	/**
	 * Escapa todos os especiais LaTeX comuns para texto puro (sem math).
	 * Diferente de {@link #escaparTexto}: aqui o conteúdo nunca é matemática,
	 * então também escapamos &amp;, #, _ (e os já cobertos % e $).
	 */
	private String escaparTextoPuro(String s)
	{
		if (s == null) return "";
		s = s.replace("\\", "\\textbackslash{}")
		     .replace("&", "\\&")
		     .replace("%", "\\%")
		     .replace("$", "\\$")
		     .replace("#", "\\#")
		     .replace("_", "\\_")
		     .replace("^", "\\textasciicircum{}")
		     .replace("~", "\\textasciitilde{}");
		return substituirUnicodeMath(s);
	}

	private String enunciado(int num, Questao q)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (Paragrafo p : q.getParagrafos())
		{
			String parte;
			if (p.isTipoImagem())
			{
				parte = includegraphics(num, p);
				if (parte.isEmpty())   // imagem ausente: pula o parágrafo
					continue;
			}
			else if (p.getTexto() != null && !p.getTexto().isBlank())
				parte = escaparTexto(p.getTexto());
			else
				continue;

			if (!primeiro) sb.append("\\par ");
			sb.append(parte);
			primeiro = false;
		}
		return sb.toString();
	}

	private String includegraphics(int num, Paragrafo p)
	{
		String nome = gravarImagem(num, p);
		if (nome == null)
			return "";
		return "{\\centering\\includegraphics[width=0.6\\linewidth]{" + nome + "}\\par}";
	}

	/**
	 * Grava a imagem do parágrafo no diretório de trabalho e devolve o nome do arquivo.
	 * Devolve {@code null} quando não há imagem para gravar (sem diretório, sem
	 * {@code ImagemFile}, blob nulo/vazio ou falha de leitura), evitando referenciar
	 * um arquivo inexistente em {@code \includegraphics} (que quebra a compilação).
	 */
	private String gravarImagem(int num, Paragrafo p)
	{
		if (workDir == null)
			return null;

		String nome = String.format("img_%02d_%d.png", num, p.getOrdem());
		try
		{
			ImagemFile imf = p.getImagemFile();
			Blob blob = imf != null ? imf.getFile() : null;
			if (blob == null)
				return null;

			byte[] bytes = blob.getBytes(1, (int) blob.length());
			if (bytes == null || bytes.length == 0)
				return null;

			Files.write(workDir.resolve(nome), bytes);
			return nome;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Escapa o texto preservando as regiões matemáticas (\(...\) e \[...\]).
	 * Nas regiões de texto puro, escapa especiais LaTeX (% $ ^ ~) que de outro
	 * modo quebram a compilação, e converte símbolos Unicode em comandos LaTeX.
	 * As regiões matemáticas são mantidas intactas (seus ^ _ { } são intencionais).
	 */
	private String escaparTexto(String s)
	{
		if (s == null) return "";

		StringBuilder out = new StringBuilder();
		int i = 0;
		while (i < s.length())
		{
			int open = primeiroIndice(s.indexOf("\\(", i), s.indexOf("\\[", i));
			if (open < 0)
			{
				out.append(escaparForaMath(s.substring(i)));
				break;
			}

			out.append(escaparForaMath(s.substring(i, open)));

			String fim = s.startsWith("\\(", open) ? "\\)" : "\\]";
			int close = s.indexOf(fim, open + 2);
			if (close < 0)
			{
				// fórmula sem fechamento: preserva o restante (só protege o % cru)
				out.append(protegerRegiaoMath(s.substring(open)));
				break;
			}

			// região matemática preservada (delimitadores inclusos);
			// o único especial que ainda precisa de escape aqui é o % (comentário).
			out.append(protegerRegiaoMath(s.substring(open, close + 2)));
			i = close + 2;
		}
		return out.toString();
	}

	/**
	 * Dentro das regiões matemáticas preservamos tudo (^ _ {{ }} $ são intencionais),
	 * exceto dois casos que quebram a compilação mesmo em modo math:
	 *  - o caractere de comentário % (comenta o resto da linha, inclusive a chave que
	 *    fecharia o \res → "Runaway argument"): escapamos o % ainda não escapado (\%);
	 *  - uma barra invertida seguida de dígito (ex.: 2\3), que é uma sequência de controle
	 *    inexistente ("Undefined control sequence") e quase sempre um erro de digitação de
	 *    "/" (fração): convertemos \<dígito> em /<dígito> (sem tocar em \\ nem em \cmd).
	 */
	private String protegerRegiaoMath(String math)
	{
		return math.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		           .replaceAll("(?<!\\\\)%", "\\\\%");
	}

	/** Menor índice não-negativo entre os dois; -1 quando ambos são -1. */
	private int primeiroIndice(int a, int b)
	{
		if (a < 0) return b;
		if (b < 0) return a;
		return Math.min(a, b);
	}

	/** Escapa especiais LaTeX e converte Unicode em um trecho fora de matemática. */
	private String escaparForaMath(String s)
	{
		if (s.isEmpty()) return s;
		s = s.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		     .replaceAll("(?<!\\\\)%", "\\\\%")
		     .replaceAll("(?<!\\\\)\\$", "\\\\\\$")
		     .replaceAll("(?<!\\\\)&", "\\\\&")
		     .replaceAll("(?<!\\\\)#", "\\\\#")
		     .replaceAll("(?<!\\\\)_", "\\\\_")
		     .replaceAll("(?<!\\\\)\\^", "\\\\textasciicircum{}")
		     .replaceAll("(?<!\\\\)~", "\\\\textasciitilde{}");
		return quebrarUrls(substituirUnicodeMath(s));
	}

	// URL em texto puro (após o escape): http(s)://… ou www.… até o próximo espaço.
	private static final Pattern URL_PATTERN = Pattern.compile("(?:https?://|www\\.)\\S*");

	/**
	 * Insere pontos de quebra invisíveis ({@code \\allowbreak}) após os separadores
	 * de uma URL ( / . - ? = &amp; : # ~ ), evitando que links longos estourem a largura
	 * da coluna. A URL continua legível e sem hífens; apenas ganha onde quebrar.
	 */
	private String quebrarUrls(String s)
	{
		if (s == null || (s.indexOf("http") < 0 && s.indexOf("www") < 0))
			return s;

		Matcher m = URL_PATTERN.matcher(s);
		StringBuffer out = new StringBuffer();
		while (m.find())
		{
			String url = m.group().replaceAll("([/.\\-?=&:#~])", "$1\\\\allowbreak{}");
			m.appendReplacement(out, Matcher.quoteReplacement(url));
		}
		m.appendTail(out);
		return out.toString();
	}

	/**
	 * Converte símbolos matemáticos Unicode (∞, →, ≤, π, …) em comandos LaTeX
	 * encapsulados por {@code \ensuremath}, válidos tanto em modo texto quanto
	 * matemático. Evita os erros "Missing $ inserted" e glifos ausentes na fonte.
	 */
	private String substituirUnicodeMath(String s)
	{
		return s
			.replace("∞", "\\ensuremath{\\infty}")
			.replace("→", "\\ensuremath{\\rightarrow}")
			.replace("➝", "\\ensuremath{\\rightarrow}")
			.replace("←", "\\ensuremath{\\leftarrow}")
			.replace("↔", "\\ensuremath{\\leftrightarrow}")
			.replace("⇒", "\\ensuremath{\\Rightarrow}")
			.replace("⇔", "\\ensuremath{\\Leftrightarrow}")
			.replace("∧", "\\ensuremath{\\land}")
			.replace("∨", "\\ensuremath{\\lor}")
			.replace("¬", "\\ensuremath{\\lnot}")
			.replace("≤", "\\ensuremath{\\leq}")
			.replace("≥", "\\ensuremath{\\geq}")
			.replace("≠", "\\ensuremath{\\neq}")
			.replace("≈", "\\ensuremath{\\approx}")
			.replace("≡", "\\ensuremath{\\equiv}")
			.replace("±", "\\ensuremath{\\pm}")
			.replace("∓", "\\ensuremath{\\mp}")
			.replace("×", "\\ensuremath{\\times}")
			.replace("÷", "\\ensuremath{\\div}")
			.replace("∈", "\\ensuremath{\\in}")
			.replace("∉", "\\ensuremath{\\notin}")
			.replace("⊂", "\\ensuremath{\\subset}")
			.replace("⊃", "\\ensuremath{\\supset}")
			.replace("⊆", "\\ensuremath{\\subseteq}")
			.replace("⊇", "\\ensuremath{\\supseteq}")
			.replace("∪", "\\ensuremath{\\cup}")
			.replace("∩", "\\ensuremath{\\cap}")
			.replace("∅", "\\ensuremath{\\emptyset}")
			.replace("∀", "\\ensuremath{\\forall}")
			.replace("∃", "\\ensuremath{\\exists}")
			.replace("∴", "\\ensuremath{\\therefore}")
			.replace("√", "\\ensuremath{\\surd}")
			.replace("∝", "\\ensuremath{\\propto}")
			.replace("∠", "\\ensuremath{\\angle}")
			.replace("π", "\\ensuremath{\\pi}")
			.replace("Δ", "\\ensuremath{\\Delta}")
			.replace("Λ", "\\ensuremath{\\Lambda}")
			.replace("Ω", "\\ensuremath{\\Omega}")
			.replace("Σ", "\\ensuremath{\\Sigma}")
			.replace("Φ", "\\ensuremath{\\Phi}")
			.replace("α", "\\ensuremath{\\alpha}")
			.replace("β", "\\ensuremath{\\beta}")
			.replace("γ", "\\ensuremath{\\gamma}")
			.replace("θ", "\\ensuremath{\\theta}")
			.replace("λ", "\\ensuremath{\\lambda}")
			.replace("μ", "\\ensuremath{\\mu}")
			.replace("°", "\\ensuremath{{}^{\\circ}}")
			.replace("²", "\\ensuremath{{}^{2}}")
			.replace("³", "\\ensuremath{{}^{3}}")
			.replace("¹", "\\ensuremath{{}^{1}}")
			.replace("½", "\\ensuremath{\\tfrac{1}{2}}")
			.replace("⅓", "\\ensuremath{\\tfrac{1}{3}}")
			.replace("¼", "\\ensuremath{\\tfrac{1}{4}}")
			.replace("¾", "\\ensuremath{\\tfrac{3}{4}}");
	}

	private String enunciadoSemImagem(Questao q)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (Paragrafo p : q.getParagrafos())
		{
			if (p.isTipoImagem()) continue;
			if (p.getTexto() == null || p.getTexto().isBlank()) continue;

			if (!primeiro) sb.append("\\par ");
			sb.append(escaparTexto(p.getTexto()));
			primeiro = false;
		}
		return sb.toString();
	}

	private String imagensCmd(int num, Questao q)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeira = true;
		for (Paragrafo p : q.getParagrafos())
		{
			if (!p.isTipoImagem()) continue;

			String nome = gravarImagem(num, p);
			if (nome == null) continue;   // imagem ausente: pula

			if (!primeira) sb.append("\\par\\vspace{4pt}");
			sb.append("\\includegraphics[width=0.8\\linewidth]{").append(nome).append("}");
			primeira = false;
		}
		return sb.toString();
	}

	private String[] alternativasTexto(List<Alternativa> alts)
	{
		String[] textos = {"---", "---", "---", "---", "---"};
		for (int i = 0; i < Math.min(alts.size(), 5); i++)
			textos[i] = alts.get(i).getTexto() != null ? escaparTexto(alts.get(i).getTexto()) : "---";
		return textos;
	}

	private String nomeArquivo()
	{
		String base = (titulo + "_" + subtitulo)
			.toLowerCase()
			.replaceAll("[áàãâä]", "a")
			.replaceAll("[éèêë]", "e")
			.replaceAll("[íìîï]", "i")
			.replaceAll("[óòõôö]", "o")
			.replaceAll("[úùûü]", "u")
			.replaceAll("[ç]", "c")
			.replaceAll("[^a-z0-9]+", "_")
			.replaceAll("_+", "_")
			.replaceAll("^_|_$", "");
		return (premium ? "" : "gratis_") + base;
	}

	private void compilar(Path texFile) throws IOException, InterruptedException
	{
		for (int passagem = 1; passagem <= 2; passagem++)
		{
			ProcessBuilder pb = new ProcessBuilder(
				xelatexExe, "-interaction=nonstopmode", texFile.getFileName().toString())
				.directory(texFile.getParent().toFile())
				.redirectOutput(ProcessBuilder.Redirect.DISCARD)
				.redirectError(ProcessBuilder.Redirect.DISCARD);

			pb.environment().put("TEXINPUTS",
				texFile.getParent().toAbsolutePath() + ";");

			int exitCode = pb.start().waitFor();
			if (exitCode != 0 && passagem == 2)
				throw new IOException(
					"XeLaTeX falhou (código " + exitCode + ") ao compilar " + texFile);
		}
	}

	// ── Builder ───────────────────────────────────────────────────

	public static class Builder
	{
		private List<Questao> questoes;
		private String titulo          = "Lista de Questões";
		private String subtitulo       = "";
		private String categoria       = "Matemática";
		private String instrucao       = "Marque a alternativa correta e mostre o desenvolvimento.";
		private boolean premium        = false;
		private Path pratiquejaStyDir  = null;
		private String xelatexExe      = "xelatex";
		private LayoutLista layout     = LayoutLista.PADRAO;
		private boolean manterFonte    = false;
		private boolean comAlternativas = false;
		private TipoGabarito tipoGabarito = TipoGabarito.COMPLETO;

		public Builder questoes(List<Questao> v)    { questoes         = v; return this; }
		public Builder titulo(String v)             { titulo           = v; return this; }
		public Builder subtitulo(String v)          { subtitulo        = v; return this; }
		public Builder categoria(String v)          { categoria        = v; return this; }
		public Builder instrucao(String v)          { instrucao        = v; return this; }
		public Builder premium(boolean v)           { premium          = v; return this; }
		public Builder layout(LayoutLista v)        { layout           = v; return this; }
		public Builder comAlternativas(boolean v)   { comAlternativas  = v; return this; }
		public Builder tipoGabarito(TipoGabarito v) { tipoGabarito     = v; return this; }
		public Builder manterFonte(boolean v)       { manterFonte      = v; return this; }
		public Builder pratiquejaStyDir(Path v)     { pratiquejaStyDir = v; return this; }
		public Builder xelatexExe(String v)         { xelatexExe       = v; return this; }

		public GeradorListaQuestoesPDF build()
		{
			if (questoes == null || questoes.isEmpty())
				throw new IllegalStateException("Informe as questões com .questoes(...)");
			return new GeradorListaQuestoesPDF(this);
		}
	}
}
