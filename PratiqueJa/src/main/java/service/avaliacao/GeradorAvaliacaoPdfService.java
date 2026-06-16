package service.avaliacao;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import dao.exercicio.ExercicioPadraoDAO;
import matematica.GeradorExercicio;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.AlternativaExercicio;
import modelo.matematica.Exercicio;
import modelo.matematica.ParagrafoExercicio;
import modelo.questao.ImagemFile;

/**
 * Gera o PDF de uma única avaliação a partir de um PedidoAvaliacao.
 * Produz o .tex e o compila via xelatex; retorna os bytes do PDF.
 */
@ApplicationScoped
public class GeradorAvaliacaoPdfService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	/** Gera e retorna os exercícios para uma avaliação, agrupados por item (cada bloco mantém
	 *  o formato — alternativas ou discursiva — do seu item). Deve ser chamado uma vez e o
	 *  resultado compartilhado entre gerarAvaliacao() e GeradorGabaritoPdfService.gerarGabarito(). */
	public List<BlocoExercicio> gerarExercicios(PedidoAvaliacao pedido)
	{
		List<BlocoExercicio> blocos = new ArrayList<>();
		for (ItemPedidoAvaliacao item : pedido.getItens())
		{
			ExercicioPadrao padrao = exercicioPadraoDAO.buscar(item.getAssunto(), item.getNivel());
			if (padrao == null)
				continue;

			String classeNome = padrao.getClasse();
			List<Exercicio> exercicios = new ArrayList<>(item.getQuantidade());
			for (int i = 0; i < item.getQuantidade(); i++)
				exercicios.add(instanciarGerador(classeNome).gerar());

			blocos.add(new BlocoExercicio(item.getFormato(), exercicios));
		}
		return blocos;
	}

	public byte[] gerarAvaliacao(PedidoAvaliacao pedido, List<BlocoExercicio> blocos, String codigoAvaliacao,
		Path pratiquejaStyDir, String xelatexExe, Path workDir, boolean incluirGabarito)
		throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(pratiquejaStyDir, workDir);

		String nomeBase = "avaliacao_" + codigoAvaliacao.replace("-", "_").toLowerCase();
		String tex = construirTex(pedido, codigoAvaliacao, blocos, incluirGabarito);

		Path texFile = workDir.resolve(nomeBase + ".tex");
		Files.writeString(texFile, tex, StandardCharsets.UTF_8);

		compilar(texFile, xelatexExe);

		byte[] bytes = Files.readAllBytes(workDir.resolve(nomeBase + ".pdf"));
		limparAuxiliares(workDir, nomeBase);
		return bytes;
	}

	@SuppressWarnings("unchecked")
	private GeradorExercicio instanciarGerador(String classeNome)
	{
		try
		{
			Class<? extends GeradorExercicio> cls =
				(Class<? extends GeradorExercicio>) Class.forName(classeNome);
			return cls.getDeclaredConstructor().newInstance();
		}
		catch (ReflectiveOperationException e)
		{
			throw new IllegalStateException("Gerador não encontrado: " + classeNome, e);
		}
	}

	// ── Construção do .tex ────────────────────────────────────────────

	private String construirTex(PedidoAvaliacao pedido, String codigoAvaliacao,
		List<BlocoExercicio> blocos, boolean incluirGabarito)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(preambulo());
		sb.append(cabecalhoAvaliacao(pedido, codigoAvaliacao));
		sb.append(linhaAluno());
		sb.append("\\vspace{6pt}\n\n");
		sb.append(blocoQuestoes(blocos));

		if (incluirGabarito && pedido.getTipoGabarito() != null)
		{
			sb.append("\n\\clearpage\n\n");
			sb.append(blocoGabarito(pedido, codigoAvaliacao, blocos));
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
			+ "\\usepackage{fancyhdr}\n"
			+ "\\usepackage{lastpage}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n\n"
			+ macros()
			+ "\\pagestyle{fancy}\n"
			+ "\\fancyhf{}\n"
			+ "\\renewcommand{\\headrulewidth}{0pt}\n"
			+ "\\fancyfoot[C]{\\small\\color{mutedtext}\\thepage\\ de\\ \\pageref{LastPage}}\n\n"
			+ "\\begin{document}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n\n";
	}

	private String cabecalhoAvaliacao(PedidoAvaliacao pedido, String codigoAvaliacao)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{tcolorbox}[enhanced, arc=4pt, boxrule=1pt,\n")
		  .append("  colback=white, colframe=darkblue,\n")
		  .append("  left=8pt, right=8pt, top=6pt, bottom=6pt]\n");

		if (pedido.getEscola() != null && !pedido.getEscola().isBlank())
			sb.append("  {\\small\\bfseries\\color{darkblue} ESCOLA:}\\enspace{\\small ")
			  .append(escapar(pedido.getEscola())).append("}\\\\\n");

		if (pedido.getNomeProfessor() != null && !pedido.getNomeProfessor().isBlank())
		{
			sb.append("  {\\small\\bfseries\\color{darkblue} PROFESSOR(A):}\\enspace{\\small ")
			  .append(escapar(pedido.getNomeProfessor())).append("}");

			if (pedido.getDataAvaliacao() != null)
				sb.append("\\hfill{\\small\\bfseries\\color{darkblue} DATA:}\\enspace{\\small ")
				  .append(pedido.getDataAvaliacao().format(FMT_DATA)).append("}");

			sb.append("\\\\\n");
		}
		else if (pedido.getDataAvaliacao() != null)
		{
			sb.append("  {\\small\\bfseries\\color{darkblue} DATA:}\\enspace{\\small ")
			  .append(pedido.getDataAvaliacao().format(FMT_DATA)).append("}\\\\\n");
		}

		sb.append("  \\vspace{4pt}\n")
		  .append("  {\\centering\\large\\bfseries\\color{darkblue} ")
		  .append(escapar(pedido.getNomeDocumento().getNome().toUpperCase()))
		  .append(": ").append(escapar(pedido.getTitulo()))
		  .append("\\par}\n")
		  .append("  \\hfill{\\footnotesize\\color{mutedtext} Cód.: \\texttt{")
		  .append(codigoAvaliacao).append("}}\n")
		  .append("\\end{tcolorbox}\n\n");

		return sb.toString();
	}

	private String linhaAluno()
	{
		return
			"\\vspace{4pt}\n"
			+ "\\noindent\\small\n"
			+ "\\begin{tabular}{p{9cm}p{3.5cm}p{3cm}}\n"
			+ "NOME:\\enspace\\hrulefill & TURMA:\\enspace\\hrulefill & NOTA:\\enspace\\hrulefill\n"
			+ "\\end{tabular}\n\n";
	}

	// ── Bloco de questões ─────────────────────────────────────────────

	private String blocoQuestoes(List<BlocoExercicio> blocos)
	{
		StringBuilder sb = new StringBuilder();
		int numInicial = 1;
		for (BlocoExercicio bloco : blocos)
		{
			if (bloco.isAlternativas())
				sb.append(blocoComAlternativas(bloco.getExercicios(), numInicial));
			else
				sb.append(blocoDiscursivo(bloco.getExercicios(), numInicial));
			numInicial += bloco.getExercicios().size();
		}
		return sb.toString();
	}

	private String blocoComAlternativas(List<Exercicio> exercicios, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(abrirGrade());
		boolean primeiro = true;
		for (int i = 0; i < exercicios.size(); i += 2)
		{
			if (!primeiro) sb.append("\\hline\n");
			Exercicio esq = exercicios.get(i);
			Exercicio dir = (i + 1 < exercicios.size()) ? exercicios.get(i + 1) : null;
			int numEsq = numInicial + i;
			int numDir = dir != null ? numInicial + i + 1 : 0;
			sb.append(linhaExercicio(numEsq, esq, numDir, dir));
			primeiro = false;
		}
		sb.append(fecharGrade());
		return sb.toString();
	}

	private String blocoDiscursivo(List<Exercicio> exercicios, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < exercicios.size(); i++)
		{
			int num = numInicial + i;
			sb.append("\\noindent\\numex{").append(String.format("%02d", num)).append("}")
			  .append("{\\small\\color{bodytext}").append(enunciado(num, exercicios.get(i))).append("}\n\n")
			  .append("\\vspace{3cm}\n\n");
		}
		return sb.toString();
	}

	// ── Bloco de gabarito ─────────────────────────────────────────────

	private String blocoGabarito(PedidoAvaliacao pedido, String codigoAvaliacao,
		List<BlocoExercicio> blocos)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{tcolorbox}[enhanced, arc=4pt, boxrule=1pt,\n")
		  .append("  colback=rulebg, colframe=darkblue,\n")
		  .append("  left=8pt, right=8pt, top=4pt, bottom=4pt]\n")
		  .append("  {\\bfseries\\color{darkblue} GABARITO}\\hfill")
		  .append("{\\footnotesize\\color{mutedtext}\\texttt{").append(codigoAvaliacao).append("}}\n")
		  .append("\\end{tcolorbox}\n\n");

		boolean comResolucao = pedido.getTipoGabarito() == TipoGabarito.COM_RESOLUCAO;

		int numInicial = 1;
		for (BlocoExercicio bloco : blocos)
		{
			if (bloco.isAlternativas() && !comResolucao)
			{
				sb.append(gabaritoCompacto(bloco.getExercicios(), numInicial));
			}
			else
			{
				sb.append(gabaritoComResolucao(bloco.getExercicios(), bloco.isAlternativas(), numInicial));
			}
			numInicial += bloco.getExercicios().size();
		}

		return sb.toString();
	}

	private String gabaritoCompacto(List<Exercicio> exercicios, int numInicial)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\vspace{4pt}\n\\noindent\\small\n");
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

	// ── Grades ────────────────────────────────────────────────────────

	private String abrirGrade()
	{
		return
			"\\noindent\n"
			+ "\\begin{tblr}{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"
			+ "  column{2} = {rightsep=0pt},\n"
			+ "  rows     = {ht=7cm, valign=t, rowsep=0pt},\n"
			+ "}\n";
	}

	private String fecharGrade() { return "\\end{tblr}\n"; }

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

	// ── Linhas de questão e gabarito ──────────────────────────────────

	private String linhaExercicio(int numEsq, Exercicio esq, int numDir, Exercicio dir)
	{
		String dirTex = dir != null ? macroExercicio(numDir, dir) : "";
		return macroExercicio(numEsq, esq) + " &\n" + dirTex + " \\\\\n";
	}

	private String macroExercicio(int num, Exercicio e)
	{
		String numStr = String.format("%02d", num);
		List<AlternativaExercicio> alts = e.getAlternativas();

		if (alts == null || alts.isEmpty())
			return "\\exd{" + numStr + "}{" + enunciado(num, e) + "}";

		String[] textos = alternativasTexto(alts);
		return "\\ex{" + numStr + "}{" + enunciado(num, e) + "}"
			+ "{" + textos[0] + "}"
			+ "{" + textos[1] + "}"
			+ "{" + textos[2] + "}"
			+ "{" + textos[3] + "}"
			+ "{" + textos[4] + "}";
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

	// ── Macros LaTeX ──────────────────────────────────────────────────

	private String macros()
	{
		return
			"\\newcommand{\\numex}[1]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1}\\end{tcolorbox}\\hspace{4pt}}\n"
			+ "\\newcommand{\\altline}[2]{%\n"
			+ "  \\def\\altph{---}%\n"
			+ "  \\def\\altarg{#2}%\n"
			+ "  \\ifx\\altarg\\altph\\else\n"
			+ "    \\noindent{\\bfseries\\color{darkblue}\\small(#1)}~{\\small#2}\\par\n"
			+ "  \\fi}\n"
			+ "\\newlength{\\cellheight}\n"
			+ "\\setlength{\\cellheight}{7cm}\n"
			+ "\\newcommand{\\alts}[5]{%\n"
			+ "  \\altline{A}{#1}%\n"
			+ "  \\altline{B}{#2}%\n"
			+ "  \\altline{C}{#3}%\n"
			+ "  \\altline{D}{#4}%\n"
			+ "  \\altline{E}{#5}\n"
			+ "  \\vspace{0.3cm}}\n"
			+ "\\newcommand{\\ex}[7]{%\n"
			+ "  \\parbox[t][\\cellheight][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#1}{\\small\\color{bodytext}#2}\\par\\vfill\n"
			+ "    \\alts{#3}{#4}{#5}{#6}{#7}}}\n"
			+ "\\newcommand{\\exd}[2]{%\n"
			+ "  \\parbox[t][\\cellheight][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#1}{\\small\\color{bodytext}#2}}}\n"
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

	// ── Helpers de texto ──────────────────────────────────────────────

	private String enunciado(int num, Exercicio e)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (ParagrafoExercicio p : e.getParagrafos())
		{
			String parte;
			if (p.isTipoImagem())
				parte = includegraphics(num, p);
			else if (p.getTexto() != null && !p.getTexto().isBlank())
				parte = escapar(p.getTexto());
			else
				continue;

			if (!primeiro) sb.append("\\par ");
			sb.append(parte);
			primeiro = false;
		}
		return sb.toString();
	}

	private String includegraphics(int num, ParagrafoExercicio p)
	{
		return "{\\centering\\includegraphics[width=0.6\\linewidth]{" + gravarImagem(num, p) + "}\\par}";
	}

	private String gravarImagem(int num, ParagrafoExercicio p)
	{
		String nome = String.format("img_%02d_%d.png", num, p.getOrdem());
		try
		{
			ImagemFile imf = p.getImagemFile();
			if (imf != null)
			{
				Blob blob = imf.getFile();
				if (blob != null)
				{
					byte[] bytes = blob.getBytes(1, (int) blob.length());
					Files.write(Path.of(nome), bytes);
				}
			}
		}
		catch (IOException | SQLException ex)
		{
			ex.printStackTrace();
		}
		return nome;
	}

	private String[] alternativasTexto(List<AlternativaExercicio> alts)
	{
		String[] textos = {"---", "---", "---", "---", "---"};
		for (int i = 0; i < Math.min(alts.size(), 5); i++)
			textos[i] = alts.get(i).getTexto() != null ? escapar(alts.get(i).getTexto()) : "---";
		return textos;
	}

	private String escapar(String s)
	{
		if (s == null) return "";
		return s.replaceAll("(?<!\\\\)%", "\\\\%")
		        .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	// ── Compilação e utilitários ──────────────────────────────────────

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
