package pdf.publicacao;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import bean.download.Diretorio;
import matematica.GeradorExercicio;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.exercicio.ParagrafoExercicio;
import modelo.exercicio.ParagrafoResolucao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.ImagemFile;
import pdf.util.Arquivo;
import util.CorAux;

/**
 * Geração do post vertical (Reel/TikTok) com layout repaginado: foto de fundo
 * vibrante (mantém a diversidade do repositório de 103 imagens) como moldura,
 * conteúdo sobre painel branco translúcido (legível sobre qualquer fundo),
 * chamada/gancho para reter atenção, alternativas em badges e resolução
 * destacada. Espelha a interface pública de {@link TikTok} (gerarPDFExercicio,
 * gerarPDF, convertPNG); a classe antiga permanece intacta.
 */
public class TikTok2
{
	ExercicioPadrao exercicio;
	String latex;
	Exercicio conta;
	Diretorio diretorio;
	ProgramacaoPost programacaoPost;

	public TikTok2(Diretorio diretorio)
	{
		super();
		this.diretorio = diretorio;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost)
	{
		this.exercicio = exercicio;
		this.programacaoPost = programacaoPost;
		gerarConta();

		diretorio.limparDiretorios();
		gerarImagem();

		caput();
		paginaExercicio();
		latex += "\\newpage\r\n";
		paginaResolucao();
		latex += "\\end{document}\r\n";

		Arquivo arq = new Arquivo(diretorio.getEnderecoTex());
		arq.escrever(latex);
		arq.finalizar();
	}

	private void gerarConta()
	{
		try
		{
			GeradorExercicio gerador = (GeradorExercicio) Class.forName(exercicio.getClasse())
				.getDeclaredConstructor().newInstance();
			conta = gerador.gerar();
		}
		catch(ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}

	// ───────────────────────── imagens (logo / fundo / enunciado) ─────────────

	private void gravarLogo()
	{
		File logo = new File(diretorio.getEnderecoLogo());

		if(!logo.exists())
		{
			File origem = new File(diretorio.getConfig().getEndereco() + programacaoPost.getConfigPost().getLogo().getEndImagem());
			try
			{
				Files.copy(origem.toPath(), logo.toPath());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void gravarBackgroundPadrao()
	{
		File background = new File(diretorio.getEnderecoBackground());

		if(!background.exists())
		{
			File origem = new File(diretorio.getConfig().getEndereco() + programacaoPost.getPadrao().getEndereco());
			try
			{
				Files.copy(origem.toPath(), background.toPath());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void gravarBackgroundEspecifico()
	{
		File background = new File(diretorio.getEnderecoBackground());

		if(!background.exists())
		{
			File origem = new File(diretorio.getConfig().getEndereco() + programacaoPost.getBackground().getEndImagem());
			try
			{
				Files.copy(origem.toPath(), background.toPath());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void gerarImagem()
	{
		gravarLogo();
		if(programacaoPost.isBasePadrao())
			gravarBackgroundPadrao();
		else
			gravarBackgroundEspecifico();

		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
			if(paragrafo.isTipoImagem())
				gravarImagemParagrafo(paragrafo);
	}

	private void gravarImagemParagrafo(ParagrafoExercicio paragrafo)
	{
		try
		{
			ImagemFile imagemFile = paragrafo.getImagemFile();
			Blob blob = imagemFile != null ? imagemFile.getFile() : null;
			if(blob != null)
			{
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				Files.write(new File(diretorio.getEnderecoImagens() + nomeImagem(paragrafo)).toPath(), bytes);
			}
		}
		catch(IOException | SQLException e)
		{
			e.printStackTrace();
		}
	}

	private String nomeImagem(ParagrafoExercicio paragrafo)
	{
		return "ex_" + paragrafo.getOrdem() + ".png";
	}

	// ───────────────────────────── preâmbulo ─────────────────────────────────

	public void caput()
	{
		String corFonte  = CorAux.convertHexPorc(ConfigPost.COR_FONTE);
		String corTitulo = CorAux.convertHexPorc(ConfigPost.COR_TITULO);
		String corNome   = CorAux.convertHexPorc(ConfigPost.COR_NOME);

		latex = "\\documentclass[12pt]{article}\r\n"
		+ "\\usepackage[utf8]{inputenc}\r\n"
		+ "\\usepackage[T1]{fontenc}\r\n"
		+ "\\usepackage{sansmathfonts}\r\n"
		+ "\\renewcommand*\\familydefault{\\sfdefault}\r\n"
		+ "\\usepackage{amsmath,amssymb,bm}\r\n"
		+ "\\usepackage{graphicx}\r\n"
		+ "\\usepackage{xcolor}\r\n"
		+ "\\usepackage{tikz}\r\n"
		+ "\\usetikzlibrary{calc,positioning}\r\n"
		+ "\\usepackage[paperwidth=259px,paperheight=460.5px,margin=0px]{geometry}\r\n"
		+ "\\pagestyle{empty}\r\n"
		+ "\r\n"
		+ "\\definecolor{iris}{rgb}{" + corTitulo + "}\r\n"
		+ "\\definecolor{cinza}{rgb}{" + corFonte + "}\r\n"
		+ "\\definecolor{nomecor}{rgb}{" + corNome + "}\r\n"
		+ "\\definecolor{laranja}{rgb}{0.87,0.48,0.25}\r\n"
		+ "\\definecolor{verde}{rgb}{0,0.54,0.44}\r\n"
		+ "\\definecolor{amarelo}{rgb}{0.96,0.66,0.13}\r\n"
		+ "\\definecolor{vermelho}{rgb}{0.86,0.27,0.27}\r\n"
		+ "\r\n"
		+ "\\tikzset{\r\n"
		+ "  nivel/.style={fill=" + corNivel() + ",text=white,rounded corners=4pt,inner xsep=7pt,inner ysep=3pt,font=\\bfseries\\footnotesize},\r\n"
		+ "  chip/.style={fill=iris,text=white,rounded corners=8pt,inner xsep=10pt,inner ysep=4pt,font=\\bfseries},\r\n"
		+ "  hook/.style={text=laranja,font=\\bfseries\\large,align=center},\r\n"
		+ "  altbadge/.style={circle,fill=iris,text=white,font=\\bfseries\\small,inner sep=0pt,minimum size=23px},\r\n"
		+ "  alttext/.style={text=cinza,anchor=west,font=\\bfseries},\r\n"
		+ "  cta/.style={fill=verde,text=white,rounded corners=10pt,inner xsep=14pt,inner ysep=6pt,font=\\bfseries},\r\n"
		+ "  site/.style={font=\\bfseries\\footnotesize,text=nomecor},\r\n"
		+ "}\r\n"
		+ "\\newcommand{\\painel}{%\r\n"
		+ "  \\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "  \\coordinate (PNW) at ([shift={(16px,-16px)}]current page.north west);\r\n"
		+ "  \\coordinate (PSE) at ([shift={(-16px,16px)}]current page.south east);\r\n"
		+ "  \\coordinate (PNE) at ([shift={(-16px,-16px)}]current page.north east);\r\n"
		+ "  \\coordinate (PN) at ([yshift=-16px]current page.north);\r\n"
		+ "  \\coordinate (PS) at ([yshift=16px]current page.south);\r\n"
		+ "  \\fill[white,opacity=0.82,rounded corners=18pt] (PNW) rectangle (PSE);\r\n"
		+ "}\r\n"
		+ "\\begin{document}\r\n\r\n";
	}

	// ───────────────────────────── página 1 ──────────────────────────────────

	public void paginaExercicio()
	{
		String enunciado = enunciado(true);

		latex += "\\begin{tikzpicture}[remember picture,overlay]\r\n"
		+ "\\painel\r\n"
		+ cabecalho(labelNivel())
		+ "\\node[chip,anchor=north] (chip) at ([yshift=-40px]PN){" + sizeChip() + exercicio.getAssunto().getNome() + "};\r\n"
		+ "\\node[hook,below=7px of chip] (hook){" + Ganchos.aleatorio() + "};\r\n"
		+ "\\node[anchor=north,align=center,text width=216px,text=iris,font=\\bfseries] (enun)"
		+ " at ([yshift=-104px]current page.north){" + fontsize(ptEnunciado(enunciado)) + "\r\n" + enunciado + "};\r\n";

		if(programacaoPost.isAlternativaReel())
			latex += alternativas();

		latex += "\\node[cta,anchor=south] at ([yshift=16px]PS){Comente sua resposta!};\r\n"
		+ "\\end{tikzpicture}\r\n";
	}

	// ───────────────────────────── página 2 ──────────────────────────────────

	public void paginaResolucao()
	{
		String enunciado = enunciado(false);
		String texto = getTextoResolucao();

		latex += "\\begin{tikzpicture}[remember picture,overlay]\r\n"
		+ "\\painel\r\n"
		+ cabecalho("RESOLUÇÃO")
		+ "\\node[chip,anchor=north] (chip) at ([yshift=-40px]PN){" + sizeChip() + exercicio.getAssunto().getNome() + "};\r\n"
		+ "\\node[anchor=north,align=center,text width=216px,text=cinza,font=\\bfseries] (enun)"
		+ " at ([yshift=-8px]chip.south){" + fontsize(Math.max(9, ptEnunciado(enunciado) - 4)) + "\r\n" + enunciado + "};\r\n"
		// Resolução num minipage (modo parágrafo normal), NÃO em node align=center: o
		// align=center do TikZ não respeita a profundidade das frações e cola as linhas;
		// o minipage espaça certo (igual à avaliação) e o \lineskip volta a funcionar.
		+ "\\node[anchor=north,inner sep=0] (res)"
		+ " at ([yshift=-16px]enun.south){\\begin{minipage}{216px}\\centering\\color{cinza}\\bfseries"
		+ fontsizeResolucao(ptResolucao(texto)) + "\r\n"
		+ espacamentoResolucao(ptResolucao(texto)) + "\r\n" + texto + "\\par\\end{minipage}};\r\n"
		+ resposta()
		+ "\\end{tikzpicture}\r\n";
	}

	/** Cabeçalho comum: logo + selo (nível ou "RESOLUÇÃO") + marca de teste. */
	private String cabecalho(String selo)
	{
		String s = "\\node[anchor=north west,inner sep=0] at ([shift={(12px,-12px)}]PNW){\\includegraphics[width=78px]{logo.png}};\r\n"
		+ "\\node[nivel,anchor=north east] at ([shift={(-12px,-14px)}]PNE){" + selo + "};\r\n";
		if(programacaoPost.isTeste())
			s += "\\node[anchor=north west,font=\\bfseries\\tiny,text=cinza] at ([shift={(14px,-46px)}]PNW){Imagem de Teste};\r\n";
		return s;
	}

	/**
	 * Alternativas como badges (círculo com a letra) + texto. Em grade 2x2
	 * quando há 4 curtas; senão em coluna única (acomoda alternativas longas
	 * ou algébricas e quantidades diferentes de 4).
	 */
	private String alternativas()
	{
		List<AlternativaExercicio> alternativas = conta.getAlternativas();
		if(alternativas.isEmpty())
			return "";

		// Enunciado com imagem é alto: alternativas em 2 colunas ancoradas abaixo
		// do enunciado (posição relativa) — evitam a sobreposição da imagem.
		if(temImagemEnunciado())
			return alternativasDuasColunas(alternativas);

		StringBuilder sb = new StringBuilder();

		if(alternativasCurtas())
		{
			String[] px = {"-66px", "44px", "-66px", "44px"};
			String[] py = {"-282px", "-282px", "-320px", "-320px"};
			for(int i = 0; i < 4; i++)
			{
				AlternativaExercicio alt = alternativas.get(i);
				sb.append("\\node[altbadge] (alt").append(i).append(") at ([shift={(").append(px[i]).append(",").append(py[i])
				  .append(")}]current page.north){").append(alt.getLetra()).append("};\r\n");
				sb.append("\\node[alttext] at (alt").append(i).append(".east){\\Large\\;").append(escapar(alt.getTexto())).append("};\r\n");
			}
		}
		else
		{
			int n = alternativas.size();
			// frações (\dfrac) ocupam altura dupla → mais espaçamento entre linhas
			boolean altas = alternativasComFracao();
			int step = altas ? (n <= 4 ? 46 : Math.max(34, 150 / n))
			                  : (n <= 4 ? 34 : Math.max(24, 130 / n));
			for(int i = 0; i < n; i++)
			{
				AlternativaExercicio alt = alternativas.get(i);
				int y = -236 - step * i;
				sb.append("\\node[altbadge] (alt").append(i).append(") at ([shift={(-92px,").append(y).append("px)}]current page.north){")
				  .append(alt.getLetra()).append("};\r\n");
				sb.append("\\node[alttext] at (alt").append(i).append(".east){\\large\\;").append(escapar(alt.getTexto())).append("};\r\n");
			}
		}
		return sb.toString();
	}

	/** Todas as 4 alternativas curtas o bastante para a grade 2x2. */
	private boolean alternativasCurtas()
	{
		List<AlternativaExercicio> alternativas = conta.getAlternativas();
		if(alternativas.size() != 4)
			return false;
		for(AlternativaExercicio alt : alternativas)
			if(escapar(alt.getTexto()).length() > 8)
				return false;
		return true;
	}

	/** Alguma alternativa contém fração (\\dfrac/\\frac) — linha mais alta. */
	private boolean alternativasComFracao()
	{
		for(AlternativaExercicio alt : conta.getAlternativas())
			if(alt.getTexto() != null && alt.getTexto().contains("frac"))
				return true;
		return false;
	}

	/** Enunciado contém pelo menos um parágrafo do tipo imagem. */
	private boolean temImagemEnunciado()
	{
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
			if(paragrafo.isTipoImagem())
				return true;
		return false;
	}

	/**
	 * Alternativas em 2 colunas ancoradas logo abaixo do enunciado (relativo).
	 * Duas colunas reduzem pela metade o nº de linhas — sobra altura para a
	 * imagem do enunciado — e a posição relativa garante que nunca se sobreponham.
	 */
	private String alternativasDuasColunas(List<AlternativaExercicio> alternativas)
	{
		StringBuilder sb = new StringBuilder();
		int n = alternativas.size();
		int stepRow = alternativasComFracao() ? 50 : 40;
		String[] colX = {"-98px", "6px"};
		for(int i = 0; i < n; i++)
		{
			AlternativaExercicio alt = alternativas.get(i);
			int y = -26 - stepRow * (i / 2);
			sb.append("\\node[altbadge] (alt").append(i).append(") at ([shift={(").append(colX[i % 2]).append(",").append(y)
			  .append("px)}]enun.south){").append(alt.getLetra()).append("};\r\n");
			sb.append("\\node[alttext] at (alt").append(i).append(".east){\\large\\;").append(escapar(alt.getTexto())).append("};\r\n");
		}
		return sb.toString();
	}

	/** Pílula verde com a alternativa correta (quando o post mostra alternativas). */
	private String resposta()
	{
		if(!programacaoPost.isAlternativaReel() || conta.correta() == null)
			return "";

		return "\\node[fill=verde,text=white,rounded corners=10pt,inner xsep=16pt,inner ysep=7pt,font=\\bfseries\\large,anchor=south]"
		+ " at ([yshift=14px]PS){Alternativa " + conta.correta().getLetra() + ":\\; " + escapar(conta.correta().getTexto()) + "\\;$\\checkmark$};\r\n";
	}

	// ───────────────────────── enunciado / resolução ─────────────────────────

	/**
	 * Enunciado: parágrafos de texto (com matemática inline) e imagens. Na página
	 * da resolução (primeiraPage=false) a imagem renderiza menor.
	 */
	private String enunciado(boolean primeiraPage)
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
		{
			if(paragrafo.isTipoImagem())
				sb.append("\\includegraphics[width=" + widthImagem(primeiraPage) + "]{"
					+ diretorio.getConfig().getImagens() + "/" + nomeImagem(paragrafo) + "}\r\n\r\n");
			else if(paragrafo.getTexto() != null && !paragrafo.getTexto().isBlank())
				sb.append(escapar(paragrafo.getTexto())).append("\r\n\r\n");
		}
		return sb.toString();
	}

	private String widthImagem(boolean primeiraPage)
	{
		if(!primeiraPage)
			return "110px";
		// Na página 1, encolhe a imagem quando há alternativas para sobrar espaço.
		return programacaoPost.isAlternativaReel() ? "130px" : "170px";
	}

	/** Junta os parágrafos de resolução, um por linha (\\ em modo texto). */
	private String getTextoResolucao()
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoResolucao paragrafo : conta.getResolucaoParagrafos())
		{
			String texto = paragrafo.getTexto();
			if(texto == null || texto.isBlank())
				continue;
			// frações de display ficam grandes (\dfrac), mas no EXPOENTE mantém \frac (pequena):
			// \dfrac num expoente fica enorme/feio (ex.: juros compostos (1+i)^{\frac{1}{12}}).
			texto = texto.replace("\\frac", "\\dfrac").replace("^{\\dfrac", "^{\\frac");
			if(sb.length() > 0)
				sb.append(" \\\\\r\n");
			sb.append(escapar(texto));
		}
		return sb.toString();
	}

	// ─────────────────────────── tamanhos / cores ────────────────────────────

	/** Corpo do enunciado na página 1 (protagonista): grande para enunciados
	 *  curtos, reduzindo para os longos de modo a não invadir as alternativas. */
	private int ptEnunciado(String texto)
	{
		int len = texto.length();
		if(len <= 30)  return 24;
		if(len <= 55)  return 20;
		if(len <= 90)  return 16;
		if(len <= 140) return 13;
		if(len <= 220) return 11;
		return 9;
	}

	/** Corpo da resolução na página 2 (foco), reduzindo conforme o volume. */
	private int ptResolucao(String texto)
	{
		int len = texto.length();
		if(len <= 120) return 15;
		if(len <= 250) return 13;
		if(len <= 450) return 11;
		if(len <= 700) return 10;
		return 9;
	}

	private String fontsize(int pt)
	{
		return "\\fontsize{" + pt + "}{" + (pt + 4) + "}\\selectfont";
	}

	/** Leading apertado (pt+2) para a resolução, igual à avaliação: faz o \lineskip
	 *  disparar nas linhas com \dfrac, evitando que a linha seguinte cole. */
	private String fontsizeResolucao(int pt)
	{
		return "\\fontsize{" + pt + "}{" + (pt + 2) + "}\\selectfont";
	}

	/** Espaçamento da resolução escalado pela fonte (mesma proporção da avaliação a 10pt:
	 *  lineskip 0,6·pt, lineskiplimit 0,2·pt, jot 0,8·pt) — funciona em qualquer tamanho. */
	private String espacamentoResolucao(int pt)
	{
		int lineskip = Math.round(pt * 0.6f);
		int limit = Math.round(pt * 0.2f);
		int jot = Math.round(pt * 0.8f);
		return "\\setlength{\\lineskip}{" + lineskip + "pt}\\setlength{\\lineskiplimit}{" + limit
			+ "pt}\\setlength{\\jot}{" + jot + "pt}";
	}

	/** Reduz a fonte do chip quando o nome do assunto é longo. */
	private String sizeChip()
	{
		int len = exercicio.getAssunto().getNome().length();
		if(len <= 22)
			return "";
		if(len <= 30)
			return "\\small ";
		return "\\footnotesize ";
	}

	/** Cor do selo de nível: Fácil=verde, Médio=amarelo, Difícil=vermelho. */
	private String corNivel()
	{
		if(exercicio.getNivel() == Nivel.Nivel1)
			return "verde";
		if(exercicio.getNivel() == Nivel.Nivel2)
			return "amarelo";
		return "vermelho";
	}

	/** Rótulo do selo de nível. */
	private String labelNivel()
	{
		if(exercicio.getNivel() == Nivel.Nivel1)
			return "NÍVEL FÁCIL";
		if(exercicio.getNivel() == Nivel.Nivel2)
			return "NÍVEL MÉDIO";
		return "NÍVEL DIFÍCIL";
	}

	/**
	 * Escapa caracteres que nunca aparecem "crus" de forma legítima no texto: "%"
	 * (comentário) e "$" (alterna math); converte "\\<dígito>" (erro de "/") em "/".
	 */
	private String escapar(String texto)
	{
		if(texto == null)
			return "";
		return texto.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		            .replaceAll("(?<!\\\\)%", "\\\\%")
		            .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	// ──────────────────────────── compilação ─────────────────────────────────

	public void gerarPDF()
	{
		ProcessBuilder pb;
		if(diretorio.getConfig().getSistemaOperacional() == SistemaOperacional.Linux)
			pb = new ProcessBuilder("sudo", "pdflatex", "-interaction=nonstopmode", diretorio.getConfig().getNome()).inheritIO()
			.directory(new File(diretorio.getEndereco()));
		else
			pb = new ProcessBuilder("pdflatex", "-interaction=nonstopmode", diretorio.getConfig().getNome()).inheritIO()
			.directory(new File(diretorio.getEndereco()));

		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(new File(diretorio.getEnderecoOutputLog())));

		Process process;
		try
		{
			process = pb.start();
			process.waitFor();
			process = pb.start();
			process.waitFor();
		}
		catch(IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void convertPNG()
	{
		ProcessBuilder pb;
		if(diretorio.getConfig().getSistemaOperacional() == SistemaOperacional.Linux)
			pb = new ProcessBuilder("sudo", "pdftocairo", "-png", "-r", "300", diretorio.getConfig().getNome() + ".pdf",
			diretorio.getConfig().getNome()).inheritIO().directory(new File(diretorio.getEndereco()));
		else
			pb = new ProcessBuilder("pdftocairo", "-png", "-r", "300", diretorio.getConfig().getNome() + ".pdf",
			diretorio.getConfig().getNome()).inheritIO().directory(new File(diretorio.getEndereco()));

		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(new File(diretorio.getEnderecoOutputLog())));

		Process process;
		try
		{
			process = pb.start();
			process.waitFor();
		}
		catch(IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
