package pdf.latex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Random;

import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import matematica.Racional;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.TipoExercicio;
import modelo.matematica.Conta;

public class InstagramFeedAntigo
{
	ExercicioPadrao exercicio;
	ConfigDownload configDownload;
	String latex;
	Conta conta;
	Diretorio diretorioBean;
	String alternaticaCorreta;
	
	public InstagramFeedAntigo(Diretorio diretorioBean)
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
		rodape(false);
		
		latex+="\\newpage \r\n";
		
		cabecalho();
		resolucao();
		rodape(true);
		
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
		int indexBackground=1+rand.nextInt(42);
		
		if(!background.exists())
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        File origem = new File(externalContext.getRealPath("image") + "/background/feed/background"+indexBackground+".png");
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
		 +"paperheight=259px\r\n" 
		 +"}\r\n"
		 +"\\usepackage{background}\r\n" 
		 +"\\backgroundsetup{scale=1,opacity=.15,angle=0,contents={\\includegraphics[width=\\paperwidth]{background.png}}}\r\n" 
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
		 +"\r\n"
		 +"\\bfseries\r\n\r\n";
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
		
		String alternativaA = racionalA.showDfrac();
		String alternativaB = racionalB.showDfrac(); 
		String alternativaC = racionalC.showDfrac();
		
		switch(rand.nextInt(3))
		{
			case 0: alternativaA=racionalResultadoCorreto.showDfrac();
					alternaticaCorreta="A"; break;
			case 1: alternativaB=racionalResultadoCorreto.showDfrac(); 
					alternaticaCorreta="B"; break;
			case 2: alternativaC=racionalResultadoCorreto.showDfrac(); 
					alternaticaCorreta="C"; break;
		}
		
		latex+="\\BC{A)} $"+alternativaA+"$\\vspace{10px} \r\n\r\n"
		+"\\BC{B)} $"+alternativaB+"$ \\vspace{10px}\r\n\r\n"
		+"\\BC{C)} $"+alternativaC+"$ \r\n\r\n";
	}
	
	public void alternativasBoolean()
	{
		Random rand=new Random();
		String resultadoCorreto = conta.resultadoCorretoBolTexto();
		
		String alternativaA,alternativaB;
		
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
			alternaticaCorreta="A";
		else
			alternaticaCorreta="B";
		
		latex+="\\BC{A)} "+alternativaA+"\\vspace{15px} \r\n\r\n"
		+"\\BC{B)} "+alternativaB+" \r\n\r\n";
	}
	
	private String widthImagem()
	{
		if(exercicio.isImagemQuadrada())
			return "3cm";
		else
		return "4.6cm";
	}
	
	public void exercicio()
	{
		latex+="\\normalsize\r\n" 
		+"\\setstretch{1.5}\r\n"
		+"\r\n"
		+"\\vspace{-10px}\r\n"
		+"\\begin{center}\r\n"; 
		
		String texto="";
		
		if(conta.getPergunta()!=null&&!conta.getPergunta().equals(""))
			latex+=conta.getPergunta();
		else
			latex+=exercicio.getEnunciadoSingular();
		
		latex+="\\vspace{10px} \r\n\r\n";
		
		if(conta.getBaos()!=null)
		{
//			texto+="\\vspace{-10px}\r\n";
			texto+="\\includegraphics[width="+widthImagem()+"]{"+diretorioBean.getConfigLatex().getImagens()+"/conta.png}\r\n";
		}
		else if(conta.getTextLatex()!=null&&!conta.getTextLatex().equals(""))
			texto="$"+conta.getTextLatex()+"$";
		else
			texto="";
		
		latex+=texto;
		
		latex+="\r\n" 
		
		+"\\end{center}\r\n\r\n" 
		+"\\vfill\r\n";
		
		if(exercicio.getTipoExercicio()==TipoExercicio.Boolean)
			alternativasBoolean();
		else
			alternativas();
	}
	
	public void resolucao()
	{
		if(exercicio.getQuantidade()>=10)
			latex+="\\normalsize\r\n\r\n";
		else if(exercicio.getQuantidade()==8)
			latex+="\\footnotesize\r\n\r\n";
		else if(exercicio.getQuantidade()==6)
			latex+="\\scriptsize\r\n\r\n";
		else
			latex+="\\tiny\r\n\r\n";
			
		latex+="\\vspace{-10px}\r\n"
		+"\\begin{center}\r\n";
		
		if(conta.getPergunta()!=null&&!conta.getPergunta().equals(""))
			latex+=conta.getPergunta()+" \r\n\r\n";
		
		latex+="\\end{center}\r\n";
		
		latex+=getTextoResolucao();
		
		latex+="\r\n" 
		+"\\large\r\n\r\n"
		+"\\vfill\r\n";
	}
	
	private String getTextoResolucao() 
	{
		String texto="";
		
		if(conta.getBaosResolucao()!=null)
		{
			texto+="\\vspace{-10px}\r\n\r\n";
			texto+="\\begin{center}\r\n";
			texto+="\\includegraphics[width="+widthImagem()+"]{"+diretorioBean.getConfigLatex().getImagensResolucao()+"/conta.png}\r\n";
			texto+="\\end{center}\r\n\r\n";
		}
		else if(conta.getBaos()!=null)
		{	
			texto+="\\vspace{-10px}\r\n\r\n";
			texto+="\\begin{center}\r\n";
			texto+="\\includegraphics[width="+widthImagem()+"]{"+diretorioBean.getConfigLatex().getImagens()+"/conta.png}\r\n";
			texto+="\\end{center}\r\n\r\n";
		}
		else if(exercicio.isMostrarResolucao()
		&&conta.getTextLatex()!=null&&!conta.getTextLatex().equals(""))
		{
			texto+="\\begin{math}\r\n";
			texto+=conta.getTextLatex()+" \\vspace{5px} \\newline \r\n";
			texto+="\\end{math}\r\n";
		}
		else
			texto+="\\vspace{10px}\r\n\r\n";
		
		texto+="\\begin{math}\r\n";
		texto+=addSpace(conta.getResolucaoLatex())+"\r\n";
		texto+="\\end{math}\r\n";
		return texto;
	}
	
	private String addSpace(String texto)
	{
		if(!texto.contains("\\begin{array}"))
		{
			
			String linhas[]=texto.split("\\\\\\\\");
			String str,resultado="";
			int space=5;
			if(exercicio.getQuantidade()==8)
				space=4;
			else if(exercicio.getQuantidade()==6)
				space=3;
			else if(exercicio.getQuantidade()==4)
				space=2;
			
			for(int i = 0; i < linhas.length-1; i++)
			{
				str = linhas[i];
				if(linhas[i+1].contains("dfrac")||str.contains("dfrac")
				||linhas[i+1].contains("frac")||str.contains("frac")
				||linhas[i+1].contains("sqrt")||str.contains("sqrt")
				||linhas[i+1].contains("^")||str.contains("^"))
					str=str+" \\vspace{"+space+"px}";
				
				resultado+=str+" \\\\";
			}
			resultado+=linhas[linhas.length-1];
			return resultado;
		}
		return texto;
	}
	
	public void rodape(boolean resposta)
	{
		if(resposta)
		{
			latex+="\\begin{flushleft}\r\n" 
			+"\\setlength{\\tabcolsep}{0em}\r\n" 
			+"\\setlength\\parindent{0pt}\r\n"
			+"\\begin{tabularx}{\\textwidth}{X r}\r\n"
			+"\\BC{Alternativa "+ alternaticaCorreta+"}&\\scriptsize pratiqueja.com\r\n \\\\"
			+"\\end{tabularx}\r\n"
			+"\\end{flushleft}\r\n";
		}
		else
		{
			latex+="\\begin{flushright}\r\n" 
			+"\\scriptsize pratiqueja.com\r\n \\\\"
			+"\\end{flushright}\r\n";
		}
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
}
