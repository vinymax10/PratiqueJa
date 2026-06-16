package service.avaliacao;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.matematica.AlternativaExercicio;
import modelo.matematica.Exercicio;

/**
 * Gera a página de gabarito de uma avaliação para uso no modo AGRUPADO_NO_FINAL.
 * Recebe os exercícios já gerados pelo GeradorAvaliacaoPdfService.
 */
@ApplicationScoped
public class GeradorGabaritoPdfService implements Serializable
{
	private static final long serialVersionUID = 1L;

	public byte[] gerarGabarito(PedidoAvaliacao pedido, List<BlocoExercicio> blocos,
		String codigoAvaliacao, Path pratiquejaStyDir, String xelatexExe, Path workDir)
		throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(pratiquejaStyDir, workDir);

		boolean comResolucao = pedido.getTipoGabarito() == TipoGabarito.COM_RESOLUCAO;

		String nomeBase = "gabarito_" + codigoAvaliacao.replace("-", "_").toLowerCase();
		String tex = construirTex(pedido, codigoAvaliacao, blocos, comResolucao);

		Path texFile = workDir.resolve(nomeBase + ".tex");
		Files.writeString(texFile, tex, StandardCharsets.UTF_8);

		compilar(texFile, xelatexExe);

		byte[] bytes = Files.readAllBytes(workDir.resolve(nomeBase + ".pdf"));
		limparAuxiliares(workDir, nomeBase);
		return bytes;
	}

	// ── Construção do .tex ────────────────────────────────────────────

	private String construirTex(PedidoAvaliacao pedido, String codigoAvaliacao,
		List<BlocoExercicio> blocos, boolean comResolucao)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(preambulo());
		sb.append(cabecalhoGabarito(pedido, codigoAvaliacao));

		int numInicial = 1;
		for (BlocoExercicio bloco : blocos)
		{
			if (bloco.isAlternativas() && !comResolucao)
				sb.append(gabaritoCompacto(bloco.getExercicios(), numInicial));
			else
				sb.append(gabaritoComResolucao(bloco.getExercicios(), bloco.isAlternativas(), numInicial));
			numInicial += bloco.getExercicios().size();
		}

		sb.append("\n\\end{document}\n");
		return sb.toString();
	}

	private String preambulo()
	{
		return
			"\\documentclass[12pt,a4paper]{article}\n"
			+ "\\usepackage{pratiqueja}\n"
			+ "\\usepackage{tabularray}\n"
			+ "\\usepackage{cancel}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n\n"
			+ macros()
			+ "\\begin{document}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n\n";
	}

	private String cabecalhoGabarito(PedidoAvaliacao pedido, String codigoAvaliacao)
	{
		return
			"\\begin{tcolorbox}[enhanced, arc=4pt, boxrule=1pt,\n"
			+ "  colback=rulebg, colframe=darkblue,\n"
			+ "  left=8pt, right=8pt, top=4pt, bottom=4pt]\n"
			+ "  {\\large\\bfseries\\color{darkblue} GABARITO}\\enspace"
			+ "{\\normalsize\\color{mutedtext}---}\\enspace"
			+ "{\\normalsize\\color{bodytext}" + escapar(pedido.getNomeDocumento().getNome())
			+ ": " + escapar(pedido.getTitulo()) + "}"
			+ "\\hfill{\\footnotesize\\color{mutedtext}\\texttt{" + codigoAvaliacao + "}}\n"
			+ "\\end{tcolorbox}\n\n"
			+ "\\vspace{6pt}\n\n";
	}

	private String gabaritoCompacto(List<Exercicio> exercicios, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\noindent\\small\n");
		for (int i = 0; i < exercicios.size(); i++)
		{
			AlternativaExercicio correta = exercicios.get(i).correta();
			String letra = correta != null ? correta.getLetra() : "?";
			sb.append(String.format("\\resheader{%02d}{%s}\\enspace ", numInicial + i, letra));
		}
		sb.append("\n\n");
		return sb.toString();
	}

	private String gabaritoComResolucao(List<Exercicio> exercicios, boolean comAlternativas, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(abrirGradeGabarito());
		boolean primeiro = true;
		for (int i = 0; i < exercicios.size(); i += 2)
		{
			if (!primeiro) sb.append("\\\\\\hline\n");
			Exercicio esq = exercicios.get(i);
			Exercicio dir = (i + 1 < exercicios.size()) ? exercicios.get(i + 1) : null;
			sb.append(macroGabarito(numInicial + i, esq, comAlternativas))
			  .append(" &\n")
			  .append(dir != null ? macroGabarito(numInicial + i + 1, dir, comAlternativas) : "")
			  .append(" \\\\\n");
			primeiro = false;
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
			+ "{\\fontsize{10}{12}\\selectfont\\color{bodytext}#3\\par}"
			+ "\\vspace{0.4cm}}\n"
			+ "\\newcommand{\\resd}[2]{%\n"
			+ "  \\noindent\\numex{#1}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\color{bodytext}#2\\par}"
			+ "\\vspace{0.4cm}}\n\n";
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
				throw new IOException("XeLaTeX falhou (código " + exitCode + ") em " + texFile);
		}
	}

	private void limparAuxiliares(Path workDir, String nomeBase) throws IOException
	{
		for (String ext : new String[]{".tex", ".aux", ".log", ".out"})
			Files.deleteIfExists(workDir.resolve(nomeBase + ext));
		Files.deleteIfExists(workDir.resolve("pratiqueja.sty"));
	}
}
