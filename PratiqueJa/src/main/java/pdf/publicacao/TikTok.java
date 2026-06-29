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
import modelo.exercicio.ParagrafoExercicio;
import modelo.exercicio.ParagrafoResolucao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.ImagemFile;
import pdf.util.Arquivo;
import util.CorAux;

public class TikTok
{
	ExercicioPadrao exercicio;
	String latex;
	Exercicio conta;
	Diretorio diretorio;
	ProgramacaoPost programacaoPost;

	public TikTok(Diretorio diretorio)
	{
		super();
		this.diretorio = diretorio;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost)
	{
		this.exercicio = exercicio;
		this.programacaoPost=programacaoPost;
		gerarConta();

		diretorio.limparDiretorios();
		gerarImagem();
		caput();

		cabecalho();
		exercicio();
		rodape(false);

		latex += "\\newpage \r\n";

		cabecalho();
		resolucao();
		rodape(true);

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
	        File origem = new File(diretorio.getConfig().getEndereco() +programacaoPost.getBackground().getEndImagem());
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

		// Imagens do enunciado (parágrafos do tipo imagem) gravadas na pasta de imagens.
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

	public void cabecalho()
	{
		latex+="\\includegraphics[width=100px]{logo.png}\r\n";
		if(programacaoPost.isTeste())
			latex+="\\textcolor{black!20}{\\normalsize ~~\\textbf{Imagem de Teste}}";
	}

	public void caput()
	{
		latex = "\\documentclass[12pt,twoside]{article}\r\n"
		+ "\r\n" + "\\usepackage[english]{babel}\r\n"
		+ "\\usepackage[utf8]{inputenc}\r\n"
		+ "\\usepackage{sansmathfonts}\r\n"
		+ "\\usepackage[T1]{fontenc}\r\n"
		+ "\\renewcommand*\\familydefault{\\sfdefault} %% Only if the base font of the document is to be sans serif\r\n"
		+ "%\\usepackage{tabularx}\r\n"
		+ "\\usepackage{dashrule}\r\n"
		+ "\\usepackage{tikz}\r\n"
		+ "\\usepackage{tabularray}\r\n"
		+ "\\usepackage[export]{adjustbox}\r\n"
		+ "%\\captionsetup[longtable]{skip=30pt}\r\n"
		+ "\\usepackage{arydshln}\r\n"
		+ "\\usepackage{ctable}\r\n"
		+ "\\usepackage{colortbl}\r\n"
		+ "\\usepackage{multicol,lipsum}\r\n"
		+ "\\usepackage{amsmath,breqn}\r\n"
		+ "\\usepackage{amssymb,amsthm,amsmath,dsfont,mathrsfs}\r\n"
		+ "\\usepackage[colorinlistoftodos]{todonotes}\r\n"
		+ "\\usepackage{geometry}\r\n"
		+ "\\geometry{\r\n"
		+ "left=25px,right=25px,bottom=20px,top=25px,\r\n"
		+ "paperwidth=259px,\r\n"
		+ "paperheight=460.5px\r\n"
		+ "}\r\n"
		+ "\\usepackage{background}\r\n"
		+"\\backgroundsetup{scale=1,opacity="+ConfigPost.TRANSPARENCIA+",angle=0,contents={\\includegraphics[width=\\paperwidth]{background.png}}}\r\n"
		+ "\\usepackage{natbib}\r\n"
		+ "\\usepackage{soul}\r\n"
		+ "\\usepackage{setspace}\r\n"
		+ "\\usepackage{hyperref}\r\n"
		+ "\\usepackage{xcolor}\r\n"
		+ "\\usepackage{cancel}\r\n"
		+ "\\color{darkgray}\r\n"
		+ "\\usepackage{graphicx}\r\n"
		+ "\\usepackage{fancyhdr}\r\n"
		+ "\\pagestyle{fancy}\r\n"
		+ "\\setlength{\\fboxsep}{0pt}\r\n"
		+ "\\setlength{\\fboxrule}{1pt}\r\n"
		+ "\\renewcommand{\\headrulewidth}{0pt}\r\n"
		+ "\r\n"
		+ "\r\n"
		+ "\\newcommand{\\T}[1]{\\textbf{\\large #1} \\newline}\r\n"
		+ "\r\n"
		+ "\\newcommand{\\TM}[1]{ \\textbf{ \\newline #1}  }\r\n"
		+ "\\setlength\\columnsep{30pt}\r\n"
		+ "\r\n"
		 +"\\definecolor{bluebell}{rgb}{0.25, 0.35, 0.89}\r\n"
		 +"\\definecolor{laranja}{rgb}{0.87, 0.48, 0.25}\r\n"
		 +"\\definecolor{verde}{rgb}{0, 0.54, 0.44}\r\n"
		 +"\\definecolor{babypink}{rgb}{1, 0.42, 0.52} \r\n"
		 +"\\definecolor{cinza}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_FONTE)+"} \r\n"
		 +"\\definecolor{iris}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_TITULO)+"} \r\n"
		 +"\\definecolor{babyblue}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_NOME)+"} \r\n"
		+ "\r\n"
		+ "\\color{cinza} \r\n \r\n"
		+ "\\newcommand{\\C}[1]{\\textcolor{cinza}{#1}}\r\n"
		+ "\\newcommand{\\BI}[1]{\\textbf{\\textcolor{iris}{#1}}}\r\n"
		+ "\\newcommand{\\BL}[1]{\\textbf{\\textcolor{laranja}{#1}}}\r\n"
		+ "\\newcommand{\\BV}[1]{\\textbf{\\textcolor{verde}{#1}}}\r\n"
		+ "\\newcommand{\\BC}[1]{\\textbf{\\textcolor{cinza}{#1}}}\r\n"
		+ "\\newcommand{\\BB}[1]{\\textbf{\\textcolor{babyblue}{#1}}}\r\n"
		+ "\r\n"
		+ "\\begin{document}\r\n"
		+ "\\setlength\\parindent{0pt}\r\n"
		+ "\r\n"
		;
	}

	private String widthImagem(boolean primeiraPage)
	{
		return primeiraPage ? "6cm" : "4cm";
	}

	private String addSizeAssunto()
	{
		int tamanho = exercicio.getAssunto().getNome().length();
		if(tamanho<=23)
			return "\\Large\r\n";
		if(tamanho<=28)
			return "\\large\r\n";
		else
			return "\\normalsize\r\n";
	}

	/**
	 * Corpo da fonte do conteúdo, proporcional ao volume (caracteres + nº de linhas),
	 * já que conteúdo com muitas quebras (\\dfrac, matrizes) ocupa altura mesmo com
	 * poucos caracteres. (Mesma lógica da InstagramFeed.)
	 */
	private String sizeFontConteudo(String texto)
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().isBlank())
			return conta.getSizeFontTextLatex();

		return fontsize(ptConteudo(texto));
	}

	/**
	 * Há folga vertical na página da resolução? Ou seja: enunciado + resolução
	 * são pequenos o bastante para ampliar ambos sem risco de estourar a página.
	 * Mede pelo total de caracteres (capta também a quebra de linhas longas),
	 * pelo nº de linhas da resolução e pela quantidade de frações \\dfrac/\\frac
	 * (que ocupam altura dupla). Sem folga, cai no dimensionamento conservador.
	 */
	private boolean folgaResolucao(String enunciado, String resolucao)
	{
		return enunciado.length() + resolucao.length() <= 260
			&& contarLinhas(resolucao) <= 7
			&& contarOcorrencias(resolucao, "frac") <= 2;
	}

	/**
	 * Fonte do enunciado na página da resolução. Com folga, mantém o tamanho
	 * cheio (aproveita o espaço); sem folga, reduz 2pt para liberar espaço à
	 * resolução (foco da página). Piso em 7pt para manter legibilidade.
	 */
	private String sizeFontEnunciadoResolucao(String enunciado, String resolucao)
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().isBlank())
			return conta.getSizeFontTextLatex();

		int base = ptConteudo(enunciado);
		int pt = folgaResolucao(enunciado, resolucao) ? base : Math.max(7, base - 2);
		return fontsize(pt);
	}

	/**
	 * Fonte da resolução (foco da página 2). Quando há folga, amplia sobre a
	 * heurística-base para aproveitar o espaço; sem folga, mantém o tamanho-base
	 * (que já preenche a página) para não estourar nem quebrar linhas.
	 */
	private String sizeFontResolucao(String enunciado, String resolucao)
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().isBlank())
			return conta.getSizeFontTextLatex();

		return fontsize(ptResolucao(enunciado, resolucao));
	}

	private int ptResolucao(String enunciado, String resolucao)
	{
		int base = ptConteudo(resolucao);
		if(!folgaResolucao(enunciado, resolucao))
			return base;                                          // sem folga: tamanho-base

		if(resolucao.length()<=120 && contarLinhas(resolucao)<=4)
			return base + 5;                                     // resolução bem pequena
		return base + 4;
	}

	private int contarOcorrencias(String texto, String alvo)
	{
		int total=0, i=0;
		while((i=texto.indexOf(alvo, i))>=0)
		{
			total++;
			i+=alvo.length();
		}
		return total;
	}

	private int ptConteudo(String texto)
	{
		int caracteres = texto.length();
		int linhas = contarLinhas(texto);

		if(caracteres<=200 && linhas<=6)        return 12;
		if(caracteres<=380 && linhas<=10)       return 11;
		if(caracteres<=600 && linhas<=14)       return 10;
		if(caracteres<=900 && linhas<=20)       return 9;
		if(caracteres<=1300 && linhas<=26)      return 8;
		return 7;
	}

	private String fontsize(int pt)
	{
		return "\\fontsize{"+pt+"}{"+(pt+2)+"}\\selectfont";
	}

	/** Espaçamento da resolução escalado pela fonte (mesma proporção da avaliação a 10pt:
	 *  lineskip 0,6·pt, lineskiplimit 0,2·pt, jot 0,8·pt) — funciona em qualquer tamanho. */
	private String espacamentoResolucao(int pt)
	{
		int lineskip = Math.round(pt * 0.6f);
		int limit = Math.round(pt * 0.2f);
		int jot = Math.round(pt * 0.8f);
		return "\\setlength{\\lineskip}{"+lineskip+"pt}\\setlength{\\lineskiplimit}{"+limit
			+"pt}\\setlength{\\jot}{"+jot+"pt}";
	}

	private int contarLinhas(String texto)
	{
		if(texto==null||texto.isEmpty())
			return 0;
		int linhas=1, i=0;
		while((i=texto.indexOf("\\\\", i))>=0)
		{
			linhas++;
			i+=2;
		}
		return linhas;
	}

	public void exercicio()
	{
		String enunciado = enunciado(true);

		latex+="\\setstretch{1.4}\r\n\r\n"
		+"\\vspace{-10px}\r\n"
		+"\\begin{center}\r\n"
		+addSizeAssunto()
		+"\\BI{"+exercicio.getAssunto().getNome()+"}\r\n\r\n"
		+"\\vspace{6px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+sizeFontConteudo(enunciado)+"\r\n\r\n"
		+enunciado
		+"\\end{center}\r\n\r\n"
		+"\\vfill\r\n";

		if(programacaoPost.isAlternativaReel())
			latex+=alternativas();
	}

	/**
	 * Enunciado: parágrafos de texto (com matemática inline \\( \\)) e imagens via \\includegraphics.
	 * Na página da resolução (primeiraPage=false) a imagem renderiza menor para liberar espaço vertical.
	 */
	private String enunciado(boolean primeiraPage)
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
		{
			if(paragrafo.isTipoImagem())
				sb.append("\\vspace{6px}\r\n\\includegraphics[width="+widthImagem(primeiraPage)+"]{"
					+diretorio.getConfig().getImagens()+"/"+nomeImagem(paragrafo)+"}\r\n\r\n");
			else if(paragrafo.getTexto()!=null&&!paragrafo.getTexto().isBlank())
				sb.append(escapar(paragrafo.getTexto())).append("\r\n\r\n");
		}
		return sb.toString();
	}

	/** Alternativas em coluna única, alinhadas à esquerda (formato vertical do Reel/TikTok). */
	private String alternativas()
	{
		List<AlternativaExercicio> alternativas = conta.getAlternativas();
		if(alternativas.isEmpty())
			return "";

		StringBuilder linhas = new StringBuilder();
		for(AlternativaExercicio alt : alternativas)
			linhas.append("\\BC{"+alt.getLetra()+")}~"+escapar(alt.getTexto())+"\\par\r\n");

		// \parskip separa as alternativas; \lineskip/\lineskiplimit garantem espaço mínimo
		// mesmo com linhas altas (\dfrac), evitando que uma fração cole na outra.
		String espacamento = "\\setlength{\\parskip}{6pt}\\setlength{\\lineskip}{6pt}"
			+ "\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{6pt}\r\n";

		return "\\vspace{8px}\r\n"
		+"\\begin{flushleft}\r\n"+espacamento+linhas+"\\end{flushleft}\r\n";
	}

	public void resolucao()
	{
		String enunciado = enunciado(false);
		String texto = getTextoResolucao();

		latex+="\\setstretch{1.4}\r\n\r\n"
		+"\\vspace{-10px}\r\n"
		+"\\begin{center}\r\n"
		+addSizeAssunto()
		+"\\BI{"+exercicio.getAssunto().getNome()+"}\r\n\r\n"
		+"\\vspace{6px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+sizeFontEnunciadoResolucao(enunciado, texto)+"\r\n\r\n"
		+enunciado
		+"\\end{center}\r\n\r\n"
		+"\\vfill\r\n"
		+"\\begin{center}\r\n"
		+sizeFontResolucao(enunciado, texto)+"\r\n\r\n"
		// espaçamento escalado pela fonte (proporção da avaliação) — funciona em qualquer tamanho
		+espacamentoResolucao(ptResolucao(enunciado, texto))+"\r\n\r\n"
		+texto
		+"\\end{center}\r\n\r\n"
		+"\\vfill\r\n";
	}

	/** Junta os parágrafos de resolução, um por linha (\\ em modo texto). */
	private String getTextoResolucao()
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoResolucao paragrafo : conta.getResolucaoParagrafos())
		{
			String texto = paragrafo.getTexto();
			if(texto==null || texto.isBlank())
				continue;
			// frações de display ficam grandes (\dfrac), mas no EXPOENTE mantém \frac (pequena):
			// \dfrac num expoente fica enorme/feio (ex.: juros compostos (1+i)^{\frac{1}{12}}).
			texto = texto.replace("\\frac", "\\dfrac").replace("^{\\dfrac", "^{\\frac");
			if(sb.length()>0)
				sb.append(" \\\\\r\n");
			sb.append(escapar(texto));
		}
		return sb.toString();
	}

	/**
	 * Escapa caracteres que nunca aparecem "crus" de forma legítima no texto: "%"
	 * (comentário) e "$" (alterna math); converte "\\<dígito>" (erro de "/") em "/".
	 * Não toca em \\\\ nem em \\comando. (Espelha GeradorListaPDF/InstagramFeed.)
	 */
	private String escapar(String texto)
	{
		if(texto==null)
			return "";
		return texto.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		            .replaceAll("(?<!\\\\)%", "\\\\%")
		            .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	public void rodape(boolean resposta)
	{
		if(resposta && programacaoPost.isAlternativaReel() && conta.correta()!=null)
		{
			latex+="\\noindent\\BC{Alternativa "+conta.correta().getLetra()+"}\r\n\r\n";
		}
	}

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
