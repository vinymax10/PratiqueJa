package pdf.exercicio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ParagrafoExercicio;
import modelo.questao.ImagemFile;

/**
 * Gera um PDF de lista de exercícios (20 questões, 2 colunas) a partir de
 * qualquer {@link GeradorExercicio} concreto.
 *
 * <p>Uso típico a partir de um bean JSF:
 * <pre>{@code
 * byte[] bytes = new GeradorListaPDF.Builder()
 *     .gerador(padrao.getClasse())        // nome da classe do gerador
 *     .titulo(padrao.getAssunto().getNome())
 *     .subtitulo("Nível " + padrao.getNivelRomano())
 *     .categoria("Matemática · " + modulo)
 *     .pratiquejaStyDir(Path.of("C:/caminho/tex-new"))
 *     .premium(usuario.isPremium())
 *     .build()
 *     .gerarBytes(Path.of(latexWorkDir));
 * }</pre>
 */
public class GeradorListaPDF
{
	private final Class<? extends GeradorExercicio> geradorClass;
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
	private Path workDir;   // preenchido em gerarBytes() antes de construirTex()
	private Path texGerado; // caminho do .tex gerado (preservado se manterFonte)

	private GeradorListaPDF(Builder b)
	{
		this.geradorClass     = b.geradorClass;
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
	}

	// ── API pública ───────────────────────────────────────────────

	/**
	 * Gera o PDF e retorna os bytes prontos para StreamedContent no JSF.
	 * Copia pratiqueja.sty para o workDir, compila e limpa ao final.
	 *
	 * @param workDir diretório de trabalho do LaTeX (já existente)
	 * @return bytes do PDF gerado
	 */
	public byte[] gerarBytes(Path workDir) throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(workDir);
		this.workDir = workDir;

		List<Exercicio> exercicios = gerarExercicios();
		String nomeBase = nomeArquivo();
		Path texFile = workDir.resolve(nomeBase + ".tex");
		Files.writeString(texFile, construirTex(exercicios), StandardCharsets.UTF_8);
		this.texGerado = texFile;

		compilar(texFile);

		byte[] bytes = Files.readAllBytes(workDir.resolve(nomeBase + ".pdf"));
		limparAuxiliares(workDir, nomeBase);
		return bytes;
	}

	/** Copia pratiqueja.sty para o diretório de trabalho (se configurado). */
	private void copiarSty(Path workDir) throws IOException
	{
		if (pratiquejaStyDir == null) return;
		Path origem = pratiquejaStyDir.resolve("pratiqueja.sty");
		if (Files.exists(origem))
			Files.copy(origem, workDir.resolve("pratiqueja.sty"), StandardCopyOption.REPLACE_EXISTING);
	}

	/** Remove arquivos auxiliares gerados pelo LaTeX, mantém apenas o PDF (e o .tex se manterFonte). */
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

	/** Caminho do .tex gerado na última chamada de gerarBytes (preservado se manterFonte=true). */
	public Path getTexGerado()
	{
		return texGerado;
	}

	// ── Geração dos exercícios ────────────────────────────────────

	private List<Exercicio> gerarExercicios()
	{
		List<Exercicio> lista = new ArrayList<>();
		for (int i = 0; i < layout.total(); i++)
		{
			try
			{
				GeradorExercicio g = geradorClass.getDeclaredConstructor().newInstance();
				lista.add(g.gerar());
			}
			catch (ReflectiveOperationException e)
			{
				throw new IllegalStateException(
					"Não foi possível instanciar " + geradorClass.getSimpleName(), e);
			}
		}
		return lista;
	}

	// ── Montagem do .tex ─────────────────────────────────────────

	private String construirTex(List<Exercicio> exercicios)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(cabecalho());

		// Páginas de exercícios — configuradas pelo LayoutLista
		int cursor = 0;
		for (int pagina = 0; pagina < layout.paginas; pagina++)
		{
			if (pagina > 0)
				sb.append("\n\\newpage\n\n");

			sb.append(paginaHeader());

			int linhas = layout.linhasPorPagina();
			sb.append(abrirGrade(layout.alturaLinha));
			for (int linha = 0; linha < linhas; linha++)
			{
				int numEsq = cursor + 1;
				int numDir = cursor + 2;
				sb.append(linhaExercicio(numEsq, exercicios.get(cursor),
				                         numDir, exercicios.get(cursor + 1)));
				cursor += 2;
			}
			sb.append(fecharGrade());
		}

		// Gabarito (somente premium)
		if (premium)
		{
			sb.append("\n\\clearpage\n\n");
			sb.append(cabecalhoGabarito());
			sb.append(abrirGradeGabarito());
			for (int i = 0; i < layout.total(); i += 2)
				sb.append(linhaGabarito(i + 1, exercicios.get(i), i + 2, exercicios.get(i + 1)));
			sb.append(fecharGradeGabarito());
		}

		sb.append("\n\\end{document}\n");
		return sb.toString();
	}

	// ── Cabeçalho do documento ────────────────────────────────────

	private String cabecalho()
	{
		return
			"% Gerado automaticamente por GeradorListaPDF — não editar manualmente\n"
			+ "\\documentclass[12pt]{article}\n"
			+ "\\usepackage{pratiqueja}\n"
			+ "\\usepackage{tabularray}\n"
			+ "\\usepackage{cancel}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n\n"
			+ macros()
			+ "\\setsubject{" + titulo + " · " + subtitulo + "}\n\n"
			+ "\\begin{document}\n"
			+ "\\pagenumbering{arabic}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n\n";
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

			// altura da célula — igual ao ht da linha; usada pelo \ex para o parbox com \vfill
			+ "\\newlength{\\cellheight}\n"
			+ "\\setlength{\\cellheight}{" + layout.alturaLinha + "}\n"

			// alternativas em coluna — as "---" são automaticamente suprimidas
			+ "\\newcommand{\\alts}[5]{%\n"
			+ "  \\altline{A}{#1}%\n"
			+ "  \\altline{B}{#2}%\n"
			+ "  \\altline{C}{#3}%\n"
			+ "  \\altline{D}{#4}%\n"
			+ "  \\altline{E}{#5}\n"
			+ "  \\vspace{0.3cm}}\n"

			// exercício com alternativas: parbox de altura fixa + \vfill empurra alternativas ao fundo
			+ "\\newcommand{\\ex}[7]{%\n"
			+ "  \\parbox[t][\\cellheight][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#1}{\\small\\color{bodytext}#2}\\par\\vfill\n"
			+ "    \\alts{#3}{#4}{#5}{#6}{#7}}}\n"

			// exercício com imagem: enunciado + imagem no topo (direita); alternativas empurradas ao rodapé
			// #1 num · #2 enunciado (texto) · #3 imagem · #4-#8 alternativas
			+ "\\newcommand{\\exImg}[8]{%\n"
			+ "  \\parbox[t][\\cellheight][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#1}{\\small\\color{bodytext}#2}\\par\n"
			+ "    {\\centering #3\\par}%\n"
			+ "    \\vfill\n"
			+ "    \\alts{#4}{#5}{#6}{#7}{#8}}}\n"

			// exercício discursivo (sem alternativas) — parbox de altura fixa para alinhar ao topo da célula
			+ "\\newcommand{\\exd}[2]{%\n"
			+ "  \\parbox[t][\\cellheight][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#1}{\\small\\color{bodytext}#2}}}\n"

			// card combinado para gabarito: [01 · B] — número azul e letra verde em uma caixa
			+ "\\newcommand{\\resheader}[2]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1"
			+ "\\hspace{5pt}{\\color{mutedtext}\\tiny\\textbullet}\\hspace{5pt}"
			+ "\\color{vegreen}#2}\\end{tcolorbox}\\hspace{4pt}}\n"

			// gabarito com alternativas
			// \par dentro do grupo: garante que \baselineskip=12pt e \lineskip=6pt
			// estejam ativos quando o TeX insere o glue entre linhas (ao finalizar o parágrafo)
			+ "\\newcommand{\\res}[3]{%\n"
			+ "  \\noindent\\resheader{#1}{#2}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#3\\par}"
			+ "\\vspace{0.4cm}}\n"

			// gabarito discursivo
			+ "\\newcommand{\\resd}[2]{%\n"
			+ "  \\noindent\\numex{#1}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#2\\par}"
			+ "\\vspace{0.4cm}}\n\n";
	}

	private String campoAluno()
	{
		return
			"\\vspace{4pt}\n"
			+ "\\noindent\\small\n"
			+ "\\begin{tabular}{p{8.5cm}p{4.5cm}p{2.5cm}}\n"
			+ "Nome:\\enspace\\hrulefill & Turma:\\enspace\\hrulefill & Data:\\enspace\\hrulefill\n"
			+ "\\end{tabular}\n\n"
			+ "\\vspace{3pt}\n"
			+ "{\\small\\color{mutedtext}\\itshape " + instrucao + "}\n"
			+ "\\vspace{4pt}\n\n";
	}

	// ── Grades de exercícios ─────────────────────────────────────

	private String abrirGrade(String alturaLinha)
	{
		return
			"\\noindent\n"
			+ "\\begin{tblr}{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"    // borda esquerda flush com cabeçalho/rodapé
			+ "  column{2} = {rightsep=0pt},\n"   // borda direita flush
			+ "  rows     = {ht=" + alturaLinha + ", valign=t, rowsep=0pt},\n"
			+ "}\n";
	}

	private String fecharGrade()
	{
		return "\\end{tblr}\n";
	}

	// ── Linha de exercício (2 questões lado a lado) ───────────────

	private String linhaExercicio(int numEsq, Exercicio esq, int numDir, Exercicio dir)
	{
		return macroExercicio(numEsq, esq) + " &\n" + macroExercicio(numDir, dir) + " \\\\\n";
	}

	private String macroExercicio(int num, Exercicio e)
	{
		String numStr = String.format("%02d", num);
		List<AlternativaExercicio> alts = e.getAlternativas();

		// Discursivo (sem alternativas): imagem segue inline no enunciado
		if (!comAlternativas || alts.isEmpty())
			return "\\exd{" + numStr + "}{" + enunciado(num, e) + "}";

		String[] textos = alternativasTexto(alts);
		String imagens = imagensCmd(num, e);

		// Com imagem: alternativas à esquerda, imagem à direita (lado a lado)
		if (!imagens.isEmpty())
			return "\\exImg{" + numStr + "}{" + enunciadoSemImagem(e) + "}{" + imagens + "}"
				+ "{" + textos[0] + "}"
				+ "{" + textos[1] + "}"
				+ "{" + textos[2] + "}"
				+ "{" + textos[3] + "}"
				+ "{" + textos[4] + "}";

		// Sem imagem: layout padrão (alternativas empilhadas ao fundo)
		return "\\ex{" + numStr + "}{" + enunciado(num, e) + "}"
			+ "{" + textos[0] + "}"
			+ "{" + textos[1] + "}"
			+ "{" + textos[2] + "}"
			+ "{" + textos[3] + "}"
			+ "{" + textos[4] + "}";
	}

	// ── Grade do gabarito ─────────────────────────────────────────

	private String cabecalhoGabarito()
	{
		// Mesma primeira linha das páginas de exercício (\listheader); segunda linha identifica o gabarito.
		return "\\listheader{" + titulo + "}{" + subtitulo + "}{" + categoria + "}"
			+ "{Gabarito — Resolução comentada}\n\n";
	}

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
			+ "  column{1} = {leftsep=0pt},\n"    // borda esquerda flush com cabeçalho/rodapé
			+ "  column{2} = {rightsep=0pt},\n"   // borda direita flush
			+ "  rows     = {valign=t, rowsep=0pt},\n"   // sem ht fixo: cada linha segue o tamanho da resolução
			+ "}\n";
	}

	private String fecharGradeGabarito()
	{
		return "\\end{longtblr}\n";
	}

	private String linhaGabarito(int numEsq, Exercicio esq, int numDir, Exercicio dir)
	{
		return macroGabarito(numEsq, esq) + " &\n" + macroGabarito(numDir, dir) + " \\\\\n";
	}

	private String macroGabarito(int num, Exercicio e)
	{
		String numStr = String.format("%02d", num);
		String resolucao = e.getResolucao() != null ? escaparTexto(e.getResolucao()) : "";
		AlternativaExercicio correta = comAlternativas ? e.correta() : null;

		if (correta == null)
			return "\\resd{" + numStr + "}{" + resolucao + "}";

		return "\\res{" + numStr + "}{" + correta.getLetra() + "}{" + resolucao + "}";
	}

	// ── Helpers ───────────────────────────────────────────────────

	/** Monta o enunciado: parágrafos de texto separados por \par; imagens via \includegraphics. */
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
				parte = escaparTexto(p.getTexto());
			else
				continue;

			if (!primeiro) sb.append("\\par ");
			sb.append(parte);
			primeiro = false;
		}
		return sb.toString();
	}

	/** Grava a imagem do parágrafo em workDir e retorna o comando \includegraphics centralizado (uso discursivo). */
	private String includegraphics(int num, ParagrafoExercicio p)
	{
		return "{\\centering\\includegraphics[width=0.6\\linewidth]{" + gravarImagem(num, p) + "}\\par}";
	}

	/** Grava a imagem do parágrafo em workDir e retorna apenas o nome do arquivo. */
	private String gravarImagem(int num, ParagrafoExercicio p)
	{
		String nome = String.format("img_%02d_%d.png", num, p.getOrdem());
		if (workDir != null)
		{
			try
			{
				ImagemFile imf = p.getImagemFile();
				Blob blob = imf.getFile();
				if (blob != null)
				{
					byte[] bytes = blob.getBytes(1, (int) blob.length());
					Files.write(workDir.resolve(nome), bytes);
				}
			}
			catch (IOException | SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return nome;
	}

	/**
	 * Escapa caracteres especiais do LaTeX que nunca aparecem de forma legítima
	 * "crua" no texto do enunciado: "%" (comentário) e "$" (alterna modo math).
	 * Não toca em ocorrências já escapadas (precedidas por "\"). Demais especiais
	 * (_, ^, {, }, &) são preservados pois fazem parte da matemática em \(...\).
	 */
	private String escaparTexto(String s)
	{
		if (s == null) return "";
		return s.replaceAll("(?<!\\\\)%", "\\\\%")
		        .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	/** Enunciado apenas com os parágrafos de texto (sem imagens), separados por \par. */
	private String enunciadoSemImagem(Exercicio e)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (ParagrafoExercicio p : e.getParagrafos())
		{
			if (p.isTipoImagem()) continue;
			if (p.getTexto() == null || p.getTexto().isBlank()) continue;

			if (!primeiro) sb.append("\\par ");
			sb.append(escaparTexto(p.getTexto()));
			primeiro = false;
		}
		return sb.toString();
	}

	/** Comandos \includegraphics de todas as imagens do exercício (no topo, à direita); "" se não houver. */
	private String imagensCmd(int num, Exercicio e)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeira = true;
		for (ParagrafoExercicio p : e.getParagrafos())
		{
			if (!p.isTipoImagem()) continue;

			if (!primeira) sb.append("\\par\\vspace{4pt}");
			sb.append("\\includegraphics[width=0.8\\linewidth]{").append(gravarImagem(num, p)).append("}");
			primeira = false;
		}
		return sb.toString();
	}

	/** Retorna sempre um array de 5 textos; preenche com "---" se houver menos de 5. */
	private String[] alternativasTexto(List<AlternativaExercicio> alts)
	{
		String[] textos = {"---", "---", "---", "---", "---"};
		for (int i = 0; i < Math.min(alts.size(), 5); i++)
			textos[i] = alts.get(i).getTexto() != null ? escaparTexto(alts.get(i).getTexto()) : "---";
		return textos;
	}

	/** Nome do arquivo: remove espaços e acentos simples. */
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

	/** Compila o .tex com XeLaTeX. Duas passagens garantem referências cruzadas. */
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

	// ── Entrypoint de teste ──────────────────────────────────────

	/**
	 * Gera um PDF de teste diretamente, sem servidor.
	 * Edite as constantes abaixo para trocar o gerador ou o assunto.
	 * Execute pelo IDE: botão direito → Run As → Java Application.
	 */
	public static void main(String[] args) throws Exception
	{
		final String GERADOR   = "matematica.intermediario.funcaoafim.FuncaoAfimNivel3";
		final String TITULO    = "Função Afim";
		final String SUBTITULO = "Nível III";
		final String CATEGORIA = "Matemática · Intermediário";
		final String INSTRUCAO = "Resolva as questões abaixo mostrando o desenvolvimento completo.";
		final boolean PREMIUM  = true;

		final Path STY_DIR    = Path.of("C:/Users/maximovrm/git/PratiqueJa/PratiqueJa/tex-new");
		final String XELATEX  = "C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe";
		final Path WORK_DIR   = STY_DIR.resolve("listas/temp_main");
		final Path OUT_FILE   = STY_DIR.resolve("listas/lista_real.pdf");

		GeradorListaPDF gerador = new Builder()
			.gerador(GERADOR)
			.titulo(TITULO)
			.subtitulo(SUBTITULO)
			.categoria(CATEGORIA)
			.instrucao(INSTRUCAO)
			.pratiquejaStyDir(STY_DIR)
			.xelatexExe(XELATEX)
			.premium(PREMIUM)
			.manterFonte(true)
			.layout(LayoutLista.ESPACOSO)   // assuntos com imagem: 4/página, célula alta
			.build();

		byte[] bytes = gerador.gerarBytes(WORK_DIR);

		Files.write(OUT_FILE, bytes);

		// Copia o .tex para o lado do PDF, como lista_real.tex
		Path texDestino = OUT_FILE.resolveSibling("lista_real.tex");
		Files.copy(gerador.getTexGerado(), texDestino, StandardCopyOption.REPLACE_EXISTING);

		System.out.println("PDF gerado: " + OUT_FILE.toAbsolutePath());
		System.out.println("TeX gerado: " + texDestino.toAbsolutePath());
	}

	// ── Builder ───────────────────────────────────────────────────

	public static class Builder
	{
		private Class<? extends GeradorExercicio> geradorClass;
		private String titulo          = "Lista de Exercícios";
		private String subtitulo       = "Nível 1";
		private String categoria       = "Matemática";
		private String instrucao       = "Marque a alternativa correta e mostre o desenvolvimento.";
		private boolean premium        = false;
		private Path pratiquejaStyDir  = null;
		private String xelatexExe      = "xelatex";
		private LayoutLista layout     = LayoutLista.PADRAO;
		private boolean manterFonte    = false;
		private boolean comAlternativas = false;

		/** Classe concreta do gerador (ex.: AdicaoNaturalNivel1.class). */
		public Builder gerador(Class<? extends GeradorExercicio> c)
		{
			geradorClass = c;
			return this;
		}

		/**
		 * Nome completo da classe do gerador — vem de {@code ExercicioPadrao.getClasse()}.
		 * Ex.: {@code "matematica.avancado.combinatoria.Combinatoria1"}.
		 */
		@SuppressWarnings("unchecked")
		public Builder gerador(String classeNome)
		{
			try
			{
				geradorClass = (Class<? extends GeradorExercicio>) Class.forName(classeNome);
			}
			catch (ClassNotFoundException e)
			{
				throw new IllegalArgumentException("Classe de gerador não encontrada: " + classeNome, e);
			}
			return this;
		}

		public Builder titulo(String v)             { titulo           = v; return this; }
		public Builder subtitulo(String v)          { subtitulo        = v; return this; }
		public Builder categoria(String v)          { categoria        = v; return this; }
		public Builder instrucao(String v)          { instrucao        = v; return this; }
		public Builder premium(boolean v)           { premium          = v; return this; }
		public Builder layout(LayoutLista v)        { layout           = v; return this; }
		public Builder comAlternativas(boolean v)   { comAlternativas  = v; return this; }

		/** Mantém o arquivo .tex no diretório de trabalho após gerar o PDF (útil para depuração). */
		public Builder manterFonte(boolean v)     { manterFonte     = v; return this; }

		/** Diretório onde está pratiqueja.sty (normalmente o caminho de tex-new/). */
		public Builder pratiquejaStyDir(Path v)   { pratiquejaStyDir = v; return this; }

		/** Caminho completo do executável xelatex (necessário quando o PATH do servidor não o inclui). */
		public Builder xelatexExe(String v)       { xelatexExe = v; return this; }

		public GeradorListaPDF build()
		{
			if (geradorClass == null)
				throw new IllegalStateException("Informe o gerador com .gerador(...)");
			return new GeradorListaPDF(this);
		}
	}
}
