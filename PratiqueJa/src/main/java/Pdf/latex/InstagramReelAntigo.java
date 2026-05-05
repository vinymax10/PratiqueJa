package Pdf.latex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Random;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import Bean.Download.Diretorio;
import Bean.Exercicio.ConfigDownload;
import Matematica.Racional;
import Modelo.Configuracao.Enum.SistemaOperacional;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Matematica.Conta;

public class InstagramReelAntigo
{
	ExercicioPadrao exercicio;
	ConfigDownload configDownload;
	String latex;
	Conta conta;
	Diretorio diretorioBean;
	String alternativaCorreta;
	String alternativas;

	public InstagramReelAntigo(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean=diretorioBean;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ConfigDownload configDownload)
	{
		this.exercicio=exercicio;
		this.configDownload=configDownload;
		diretorioBean.limparDiretorios();
		gerarConta();
		
		gerarImagem();
		caput();
		
		cabecalho();
		exercicio();
		rodape();
		
		latex+="\\end{document}\r\n";
		
		Arquivo arq=new Arquivo(diretorioBean.getEnderecoTex());
		
		arq.escrever(latex);
		arq.finalizar();
		
	}
	
	private void gravarLogo()
	{
		File logo = new File(diretorioBean.getEnderecoLogo());
		if(!logo.exists())
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        File origem = new File(externalContext.getRealPath("image") + "/logo2.png");
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
	
	private void gravarBackground()
	{
		File background = new File(diretorioBean.getEnderecoBackground());
		Random rand=new Random();
		int indexBackground=1+rand.nextInt(103);
		
		if(!background.exists())
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        File origem = new File(externalContext.getRealPath("image") + "/background/reel/background"+indexBackground+".png");
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
		gravarBackground();
		
		File outputFile;
		OutputStream outputStream;
		
		try
		{
			if(conta.getBaos()!=null)
			{
				outputFile = new File(diretorioBean.getEnderecoImagens()+"conta.png");
				outputStream = new FileOutputStream(outputFile);
				conta.getBaos().writeTo(outputStream);
			}
			
			if(conta.getBaosResolucao()!=null)
			{
				outputFile = new File(diretorioBean.getEnderecoImagensResolucao()+"conta.png");
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
		Random rand=new Random();
		int index=rand.nextInt(12);
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
		latex+="\\includegraphics[width=60px]{logo2.png}\r\n"; 
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
		 +"paperheight=460.5px\r\n" 
		 +"}\r\n"
		 +"\\usepackage{background}\r\n" 
		 +"\\backgroundsetup{scale=1,opacity=.15,angle=0,contents={\\includegraphics{background.png}}}\r\n" 
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
		 +"\\renewcommand{\\headrulewidth}{0pt}\r\n" 
		 +"\r\n" 
		 +"\r\n" 
		 +"\\newcommand{\\T}[1]{\\textbf{\\large #1} \\newline}\r\n" 
		 +"\r\n" 
		 +"\\newcommand{\\TM}[1]{ \\textbf{ \\newline #1}  }\r\n" 
		 +"\\setlength\\columnsep{30pt}\r\n" 
		 +"\r\n" 
		 +"\\definecolor{bluebell}{rgb}{0.59, 0.64, 1}\r\n" 
		 +"\\definecolor{laranja}{rgb}{1, 0.58, 0.33}\r\n" 
		 +"\\definecolor{verde}{rgb}{0.07, 0.78, 0.67}\r\n" 
		 +"\\definecolor{babypink}{rgb}{0.96, 0.76, 0.76} \r\n" 
		 +"\\definecolor{cinza}{rgb}{0.4, 0.4, 0.4} \r\n" 
		 +"\\definecolor{iris}{rgb}{0.59, 0.64, 1} \r\n" 
		 +"\\definecolor{babyblue}{rgb}{0.13, 0.59, 0.95} \r\n"
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
		 +"\r\n";
	}
	
	public void alternativas()
	{
		Random rand=new Random();
		Racional racionalResultadoCorreto;
		String resultadoCorreto = conta.getResultadoCorreto();
		racionalResultadoCorreto = Racional.toConvert(resultadoCorreto);
		int limite=20;
		
		Racional racionalA = racionalResultadoCorreto.numeroProximo(limite);
		while(racionalA.igual(racionalResultadoCorreto))
			racionalA = racionalResultadoCorreto.numeroProximo(limite);
		
		Racional racionalB= racionalResultadoCorreto.numeroProximo(limite);
		while(racionalB.igual(racionalResultadoCorreto)||racionalB.igual(racionalA))
			racionalB = racionalResultadoCorreto.numeroProximo(limite);
		
		Racional racionalC= racionalResultadoCorreto.numeroProximo(limite);
		while(racionalC.igual(racionalResultadoCorreto)||racionalC.igual(racionalA)||racionalC.igual(racionalB))
			racionalC = racionalResultadoCorreto.numeroProximo(limite);
		
		String alternativaA = racionalA.toString();
		String alternativaB = racionalB.toString(); 
		String alternativaC = racionalC.toString();
		
		switch(rand.nextInt(3))
		{
			case 0: alternativaA=racionalResultadoCorreto.toString();
					alternativaCorreta="A"; break;
			case 1: alternativaB=racionalResultadoCorreto.toString(); 
					alternativaCorreta="B"; break;
			case 2: alternativaC=racionalResultadoCorreto.toString(); 
					alternativaCorreta="C"; break;
		}
		
		alternativas="A) "+alternativaA+"\r\n\r\n"
		+"B) "+alternativaB+"\r\n\r\n"
		+"C) "+alternativaC+"\r\n\r\n";
	}
	
	public void exercicio()
	{
		latex+="\\large\r\n" 
		+"\\setstretch{1.3}\r\n"
		+"\r\n"
		+"\\begin{center}\r\n"; 
		
		String texto="";
		
		if(conta.getPergunta()!=null&&!conta.getPergunta().equals(""))
			latex+=conta.getPergunta()+" \r\n\r\n";
		
		latex+="\\vspace{10px}\r\n";
		
		if(conta.getBaos()!=null)
			texto="\\includegraphics[valign=t,width=4.6cm]{"+diretorioBean.getConfigLatex().getImagens()+"/conta.png}";
		else if(conta.getTextLatex()!=null&&!conta.getTextLatex().equals(""))
			texto="$"+conta.getTextLatex()+"$";
		else
			texto="";
		
		latex+=texto;
		
		latex+="\r\n" 
		
		+"\\end{center}\r\n" 
		+"\\normalsize\r\n\r\n"
		+"\\vfill\r\n";
		
		alternativas();
	}
	
	
	public void rodape()
	{
		latex+="\\bfseries\r\n";
		latex+="\\begin{flushright}\r\n" 
		+"\\scriptsize pratiqueja.com\r\n" 
		+"\\end{flushright}\r\n";
	}
	
	public void gerarPDF()
	{
//		 -synctex=1 -interaction=nonstopmode --shell-escape
		ProcessBuilder pb; 
		if(diretorioBean.getConfigLatex().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdflatex", diretorioBean.getConfigLatex().getNome())
	        .inheritIO()
	        .directory(new File(diretorioBean.getEndereco()));
		else 
			pb = new ProcessBuilder("pdflatex", diretorioBean.getConfigLatex().getNome())
	        .inheritIO()
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
		if(diretorioBean.getConfigLatex().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdftopng","-r","300",
			diretorioBean.getConfigLatex().getNome()+".pdf", diretorioBean.getConfigLatex().getNome())
	        .inheritIO()
	        .directory(new File(diretorioBean.getEndereco()));
		else 
			pb = new ProcessBuilder("pdftopng","-r","300", 
			diretorioBean.getConfigLatex().getNome()+".pdf", diretorioBean.getConfigLatex().getNome())
	        .inheritIO()
	        .directory(new File(diretorioBean.getEndereco()));
		
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

	public String getAlternativaCorreta()
	{
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(String alternativaCorreta)
	{
		this.alternativaCorreta = alternativaCorreta;
	}

	public String getAlternativas()
	{
		return alternativas;
	}

	public void setAlternativas(String alternativas)
	{
		this.alternativas = alternativas;
	}
	
}
