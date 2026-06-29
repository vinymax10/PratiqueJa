package pdf.publicacao;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;

import bean.download.Diretorio;
import matematica.GeradorExercicio;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.ParagrafoExercicio;
import modelo.exercicio.ParagrafoResolucao;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.ImagemFile;
import pdf.util.Arquivo;
import util.CorAux;

public class InstagramFeed
{
	ExercicioPadrao exercicio;
	String latex;
	Exercicio conta;
	Diretorio diretorio;
	ProgramacaoPost programacaoPost;

	public InstagramFeed(Diretorio diretorio)
	{
		super();
		this.diretorio=diretorio;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost)
	{
		this.exercicio=exercicio;
		this.programacaoPost=programacaoPost;
		gerarConta();

		diretorio.limparDiretorios();
		gerarImagem();
		caput();

		cabecalho();
		exercicio();
		rodape(false);

		latex+="\\newpage \r\n";

		cabecalho();
		resolucao();
		rodape(true);

		latex+="\\end{document}\r\n";

		Arquivo arq=new Arquivo(diretorio.getEnderecoTex());

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
		latex=	"\\documentclass[12pt,twoside]{article}\r\n"
		 +"\r\n"
		 +"\\usepackage[english]{babel}\r\n"
		 +"\\usepackage[utf8]{inputenc}\r\n"
		 +"\\usepackage{sansmathfonts}\r\n"
		 +"\\usepackage[T1]{fontenc}\r\n"
		 +"\\renewcommand*\\familydefault{\\sfdefault} %% Only if the base font of the document is to be sans serif\r\n"
		 +"%\\usepackage{tabularx}\r\n"
		 +"\\usepackage{dashrule}\r\n"
		 +"\\usepackage{tikz}\r\n"
		 +"\\usepackage{tabularray}\r\n"
		 +"\\usepackage[export]{adjustbox}\r\n"
		 +"%\\captionsetup[longtable]{skip=30pt}\r\n"
		 +"\\usepackage{arydshln}\r\n"
		 +"\\usepackage{ctable}\r\n"
		 +"\\usepackage{colortbl}\r\n"
		 +"\\usepackage{multicol,lipsum}\r\n"
		 +"\\usepackage{amsmath,breqn}\r\n"
		 +"\\usepackage{amssymb,amsthm,amsmath,dsfont,mathrsfs}\r\n"
		 +"\\usepackage[colorinlistoftodos]{todonotes}\r\n"
		 +"\\usepackage{geometry}\r\n"
		 +"\\geometry{\r\n"
		 +"left=25px,right=25px,bottom=0px,top=10px,\r\n"
		 +"paperwidth=259px,\r\n"
		 +"paperheight=323.75px\r\n"
		 +"}\r\n"
		 +"\\usepackage{background}\r\n"
		 +"\\backgroundsetup{scale=1,opacity="+ConfigPost.TRANSPARENCIA+",angle=0,contents={\\includegraphics[width=\\paperwidth]{background.png}}}\r\n"
		 +"\\usepackage{natbib}\r\n"
		 +"\\usepackage{soul}\r\n"
		 +"\\usepackage{setspace}\r\n"
		 +"\\usepackage{hyperref}\r\n"
		 +"\\usepackage{xcolor}\r\n"
		 +"\\usepackage{cancel}\r\n"
		 +"\\color{darkgray}\r\n"
		 +"\\usepackage{graphicx}\r\n"
		 +"\\usepackage{fancyhdr}\r\n"
		 +"\\pagestyle{fancy}\r\n"
		 +"\\setlength{\\fboxsep}{0pt}\r\n"
		 +"\\setlength{\\fboxrule}{1pt}\r\n"
		 +"\\renewcommand{\\headrulewidth}{0pt}\r\n"
		 +"\r\n"
		 +"\r\n"
		 +"\\newcommand{\\T}[1]{\\textbf{\\large #1} \\newline}\r\n"
		 +"\r\n"
		 +"\\newcommand{\\TM}[1]{ \\textbf{ \\newline #1}  }\r\n"
		 +"\\setlength\\columnsep{30pt}\r\n"
		 +"\r\n"
		 +"\\definecolor{bluebell}{rgb}{0.25, 0.35, 0.89}\r\n"
		 +"\\definecolor{laranja}{rgb}{0.87, 0.48, 0.25}\r\n"
		 +"\\definecolor{verde}{rgb}{0, 0.54, 0.44}\r\n"
		 +"\\definecolor{babypink}{rgb}{1, 0.42, 0.52} \r\n"
		 +"\\definecolor{cinza}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_FONTE)+"} \r\n"
		 +"\\definecolor{iris}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_TITULO)+"} \r\n"
		 +"\\definecolor{babyblue}{rgb}{"+CorAux.convertHexPorc(ConfigPost.COR_NOME)+"} \r\n"
		 +"\r\n"
		 +"\\color{cinza} \r\n \r\n"
		 +"\\newcommand{\\C}[1]{\\textcolor{cinza}{#1}}\r\n"
		 +"\\newcommand{\\BI}[1]{\\textbf{\\textcolor{iris}{#1}}}\r\n"
		 +"\\newcommand{\\BL}[1]{\\textbf{\\textcolor{laranja}{#1}}}\r\n"
		 +"\\newcommand{\\BV}[1]{\\textbf{\\textcolor{verde}{#1}}}\r\n"
		 +"\\newcommand{\\BC}[1]{\\textbf{\\textcolor{cinza}{#1}}}\r\n"
		 +"\\newcommand{\\BB}[1]{\\textbf{\\textcolor{babyblue}{#1}}}\r\n"
		 +"\r\n"
		 +"\\begin{document}\r\n"
		 +"\\setlength\\parindent{0pt}\r\n"
		 +"\r\n"
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
	 * Corpo da fonte do conteúdo, proporcional ao volume (a página do feed é pequena).
	 * Considera caracteres E número de linhas (cada quebra ocupa altura fixa, então
	 * resoluções com muitas linhas — ex.: matrizes — precisam de fonte menor para caber).
	 */
	private String sizeFontConteudo(String texto)
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().isBlank())
			return conta.getSizeFontTextLatex();

		return fontsize(ptConteudo(texto));
	}

	/**
	 * Fonte do enunciado quando ele aparece na página da resolução: limitado
	 * a 8pt (foco da página é a resolução; o enunciado é só um lembrete) e
	 * com piso em 7pt para manter legibilidade.
	 */
	private String sizeFontEnunciadoResolucao(String texto)
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().isBlank())
			return conta.getSizeFontTextLatex();

		return fontsize(Math.max(7, Math.min(8, ptConteudo(texto) - 2)));
	}

	private int ptConteudo(String texto)
	{
		int caracteres = texto.length();
		int linhas = contarLinhas(texto);

		// Limiares de linhas mais apertados que os de caracteres: a altura total
		// (linhas × baselineskip + \lineskip) é o que estoura a página, não o nº
		// de caracteres por si só.
		if(caracteres<=140 && linhas<=4)        return 12;
		if(caracteres<=260 && linhas<=6)        return 10;
		if(caracteres<=420 && linhas<=8)        return 9;
		if(caracteres<=650 && linhas<=10)       return 8;
		if(caracteres<=900 && linhas<=12)       return 7;
		return 6;
	}

	private String fontsize(int pt)
	{
		return "\\fontsize{"+pt+"}{"+(pt+2)+"}\\selectfont";
	}

	/**
	 * \lineskip/\lineskiplimit/\jot da resolução escalados pela fonte (mesma proporção da
	 * avaliação a 10pt: 0,6·pt / 0,2·pt / 0,8·pt) — funciona em qualquer tamanho, não só 10pt.
	 */
	private String espacamentoResolucao(String texto)
	{
		// Fonte custom (override manual): não dá p/ inferir o pt, usa um valor médio.
		int pt = (conta.getSizeFontTextLatex()!=null && !conta.getSizeFontTextLatex().isBlank()) ? 9 : ptConteudo(texto);
		int lineskip = Math.round(pt * 0.6f);
		int limit = Math.round(pt * 0.2f);
		int jot = Math.round(pt * 0.8f);
		return "\\setlength{\\lineskip}{"+lineskip+"pt}\\setlength{\\lineskiplimit}{"+limit+"pt}\\setlength{\\jot}{"+jot+"pt}";
	}

	/** Número de linhas do conteúdo = 1 + quantidade de quebras LaTeX ("\\"). */
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
		+"\\vspace{-16px}\r\n"
		+"\\begin{center}\r\n"
		+addSizeAssunto()
		+"\\BI{"+exercicio.getAssunto().getNome()+"}\r\n\r\n"
		+"\\vspace{4px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+sizeFontConteudo(enunciado)+"\r\n\r\n"
		+enunciado
		+"\\end{center}\r\n\r\n"
		+"\\vfill\r\n";
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

	public void resolucao()
	{
		String enunciado = enunciado(false);
		String texto = getTextoResolucao();

		latex+="\\setstretch{1.4}\r\n\r\n"
		+"\\vspace{-16px}\r\n"
		+"\\begin{center}\r\n"
		+addSizeAssunto()
		+"\\BI{"+exercicio.getAssunto().getNome()+"}\r\n\r\n"
		+"\\vspace{4px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+sizeFontEnunciadoResolucao(enunciado)+"\r\n\r\n"
		+enunciado
		+"\\end{center}\r\n\r\n"
		+"\\vfill\r\n"
		+"\\begin{center}\r\n"
		+sizeFontConteudo(texto)+"\r\n\r\n"
		// gap mínimo entre linhas altas (\dfrac) — aperta quando a fonte cai a 6/7pt
		+espacamentoResolucao(texto)+"\r\n\r\n"
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
	 * Escapa caracteres que nunca aparecem "crus" de forma legítima no texto:
	 * "%" (comentário) e "$" (alterna math). Converte "\\<dígito>" (erro de "/")
	 * em "/". Não toca em \\\\ nem em \\comando. (Espelha GeradorListaPDF.)
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
	}

	public void gerarPDF()
	{
		ProcessBuilder pb;
		if(diretorio.getConfig().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdflatex", "-interaction=nonstopmode", diretorio.getConfig().getNome())
	        .inheritIO()
	        .directory(new File(diretorio.getEndereco()));
		else
			pb = new ProcessBuilder("pdflatex", "-interaction=nonstopmode", diretorio.getConfig().getNome())
	        .inheritIO()
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
		if(diretorio.getConfig().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdftocairo","-png","-r","300",
			diretorio.getConfig().getNome()+".pdf", diretorio.getConfig().getNome())
	        .inheritIO()
	        .directory(new File(diretorio.getEndereco()));
		else
			pb = new ProcessBuilder("pdftocairo","-png","-r","300",
			diretorio.getConfig().getNome()+".pdf", diretorio.getConfig().getNome())
	        .inheritIO()
	        .directory(new File(diretorio.getEndereco()));

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
