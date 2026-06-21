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
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.ParagrafoExercicio;
import modelo.questao.ImagemFile;
import pdf.exercicio.LayoutLista;

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
	 *  resultado compartilhado entre gerarAvaliacao() e GeradorGabaritoPdfService.gerarGabaritosCombinados(). */
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

			blocos.add(new BlocoExercicio(item.getFormato(), padrao.getLayoutLista(), exercicios));
		}
		return blocos;
	}

	public byte[] gerarAvaliacao(PedidoAvaliacao pedido, List<BlocoExercicio> blocos, String codigoAvaliacao,
		Path pratiquejaStyDir, String xelatexExe, Path workDir, boolean incluirGabarito, byte[] logoEscolaBytes)
		throws IOException, InterruptedException
	{
		Files.createDirectories(workDir);
		copiarSty(pratiquejaStyDir, workDir);

		String nomeBase = "avaliacao_" + codigoAvaliacao.replace("-", "_").toLowerCase();
		String tex = construirTex(pedido, codigoAvaliacao, blocos, incluirGabarito, workDir, logoEscolaBytes);

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
		List<BlocoExercicio> blocos, boolean incluirGabarito, Path workDir, byte[] logoEscolaBytes)
	{
		String logoEscola = gravarLogoEscola(logoEscolaBytes, workDir);

		StringBuilder sb = new StringBuilder();
		sb.append(preambulo());
		sb.append("\\setsubject{").append(escapar(pedido.getTitulo())).append("}\n\n");
		sb.append(cabecalhoAvaliacao(pedido, codigoAvaliacao, logoEscola));
		sb.append(linhaAluno());
		sb.append("\\vspace{6pt}\n\n");
		sb.append(blocoQuestoes(blocos, workDir));

		if (incluirGabarito && pedido.getTipoGabarito() != null)
		{
			sb.append("\n\\clearpage\n\n");
			sb.append(blocoGabarito(pedido, codigoAvaliacao, blocos, logoEscola));
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
			// Rodapé herdado do pratiqueja.sty (PratiqueJá à esquerda, título no centro, filete);
			// só a direita é sobrescrita para a paginação no formato X/Y.
			+ "\\fancyfoot[R]{\\small\\color{mutedtext}\\thepage/\\pageref{LastPage}}\n\n"
			+ "\\begin{document}\n"
			+ "\\setstretch{1.2}\n"
			+ "\\color{bodytext}\n\n";
	}

	private String cabecalhoAvaliacao(PedidoAvaliacao pedido, String codigoAvaliacao, String logoEscola)
	{
		// Mesmo estilo do \listheader: caixa branca, filete azul à esquerda. No canto direito vai a
		// logo da escola (planos Profissional/Master) ou, na ausência dela, o wordmark "PratiqueJá".
		String titulo = escapar(pedido.getTitulo());

		String marca = logoEscola != null
			? "\\includegraphics[height=1.1cm,keepaspectratio]{" + logoEscola + "}"
			: "{\\color{mutedtext}\\small\\textbf{Pratique}\\textcolor{darkblue}{\\textbf{Já}}}";

		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{tcolorbox}[\n")
		  .append("  enhanced, arc=0pt, boxrule=0pt,\n")
		  .append("  colback=white, colframe=white,\n")
		  .append("  left=14pt, right=14pt, top=6pt, bottom=5pt,\n")
		  .append("  borderline west={5pt}{0pt}{darkblue},\n")
		  .append("  borderline south={0.8pt}{0pt}{sepcolor},\n")
		  .append("]\n")
		  .append("  \\noindent\n")
		  .append("  {\\color{titletext}\\fontsize{16}{20}\\selectfont\\bfseries ").append(titulo).append("}\\hfill\n")
		  .append("  ").append(marca).append("\\par\n")
		  .append("  \\vspace{3pt}\n")
		  .append("  {\\color{mutedtext}\\small ").append(linhaInfoCabecalho(pedido))
		  .append("\\hfill\\texttt{Cód.: ").append(codigoAvaliacao).append("}}\n")
		  .append("\\end{tcolorbox}\n\n");

		return sb.toString();
	}

	/** Linha secundária do cabeçalho: Escola · Prof.(a) · Data (só os preenchidos). */
	private String linhaInfoCabecalho(PedidoAvaliacao pedido)
	{
		List<String> partes = new ArrayList<>();
		if (pedido.getEscola() != null && !pedido.getEscola().isBlank())
			partes.add("{\\bfseries\\color{darkblue}Escola:}\\enspace " + escapar(pedido.getEscola()));
		if (pedido.getNomeProfessor() != null && !pedido.getNomeProfessor().isBlank())
			partes.add("{\\bfseries\\color{darkblue}Prof.(a):}\\enspace " + escapar(pedido.getNomeProfessor()));
		if (pedido.getDataAvaliacao() != null)
			partes.add("{\\bfseries\\color{darkblue}Data:}\\enspace " + pedido.getDataAvaliacao().format(FMT_DATA));
		return String.join("\\quad ", partes);
	}

	/** Grava os bytes da logo da escola no workDir e devolve o nome do arquivo, ou null se não houver
	 *  logo (os bytes já vêm prontos e validados por plano do MontadorPedidoAvaliacaoService). */
	private String gravarLogoEscola(byte[] logoBytes, Path workDir)
	{
		if (logoBytes == null || logoBytes.length == 0)
			return null;

		try
		{
			String nome = "logo_escola.png";
			Files.write(workDir.resolve(nome), logoBytes);
			return nome;
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private String linhaAluno()
	{
		return
			"\\vspace{4pt}\n"
			+ "\\noindent\\footnotesize\n"
			+ "\\begin{tabular}{p{9cm}p{3.5cm}p{3cm}}\n"
			+ "Nome:\\enspace\\hrulefill & Turma:\\enspace\\hrulefill & Nota:\\enspace\\hrulefill\n"
			+ "\\end{tabular}\n\n";
	}

	// ── Bloco de questões ─────────────────────────────────────────────

	/** Uma questão da prova: o exercício, se as alternativas devem ser exibidas (discursiva = não)
	 *  e o layout do seu bloco (define se a página é Padrão ou Espaçosa). */
	private static final class Questao
	{
		final Exercicio exercicio;
		final boolean mostrarAlternativas;
		final LayoutLista layout;

		Questao(Exercicio exercicio, boolean mostrarAlternativas, LayoutLista layout)
		{
			this.exercicio = exercicio;
			this.mostrarAlternativas = mostrarAlternativas;
			this.layout = layout;
		}
	}

	private String blocoQuestoes(List<BlocoExercicio> blocos, Path workDir)
	{
		// Todas as questões (alternativas e discursivas) numa grade de 2 colunas. Cada questão
		// carrega o layout do seu bloco; a distribuição é feita por página (ver paginar()).
		List<Questao> questoes = new ArrayList<>();
		for (BlocoExercicio bloco : blocos)
		{
			boolean mostrarAlternativas = bloco.isAlternativas();
			for (Exercicio e : bloco.getExercicios())
				questoes.add(new Questao(e, mostrarAlternativas, bloco.getLayout()));
		}

		if (questoes.isEmpty())
			return "";

		return blocoGrade(questoes, workDir);
	}

	/** Renderiza as questões página a página. O layout de cada página vem de paginar(): uma página
	 *  com ao menos uma questão espaçosa é toda espaçosa (4/página, linhas de 10,5cm); caso
	 *  contrário é padrão (6/página, linhas de 7cm). Numeração contínua e em ordem. */
	private String blocoGrade(List<Questao> questoes, Path workDir)
	{
		List<List<Questao>> paginas = paginar(questoes);

		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (int p = 0; p < paginas.size(); p++)
		{
			if (p > 0)
				sb.append("\\newpage\n");

			List<Questao> pagina = paginas.get(p);
			boolean espacoso = pagina.stream().anyMatch(q -> q.layout == LayoutLista.ESPACOSO);
			String altura = espacoso ? "10.5cm" : "7cm";

			sb.append(abrirGrade());
			for (int i = 0; i < pagina.size(); i += 2)
			{
				Questao esq = pagina.get(i);
				Questao dir = (i + 1 < pagina.size()) ? pagina.get(i + 1) : null;
				sb.append(linhaExercicio(altura, num + i, esq, dir != null ? num + i + 1 : 0, dir, workDir));
			}
			sb.append(fecharGrade());
			num += pagina.size();
		}
		return sb.toString();
	}

	/** Distribui as questões em páginas. Regra: uma página que contém ao menos uma questão espaçosa
	 *  comporta 4 questões (e renderiza tudo espaçoso); uma página só com questões padrão comporta 6.
	 *  Assim, uma questão padrão "órfã" no fim de um bloco desce para a página espaçosa seguinte. */
	private List<List<Questao>> paginar(List<Questao> questoes)
	{
		int capEspacoso = LayoutLista.ESPACOSO.exerciciosPorPagina;
		int capPadrao = LayoutLista.PADRAO.exerciciosPorPagina;

		List<List<Questao>> paginas = new ArrayList<>();
		List<Questao> atual = new ArrayList<>();
		boolean atualEspacoso = false;

		for (Questao q : questoes)
		{
			boolean questaoEspacosa = q.layout == LayoutLista.ESPACOSO;
			int capacidade = (atualEspacoso || questaoEspacosa) ? capEspacoso : capPadrao;

			if (!atual.isEmpty() && atual.size() >= capacidade)
			{
				paginas.add(atual);
				atual = new ArrayList<>();
				atualEspacoso = false;
			}

			atual.add(q);
			atualEspacoso = atualEspacoso || questaoEspacosa;
		}
		if (!atual.isEmpty())
			paginas.add(atual);

		return paginas;
	}

	// ── Bloco de gabarito ─────────────────────────────────────────────

	private String blocoGabarito(PedidoAvaliacao pedido, String codigoAvaliacao,
		List<BlocoExercicio> blocos, String logoEscola)
	{
		// Logo da escola (Profissional/Master) à direita do título GABARITO, quando houver.
		String logoTex = logoEscola != null
			? "\\raisebox{-0.25cm}{\\includegraphics[height=0.8cm,keepaspectratio]{" + logoEscola + "}}\\hspace{12pt}"
			: "";

		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{tcolorbox}[enhanced, arc=4pt, boxrule=1pt,\n")
		  .append("  colback=rulebg, colframe=darkblue,\n")
		  .append("  left=8pt, right=8pt, top=4pt, bottom=4pt]\n")
		  .append("  {\\bfseries\\color{darkblue} GABARITO}\\hfill")
		  .append(logoTex)
		  .append("{\\footnotesize\\color{mutedtext}\\texttt{").append(codigoAvaliacao).append("}}\n")
		  .append("\\end{tcolorbox}\n\n");

		boolean comResolucao = pedido.getTipoGabarito() == TipoGabarito.COM_RESOLUCAO;

		if (!comResolucao)
		{
			// Somente gabarito: todos os itens num único fluxo contínuo (quebra só no fim da linha).
			sb.append(gabaritoCompacto(blocos));
		}
		else
		{
			// Com resolução: passo a passo, por bloco.
			int numInicial = 1;
			for (BlocoExercicio bloco : blocos)
			{
				sb.append(gabaritoComResolucao(bloco.getExercicios(), bloco.isAlternativas(), numInicial));
				numInicial += bloco.getExercicios().size();
			}
		}

		return sb.toString();
	}

	private String gabaritoCompacto(List<BlocoExercicio> blocos)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\\vspace{4pt}\n\\noindent\\small\n");
		int num = 1;
		for (BlocoExercicio bloco : blocos)
		{
			boolean mostrarLetra = bloco.isAlternativas();
			for (Exercicio e : bloco.getExercicios())
				sb.append(String.format("\\resheader{%02d}{%s}\\enspace ", num++, respostaCompacta(e, mostrarLetra)));
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

	// ── Grades ────────────────────────────────────────────────────────

	private String abrirGrade()
	{
		// Sem "ht" fixo: a altura de cada linha vem do \parbox de altura própria de cada questão,
		// permitindo linhas Padrão (7cm) e Espaçoso (10,5cm) na mesma grade/página.
		return
			"\\noindent\n"
			+ "\\begin{tblr}{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"
			+ "  column{2} = {rightsep=0pt},\n"
			+ "  rows     = {valign=t, rowsep=0pt},\n"
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

	private String linhaExercicio(String altura, int numEsq, Questao esq, int numDir, Questao dir, Path workDir)
	{
		String esqTex = macroExercicio(altura, numEsq, esq, workDir);
		String dirTex = dir != null ? macroExercicio(altura, numDir, dir, workDir) : "";
		return esqTex + " &\n" + dirTex + " \\\\\n";
	}

	private String macroExercicio(String altura, int num, Questao q, Path workDir)
	{
		String numStr = String.format("%02d", num);
		Exercicio e = q.exercicio;
		List<AlternativaExercicio> alts = e.getAlternativas();

		// Discursiva (ou exercício sem alternativas): só o enunciado, sem as opções (\exd)
		if (!q.mostrarAlternativas || alts == null || alts.isEmpty())
			return "\\exd{" + altura + "}{" + numStr + "}{" + enunciado(num, e, workDir) + "}";

		String[] textos = alternativasTexto(alts);
		return "\\ex{" + altura + "}{" + numStr + "}{" + enunciado(num, e, workDir) + "}"
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
			+ "\\newcommand{\\alts}[5]{%\n"
			+ "  \\altline{A}{#1}%\n"
			+ "  \\altline{B}{#2}%\n"
			+ "  \\altline{C}{#3}%\n"
			+ "  \\altline{D}{#4}%\n"
			+ "  \\altline{E}{#5}\n"
			+ "  \\vspace{0.3cm}}\n"
			+ "\\newcommand{\\ex}[8]{%\n"
			+ "  \\parbox[t][#1][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#2}{\\small\\color{bodytext}#3}\\par\\vfill\n"
			+ "    \\alts{#4}{#5}{#6}{#7}{#8}}}\n"
			+ "\\newcommand{\\exd}[3]{%\n"
			+ "  \\parbox[t][#1][t]{\\linewidth}{%\n"
			+ "    \\setlength{\\parskip}{6pt}%\n"
			+ "    \\noindent\\numex{#2}{\\small\\color{bodytext}#3}}}\n"
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

	// ── Helpers de texto ──────────────────────────────────────────────

	private String enunciado(int num, Exercicio e, Path workDir)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for (ParagrafoExercicio p : e.getParagrafos())
		{
			String parte;
			if (p.isTipoImagem())
				parte = includegraphics(num, p, workDir);
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

	private String includegraphics(int num, ParagrafoExercicio p, Path workDir)
	{
		String nome = gravarImagem(num, p, workDir);
		if (nome == null)
			return "{\\itshape\\color{mutedtext}[imagem indisponível]}";
		return "{\\centering\\includegraphics[width=0.6\\linewidth]{" + nome + "}\\par}";
	}

	/** Grava a imagem do parágrafo no diretório de trabalho do xelatex (onde ele a procura).
	 *  Retorna o nome do arquivo se gravou, ou null se a imagem estiver ausente/ilegível. */
	private String gravarImagem(int num, ParagrafoExercicio p, Path workDir)
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
					if (bytes != null && bytes.length > 0)
					{
						Files.write(workDir.resolve(nome), bytes);
						return nome;
					}
				}
			}
		}
		catch (IOException | SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
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
