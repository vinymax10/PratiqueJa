package service.avaliacao;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;

/**
 * Gera a página de gabarito de uma avaliação para uso no modo AGRUPADO_NO_FINAL.
 * Recebe os exercícios já gerados pelo GeradorAvaliacaoPdfService.
 */
@ApplicationScoped
public class GeradorGabaritoPdfService implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Gera UM único PDF com os gabaritos de todos os exemplares (modo AGRUPADO_NO_FINAL).
	 * <ul>
	 *   <li>Somente gabarito: cada exemplar em modo compacto, separados por linha tracejada
	 *       (vários por página, fluindo);</li>
	 *   <li>Com resolução: cada exemplar começa em uma nova página.</li>
	 * </ul>
	 * O mapa deve preservar a ordem dos exemplares (use LinkedHashMap): chave = código, valor = blocos.
	 */
	public byte[] gerarGabaritosCombinados(PedidoAvaliacao pedido,
		Map<String, List<BlocoExercicio>> porExemplar, Path pratiquejaStyDir, String xelatexExe, Path workDir)
		throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(pratiquejaStyDir, workDir);

		boolean comResolucao = pedido.getTipoGabarito() == TipoGabarito.COM_RESOLUCAO;

		String nomeBase = "gabaritos_" + pedido.getCodigoBatch().replace("-", "_").toLowerCase();
		String tex = construirTexCombinado(pedido, porExemplar, comResolucao);

		Path texFile = workDir.resolve(nomeBase + ".tex");
		Files.writeString(texFile, tex, StandardCharsets.UTF_8);

		compilar(texFile, xelatexExe);

		Path pdfFile = workDir.resolve(nomeBase + ".pdf");
		byte[] bytes = Files.exists(pdfFile) ? Files.readAllBytes(pdfFile) : new byte[0];
		if (!pdfValido(bytes))
			throw new IOException("XeLaTeX não produziu um PDF válido para " + texFile
				+ "\n──── trecho do .log ────\n" + extrairErroDoLog(texFile));

		limparAuxiliares(workDir, nomeBase);
		return bytes;
	}

	/** Valida que os bytes são de um PDF íntegro (cabeçalho %PDF- e trailer %%EOF),
	 *  para não propagar um PDF truncado/vazio ao merge (erro "Missing root object"). */
	private boolean pdfValido(byte[] bytes)
	{
		if (bytes == null || bytes.length < 8)
			return false;
		String head = new String(bytes, 0, 5, StandardCharsets.US_ASCII);
		if (!head.equals("%PDF-"))
			return false;
		int from = Math.max(0, bytes.length - 1024);
		String tail = new String(bytes, from, bytes.length - from, StandardCharsets.ISO_8859_1);
		return tail.contains("%%EOF");
	}

	// ── Construção do .tex ────────────────────────────────────────────

	private String construirTexCombinado(PedidoAvaliacao pedido,
		Map<String, List<BlocoExercicio>> porExemplar, boolean comResolucao)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(preambulo());

		// Rodapé central (\pj@subject) = título da avaliação, no lugar do placeholder "Assunto".
		sb.append("\\setsubject{")
		  .append(escapar(pedido.getNomeDocumento().getNome()))
		  .append(": ")
		  .append(escapar(pedido.getTitulo()))
		  .append("}\n\n");

		boolean primeiro = true;
		for (Map.Entry<String, List<BlocoExercicio>> exemplar : porExemplar.entrySet())
		{
			if (!primeiro)
				sb.append(comResolucao ? "\\newpage\n\n" : separadorTracejado());

			sb.append(cabecalhoGabarito(pedido, exemplar.getKey()));

			if (!comResolucao)
			{
				// Somente gabarito: todos os itens num único fluxo contínuo (quebra só no fim da linha).
				sb.append(gabaritoCompacto(exemplar.getValue()));
			}
			else
			{
				// Com resolução: passo a passo, por bloco.
				int numInicial = 1;
				for (BlocoExercicio bloco : exemplar.getValue())
				{
					sb.append(gabaritoComResolucao(bloco.getExercicios(), bloco.isAlternativas(), numInicial));
					numInicial += bloco.getExercicios().size();
				}
			}
			primeiro = false;
		}

		sb.append("\n\\end{document}\n");
		return sb.toString();
	}

	/** Linha tracejada que separa o gabarito de um exemplar do próximo (modo somente gabarito). */
	private String separadorTracejado()
	{
		return
			"\n\\par\\vspace{12pt}\\noindent\n"
			+ "\\tikz\\draw[dashed, line width=0.7pt, color=graycolor] (0,0) -- (\\linewidth,0);\n"
			+ "\\par\\vspace{12pt}\\noindent\n\n";
	}

	private String preambulo()
	{
		return
			"\\documentclass[12pt,a4paper]{article}\n"
			+ "\\usepackage{pratiqueja}\n"
			+ "\\usepackage{tabularray}\n"
			+ "\\usepackage{cancel}\n"
			+ "\\usepackage{tikz}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n\n"
			+ macros()
			+ "\\begin{document}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n\n";
	}

	private String cabecalhoGabarito(PedidoAvaliacao pedido, String codigoAvaliacao)
	{
		// Mesmo estilo do \listheader: caixa branca, filete azul à esquerda, wordmark "PratiqueJá".
		String documento = escapar(pedido.getNomeDocumento().getNome()) + ": " + escapar(pedido.getTitulo());

		return
			"\\begin{tcolorbox}[\n"
			+ "  enhanced, arc=0pt, boxrule=0pt,\n"
			+ "  colback=white, colframe=white,\n"
			+ "  left=14pt, right=14pt, top=6pt, bottom=5pt,\n"
			+ "  borderline west={5pt}{0pt}{darkblue},\n"
			+ "  borderline south={0.8pt}{0pt}{sepcolor},\n"
			+ "]\n"
			+ "  \\noindent\n"
			+ "  {\\color{titletext}\\fontsize{16}{20}\\selectfont\\bfseries GABARITO}\\hfill\n"
			+ "  {\\color{mutedtext}\\small\\textbf{Pratique}\\textcolor{darkblue}{\\textbf{Já}}}\\par\n"
			+ "  \\vspace{3pt}\n"
			+ "  {\\color{mutedtext}\\small " + documento
			+ "\\hfill\\texttt{Cód.: " + codigoAvaliacao + "}}\n"
			+ "\\end{tcolorbox}\n\n"
			+ "\\vspace{4pt}\n\n";
	}

	private String gabaritoCompacto(List<BlocoExercicio> blocos)
	{
		// Modo compacto: "NN) R" (número em azul + parêntese, resposta em verde) de TODOS os itens
		// num único fluxo contínuo, quebrando só ao chegar no fim da linha/página. O parêntese evita
		// confusão com sinal negativo. A resposta é a letra (alternativas) ou o conteúdo da
		// alternativa correta (discursiva).
		StringBuilder sb = new StringBuilder();
		sb.append("\\noindent\\small\n");
		int num = 1;
		for (BlocoExercicio bloco : blocos)
		{
			boolean mostrarLetra = bloco.isAlternativas();
			for (Exercicio e : bloco.getExercicios())
			{
				sb.append("{\\bfseries\\color{darkblue}")
				  .append(String.format("%02d", num))
				  .append(")}~{\\bfseries\\color{vegreen}")
				  .append(respostaCompacta(e, mostrarLetra))
				  .append("}\\hspace{16pt}%\n");
				num++;
			}
		}
		sb.append("\n\n");
		return sb.toString();
	}

	/** Resposta do gabarito compacto: a letra (alternativas) ou o conteúdo da alternativa
	 *  correta (discursiva — o exercício não exibe alternativas, então mostra a resposta). */
	private String respostaCompacta(Exercicio e, boolean mostrarLetra)
	{
		AlternativaExercicio correta = e.correta();
		if (correta == null)
			return "?";
		if (mostrarLetra)
			return correta.getLetra();
		return correta.getTexto() != null ? escapar(correta.getTexto()) : "?";
	}

	private String gabaritoComResolucao(List<Exercicio> exercicios, boolean comAlternativas, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(abrirGradeGabarito());
		for (int i = 0; i < exercicios.size(); i += 2)
		{
			Exercicio esq = exercicios.get(i);
			Exercicio dir = (i + 1 < exercicios.size()) ? exercicios.get(i + 1) : null;
			sb.append(macroGabarito(numInicial + i, esq, comAlternativas))
			  .append(" &\n")
			  .append(dir != null ? macroGabarito(numInicial + i + 1, dir, comAlternativas) : "")
			  .append(" \\\\\n");
		}
		sb.append(fecharGradeGabarito());
		return sb.toString();
	}

	private String macroGabarito(int num, Exercicio e, boolean comAlternativas)
	{
		String numStr = String.format("%02d", num);
		String resolucao = e.getResolucao() != null ? escapar(e.getResolucao()) : "";
		AlternativaExercicio correta = comAlternativas ? e.correta() : null;

		if (correta == null)
			return "\\resd{" + numStr + "}{" + resolucao + "}";
		return "\\res{" + numStr + "}{" + correta.getLetra() + "}{" + resolucao + "}";
	}

	// ── Grades ────────────────────────────────────────────────────────

	private String abrirGradeGabarito()
	{
		return
			"\\DefTblrTemplate{caption}{default}{}\n"
			+ "\\DefTblrTemplate{conthead}{default}{}\n"
			+ "\\DefTblrTemplate{conthead-text}{default}{}\n"
			+ "\\DefTblrTemplate{contfoot}{default}{}\n"
			+ "\\DefTblrTemplate{contfoot-text}{default}{}\n"
			+ "\\begin{longtblr}[label=none]{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"
			+ "  column{2} = {rightsep=0pt},\n"
			+ "  rows     = {valign=t, rowsep=0pt},\n"
			+ "}\n";
	}

	private String fecharGradeGabarito() { return "\\end{longtblr}\n"; }

	// ── Macros LaTeX ──────────────────────────────────────────────────

	private String macros()
	{
		return
			"\\newcommand{\\numex}[1]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1}\\end{tcolorbox}\\hspace{4pt}}\n"
			+ "\\newcommand{\\resheader}[2]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1"
			+ "\\hspace{5pt}{\\color{mutedtext}\\tiny\\textbullet}\\hspace{5pt}"
			+ "\\color{vegreen}#2}\\end{tcolorbox}\\hspace{4pt}}\n"
			+ "\\newcommand{\\res}[3]{%\n"
			+ "  \\noindent\\resheader{#1}{#2}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#3\\par}"
			+ "\\vspace{0.5cm}}\n"
			+ "\\newcommand{\\resd}[2]{%\n"
			+ "  \\noindent\\numex{#1}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#2\\par}"
			+ "\\vspace{0.5cm}}\n\n";
	}

	// ── Helpers ───────────────────────────────────────────────────────

	private String escapar(String s)
	{
		if (s == null) return "";
		return s.replaceAll("(?<!\\\\)%", "\\\\%")
		        .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	private void copiarSty(Path styDir, Path workDir) throws IOException
	{
		if (styDir == null)
			throw new IOException("Diretório do pratiqueja.sty não foi informado.");
		Path origem = styDir.resolve("pratiqueja.sty");
		if (!Files.exists(origem))
			throw new IOException("Arquivo pratiqueja.sty não encontrado em " + origem);
		Files.copy(origem, workDir.resolve("pratiqueja.sty"), StandardCopyOption.REPLACE_EXISTING);
	}

	private void compilar(Path texFile, String xelatexExe) throws IOException, InterruptedException
	{
		for (int passagem = 1; passagem <= 2; passagem++)
		{
			ProcessBuilder pb = new ProcessBuilder(
				xelatexExe, "-interaction=nonstopmode", texFile.getFileName().toString())
				.directory(texFile.getParent().toFile())
				.redirectOutput(ProcessBuilder.Redirect.DISCARD)
				.redirectError(ProcessBuilder.Redirect.DISCARD);
			pb.environment().put("TEXINPUTS", texFile.getParent().toAbsolutePath() + ";");

			int exitCode = pb.start().waitFor();
			if (exitCode != 0 && passagem == 2)
				throw new IOException("XeLaTeX falhou (código " + exitCode + ") em " + texFile
					+ "\n──── trecho do .log ────\n" + extrairErroDoLog(texFile));
		}
	}

	private String extrairErroDoLog(Path texFile)
	{
		String nomeBase = texFile.getFileName().toString().replaceFirst("\\.tex$", "");
		Path logFile = texFile.getParent().resolve(nomeBase + ".log");
		if (!Files.exists(logFile))
			return "(arquivo .log não encontrado em " + logFile + ")";

		try
		{
			List<String> linhas = Files.readAllLines(logFile, StandardCharsets.UTF_8);
			StringBuilder erro = new StringBuilder();
			for (int i = 0; i < linhas.size(); i++)
			{
				String l = linhas.get(i);
				if (l.startsWith("!") || l.startsWith("l."))
				{
					int fim = Math.min(linhas.size(), i + 6);
					for (int j = i; j < fim; j++)
						erro.append(linhas.get(j)).append('\n');
					erro.append("...\n");
				}
			}
			return erro.length() > 0 ? erro.toString() : "(nenhum erro explícito; veja: " + logFile + ")";
		}
		catch (IOException ex)
		{
			return "(falha ao ler .log: " + ex.getMessage() + ")";
		}
	}

	private void limparAuxiliares(Path workDir, String nomeBase) throws IOException
	{
		for (String ext : new String[]{".tex", ".aux", ".log", ".out"})
			Files.deleteIfExists(workDir.resolve(nomeBase + ext));
		Files.deleteIfExists(workDir.resolve("pratiqueja.sty"));
	}
}
