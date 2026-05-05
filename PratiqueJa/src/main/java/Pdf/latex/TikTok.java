package Pdf.latex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Random;

import Auxiliar.CorAux;
import Bean.Download.Diretorio;
import Matematica.Racional;
import Modelo.Configuracao.Enum.SistemaOperacional;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Exercicio.Enum.TipoExercicio;
import Modelo.Instagram.ProgramacaoPost;
import Modelo.Matematica.Conta;

public class TikTok
{
	ExercicioPadrao exercicio;
	String latex;
	Conta conta;
	Diretorio diretorioBean;
	String alternaticaCorreta;
	ProgramacaoPost programacaoPost;

	public TikTok(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean = diretorioBean;
	}
	
	public void gerarPDFExercicio(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost)
	{
		this.exercicio = exercicio;
		this.programacaoPost=programacaoPost;
		gerarConta();

		diretorioBean.limparDiretorios();
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

		Arquivo arq = new Arquivo(diretorioBean.getEnderecoTex());

		arq.escrever(latex);
		arq.finalizar();
	}
	
	private void gravarLogo()
	{
		File logo = new File(diretorioBean.getEnderecoLogo());
		
		if(!logo.exists())
		{
	        File origem = new File(diretorioBean.getEndBackgroundServidor() +programacaoPost.getConfigPost().getLogo().getEndImagem());
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
//	private void gravarBackground()
//	{
//		File background = new File(diretorioBean.getEnderecoBackground());
//		Random rand = new Random();
//		int indexBackground = 1 + rand.nextInt(103);
//
//		if(!background.exists())
//		{
//	        File origem = new File(diretorioBean.getEndBackgroundServidor() + "/background/reel/background"+indexBackground+".png");
//			try
//			{
//				Files.copy(origem.toPath(), background.toPath());
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
	
	private void gravarBackgroundPadrao()
	{
		File background = new File(diretorioBean.getEnderecoBackground());
		int indexBackground=1;
		if(programacaoPost.isBackgroundAleatorioReel())
		{
			Random rand=new Random();
			indexBackground=1+rand.nextInt(103);
		}
		else
		{
			String listStr[]=programacaoPost.getPadraoReel().getNome().split(" ");
			if(listStr.length>1)
				indexBackground = Integer.valueOf(listStr[1]);
		}
		
		if(!background.exists())
		{
	        File origem = new File(diretorioBean.getEndBackgroundServidor() + "/background/reel/background"+indexBackground+".png");
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
		File background = new File(diretorioBean.getEnderecoBackground());
		
		if(!background.exists())
		{
	        File origem = new File(diretorioBean.getEndBackgroundServidor() +programacaoPost.getBackgroundReel().getEndImagem());
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
		if(programacaoPost.isBasePadraoReel())
			gravarBackgroundPadrao();
		else
			gravarBackgroundEspecifico();

		File outputFile;
		OutputStream outputStream;

		try
		{
			if(conta.getBaos() != null)
			{
				outputFile = new File(diretorioBean.getEnderecoImagens() + "conta.png");
				outputStream = new FileOutputStream(outputFile);
				conta.getBaos().writeTo(outputStream);
			}

			if(conta.getBaosResolucao() != null)
			{
				outputFile = new File(diretorioBean.getEnderecoImagensResolucao() + "conta.png");
				outputStream = new FileOutputStream(outputFile);
				conta.getBaosResolucao().writeTo(outputStream);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void gerarConta()
	{
		Random rand = new Random();
		int index = rand.nextInt(12);
		try
		{
			conta = (Conta) Class.forName(exercicio.getClasse()).getConstructor(Integer.TYPE).newInstance(index);
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
		| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void cabecalho()
	{
		latex+="\\includegraphics[width=100px]{logo2.png}\r\n";
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
		+"\\backgroundsetup{scale=1,opacity="+programacaoPost.getConfigPost().getTransparenciaPorc()+",angle=0,contents={\\includegraphics[width=\\paperwidth]{background.png}}}\r\n" 
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
		 +"\\definecolor{cinza}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorFonte())+"} \r\n" 
		 +"\\definecolor{iris}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorTitulo())+"} \r\n" 
		 +"\\definecolor{babyblue}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorNome())+"} \r\n"
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

	public void alternativas()
	{
		Random rand = new Random();

		String alternativaA = alternativa(conta.getResultadoCorreto());

		String alternativaB = alternativa(conta.getResultadoCorreto());
		while(alternativaB.equals(alternativaA))
			alternativaB = alternativa(conta.getResultadoCorreto());

		String alternativaC = alternativa(conta.getResultadoCorreto());
		while(alternativaC.equals(alternativaA) || alternativaC.equals(alternativaB))
			alternativaC = alternativa(conta.getResultadoCorreto());

		String resultadoCorreto = resultadoCorretoLatex(conta.getResultadoCorreto());
		switch(rand.nextInt(3))
		{
			case 0:
				alternativaA = resultadoCorreto;
				alternaticaCorreta = "A";
				break;
			case 1:
				alternativaB = resultadoCorreto;
				alternaticaCorreta = "B";
				break;
			case 2:
				alternativaC = resultadoCorreto;
				alternaticaCorreta = "C";
				break;
		}

		latex += "\\BC{A)} $" + alternativaA + "$\\vspace{15px} \r\n\r\n" + "\\BC{B)} $" + alternativaB
		+ "$ \\vspace{15px}\r\n\r\n" + "\\BC{C)} $" + alternativaC + "$ \r\n\r\n";
	}

	public String resultadoCorretoLatex(String resultadoCorreto)
	{
		if(resultadoCorreto.contains("%"))
		{
			return resultadoCorreto;
		}
		else
		{
			Racional racionalResultadoCorreto = Racional.toConvert(resultadoCorreto);
			return racionalResultadoCorreto.showDfrac();
		}
	}

	public String alternativa(String resultadoCorreto)
	{
		Random rand = new Random();
		if(resultadoCorreto.contains("%"))
		{
			String resultadoCorretoStr = resultadoCorreto.replaceAll("\\\\%", "");
			long resultadoCorretoNumerador = Long.valueOf(resultadoCorretoStr);
			do
				resultadoCorretoNumerador += rand.nextBoolean() ? 1 + rand.nextInt(10) : -1 - rand.nextInt(10);
			while(resultadoCorretoNumerador<=0);
			
			return resultadoCorretoNumerador+"\\%";
		}
		else
		{
			Racional racionalResultadoCorreto = Racional.toConvert(resultadoCorreto);
			int limite = 20;

			Racional alternativa = racionalResultadoCorreto.numeroProximo(limite);
			while(alternativa.igual(racionalResultadoCorreto))
				alternativa = racionalResultadoCorreto.numeroProximo(limite);

			return alternativa.showDfrac();
		}

	}

	public void alternativasBoolean()
	{
		Random rand = new Random();
		String resultadoCorreto = conta.resultadoCorretoBolTexto();

		String alternativaA, alternativaB;

		if(rand.nextBoolean())
		{
			alternativaA = "Sim";
			alternativaB = "Não";
		}
		else
		{
			alternativaA = "Não";
			alternativaB = "Sim";
		}

		if(alternativaA.equals(resultadoCorreto))
			alternaticaCorreta = "A";
		else
			alternaticaCorreta = "B";

		latex += "\\BC{A)} " + alternativaA + "\\vspace{15px} \r\n\r\n" + "\\BC{B)} " + alternativaB + " \r\n\r\n";
	}

	private String widthImagem(boolean primeiraPage)
	{
		if(primeiraPage)
		{
			if(exercicio.isImagemQuadrada())
				return "4cm";
			else
				return "7cm";
		}
		else
		{
			if(exercicio.isImagemQuadrada())
				return "3cm";
			else
				return "5cm";
		}
	}

	private String addSizeAssunto()
	{
		if(exercicio.getAssuntoCurso().getNome().length()<=23)
			return "\\Large\r\n";
		if(exercicio.getAssuntoCurso().getNome().length()<=28)
			return "\\large\r\n";
		else
			return "\\normalsize\r\n";
	}
	
	public void exercicio()
	{
		latex += "\\normalsize\r\n" 
		+ "\\setstretch{1.5}\r\n" 
		+ "\r\n" 
		+ "\\vspace{-10px}\r\n" 
		+ "\\begin{center}\r\n"
		+addSizeAssunto()
		+ "\\BI{" + exercicio.getAssuntoCurso().getNome() + "}\r\n\r\n"
		+"\\vspace{10px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+"\\normalsize\r\n";
		
		String texto = "";

		if(conta.getPergunta() != null && !conta.getPergunta().equals(""))
			latex += conta.getPergunta();
		else
			latex += exercicio.getEnunciadoSingular();

		latex += "\\vspace{10px} \r\n\r\n";

		addSizeFont();

		if(conta.getBaos() != null)
		{
//			texto+="\\vspace{-10px}\r\n";
			texto += "\\includegraphics[width=" + widthImagem(true) + "]{" + diretorioBean.getConfigLatex().getImagens()
			+ "/conta.png}\r\n";
		}
		else if(conta.getTextLatex() != null && !conta.getTextLatex().equals(""))
			texto = "$" + conta.getTextLatex() + "$";
		else
			texto = "";

		latex += texto;

		latex += "\r\n"

		+ "\\end{center}\r\n\r\n" + "\\vfill\r\n" + "\\large\r\n";

		if(programacaoPost.isAlternativaReel())
		{
			if(exercicio.getTipoExercicio() == TipoExercicio.Boolean)
				alternativasBoolean();
			else
				alternativas();
		}
	}

	private void addSizeFont()
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().equals(""))
			latex+=conta.getSizeFontTextLatex()+"\r\n\r\n";
		else
		{
			if(exercicio.getQuantidade() >= 10)
				latex += "\\large\r\n\r\n";
			else if(exercicio.getQuantidade() == 8)
				latex += "\\large\r\n\r\n";
			else if(exercicio.getQuantidade() == 6)
				latex += "\\footnotesize\r\n\r\n";
			else
				latex += "\\scriptsize\r\n\r\n";
		}
	}

	public void resolucao()
	{
		latex += "\\normalsize\r\n" + "\\vspace{-10px}\r\n" + "\\begin{center}\r\n";

		if(conta.getPergunta() != null && !conta.getPergunta().equals(""))
			latex += conta.getPergunta() + " \r\n\r\n";

		latex += "\\end{center}\r\n";

		addSizeFont();
		latex += getTextoResolucao();

		latex += "\r\n" + "\\large\r\n\r\n" + "\\vfill\r\n";
	}

	private String getTextoResolucao()
	{
		String texto = "";

		if(conta.getBaosResolucao() != null)
		{
			texto += "\\vspace{-20px}\r\n\r\n";
			texto += "\\begin{center}\r\n";
			texto += "\\includegraphics[width=" + widthImagem(false) + "]{"
			+ diretorioBean.getConfigLatex().getImagensResolucao() + "/conta.png}\r\n";
			texto += "\\end{center}\r\n\r\n";
		}
		else if(conta.getBaos() != null)
		{
			texto += "\\vspace{-20px}\r\n\r\n";
			texto += "\\begin{center}\r\n";
			texto += "\\includegraphics[width=" + widthImagem(false) + "]{" + diretorioBean.getConfigLatex().getImagens()
			+ "/conta.png}\r\n";
			texto += "\\end{center}\r\n\r\n";
		}
		else if(exercicio.isMostrarResolucao() && conta.getTextLatex() != null && !conta.getTextLatex().equals(""))
		{
			texto += "\\begin{math}\r\n";
			texto += conta.getTextLatex() + " \\vspace{10px} \\newline \r\n";
			texto += "\\end{math}\r\n\r\n";
		}
		else
			texto += "\\vspace{10px}\r\n\r\n";

		texto += "\\begin{math}\r\n";
		texto += addSpace(conta.getResolucaoLatex()) + "\r\n";
		texto += "\\end{math}\r\n";
		return texto;
	}

	private String addSpace(String texto)
	{
		if(!texto.contains("\\begin{array}"))
		{
			String linhas[] = texto.split("\\\\\\\\");
			String str, resultado = "";
			int space = 10;
			if(exercicio.getQuantidade() == 8)
				space = 9;
			else if(exercicio.getQuantidade() == 6)
				space = 8;
			else if(exercicio.getQuantidade() == 4)
				space = 7;

			for(int i = 0; i < linhas.length - 1; i++)
			{
				str = linhas[i];
				if(linhas[i + 1].contains("dfrac") || str.contains("dfrac") || linhas[i + 1].contains("frac")
				|| str.contains("frac") || linhas[i + 1].contains("sqrt") || str.contains("sqrt")
				|| linhas[i + 1].contains("^") || str.contains("^"))
					str = str + " \\vspace{" + space + "px}";

				resultado += str + " \\\\";
			}
			resultado += linhas[linhas.length - 1];
			return resultado;
		}
		return texto;
	}
	
	private String addSizeNomeAlternativa()
	{
		if(programacaoPost.getConfigPost().getNome().length()<=22)
			return "\\footnotesize";
		if(programacaoPost.getConfigPost().getNome().length()<=29)
			return "\\scriptsize";
		else
			return "\\tiny";
	}
	
	private String addSizeNome()
	{
		if(programacaoPost.getConfigPost().getNome().length()<=33)
			return "\\normalsize";
		else
			return "\\small";
	}

	public void rodape(boolean resposta)
	{
		if(resposta&&programacaoPost.isAlternativaReel())
		{
			latex += "\\begin{flushleft}\r\n" 
		+ "\\setlength{\\tabcolsep}{0em}\r\n" 
			+ "\\setlength\\parindent{0pt}\r\n"
			+ "\\begin{tabularx}{\\textwidth}{X r}\r\n" 
			+ "\\BC{Alternativa " + alternaticaCorreta
			+ "}&"+addSizeNomeAlternativa()+" \\BB{\\textbf{"+programacaoPost.getConfigPost().getNome()+"}}\r\n \\\\" 
			+ "\\end{tabularx}\r\n" 
			+ "\\end{flushleft}\r\n";
		}
		else
		{
			latex += "\\begin{flushright}\r\n" 
			+addSizeNome()+" \\BB{\\textbf{"+programacaoPost.getConfigPost().getNome()+"}}\r\n \\\\"
			+ "\\end{flushright}\r\n";

		}
		
	}

	public void gerarPDF()
	{
//		 -synctex=1 -interaction=nonstopmode --shell-escape
		ProcessBuilder pb;
		if(diretorioBean.getConfigLatex().getSistemaOperacional() == SistemaOperacional.Linux)
			pb = new ProcessBuilder("sudo", "pdflatex", diretorioBean.getConfigLatex().getNome()).inheritIO()
			.directory(new File(diretorioBean.getEndereco()));
		else
			pb = new ProcessBuilder("pdflatex", diretorioBean.getConfigLatex().getNome()).inheritIO()
			.directory(new File(diretorioBean.getEndereco()));

		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(new File(diretorioBean.getEnderecoOutputLog())));

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
//		 -synctex=1 -interaction=nonstopmode --shell-escape
		ProcessBuilder pb;
		if(diretorioBean.getConfigLatex().getSistemaOperacional() == SistemaOperacional.Linux)
			pb = new ProcessBuilder("sudo", "pdftopng", "-r", "300", diretorioBean.getConfigLatex().getNome() + ".pdf",
			diretorioBean.getConfigLatex().getNome()).inheritIO().directory(new File(diretorioBean.getEndereco()));
		else
			pb = new ProcessBuilder("pdftopng", "-r", "300", diretorioBean.getConfigLatex().getNome() + ".pdf",
			diretorioBean.getConfigLatex().getNome()).inheritIO().directory(new File(diretorioBean.getEndereco()));

		pb.redirectErrorStream(true);
		pb.redirectOutput(Redirect.appendTo(new File(diretorioBean.getEnderecoOutputLog())));

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
