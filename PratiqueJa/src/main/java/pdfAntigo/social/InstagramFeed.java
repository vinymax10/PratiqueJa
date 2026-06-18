package pdfAntigo.social;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Random;

import util.CorAux;
import bean.download.Diretorio;
import matematica.ExercicioFactory;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.Exercicio;
import modelo.matematica.ParagrafoExercicio;
import modelo.publicacao.ProgramacaoPost;
import pdfAntigo.util.Arquivo;

public class InstagramFeed
{
	ExercicioPadrao exercicio;
	String latex;
	Exercicio conta;
	Diretorio diretorio;
	String alternaticaCorreta;
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
	
	private void gravarLogo()
	{
		File logo = new File(diretorio.getEnderecoLogo());
		
		if(!logo.exists())
		{
	        File origem = new File(diretorio.getEndBackgroundServidor() +programacaoPost.getConfigPost().getLogo().getEndImagem());
			try
			{
				Files.copy(origem.toPath(), logo.toPath());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
//		File outputFile;
//		OutputStream outputStream;
//
//		try
//		{
//			Blob blob=programacaoPost.getConfigPost().getLogo().getFile();
//			outputFile = new File(diretorioBean.getEnderecoLogo());
//			outputStream = new FileOutputStream(outputFile);
//			outputStream.write(blob.getBytes(1,(int)blob.length()));
//		}
//		catch(IOException | SQLException e)
//		{
//			e.printStackTrace();
//		}
	}
	
//	private void gravarBackgroundEspecifico()
//	{
//		File outputFile;
//		OutputStream outputStream;
//		
//		try
//		{
//			Blob blob=programacaoPost.getBackgroundFeed().getFile();
//			outputFile = new File(diretorioBean.getEnderecoBackground());
//			outputStream = new FileOutputStream(outputFile);
//			outputStream.write(blob.getBytes(1,(int)blob.length()));
//		}
//		catch(IOException | SQLException e)
//		{
//			e.printStackTrace();
//		}
//		
//	}
	
	private void gravarBackgroundPadrao()
	{
		File background = new File(diretorio.getEnderecoBackground());
				
		if(!background.exists())
		{
	        File origem = new File(diretorio.getEndBackgroundServidor() + programacaoPost.getPadraoFeed().getEndereco());
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
	        File origem = new File(diretorio.getEndBackgroundServidor() +programacaoPost.getBackgroundFeed().getEndImagem());
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
		if(programacaoPost.isBasePadraoFeed())
			gravarBackgroundPadrao();
		else
			gravarBackgroundEspecifico();
		
		File outputFile;
		OutputStream outputStream;
		
		int p = 0;
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
		{
			if(paragrafo.isTipoImagem())
			{
				try
				{
					Blob blob = paragrafo.getImagemFile().getFile();
					outputFile = new File(diretorio.getEnderecoImagens() + "conta_" + p + ".png");
					outputStream = new FileOutputStream(outputFile);
					outputStream.write(blob.getBytes(1, (int) blob.length()));
					outputStream.close();
				}
				catch(IOException | SQLException e)
				{
					e.printStackTrace();
				}
			}
			p++;
		}
	}

	private void gerarConta()
	{
		int index = new Random().nextInt(12);
		conta = ExercicioFactory.gerar(exercicio.getClasse(), index);
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
		 +"\\backgroundsetup{scale=1,opacity="+programacaoPost.getConfigPost().getTransparenciaPorc()+",angle=0,contents={\\includegraphics[width=\\paperwidth]{background.png}}}\r\n" 
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
		 +"\\definecolor{cinza}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorFonte())+"} \r\n" 
		 +"\\definecolor{iris}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorTitulo())+"} \r\n" 
		 +"\\definecolor{babyblue}{rgb}{"+CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorNome())+"} \r\n"
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
//		 +"\\bfseries\r\n\r\n"
//		 +"\\boldmath\r\n\r\n"
		 ;
	}
	
	private String widthImagem(boolean primeiraPage)
	{
//		if(primeiraPage)
//		{
//			if(exercicio.isImagemQuadrada())
//				return "5cm";
//			else
//				return "7cm";
//		}
//		else
//		{
//			if(exercicio.isImagemQuadrada())
//				return "3cm";
//			else
				return "4cm";
//		}
	}
	
	private String addSizeAssunto()
	{
		if(exercicio.getAssunto().getNome().length()<=23)
			return "\\Large\r\n";
		if(exercicio.getAssunto().getNome().length()<=28)
			return "\\large\r\n";
		else
			return "\\normalsize\r\n";
	}
	
	public void exercicio()
	{
		latex+="\\normalsize\r\n" 
		+"\\setstretch{1.5}\r\n"
		+"\r\n"
		+"\\vspace{-10px}\r\n"
		+"\\begin{center}\r\n"
//		+"\\Large\r\n"
		+addSizeAssunto()
		+"\\BI{"+exercicio.getAssunto().getNome()+"}\r\n\r\n"
		+"\\vspace{10px}\r\n"
		+"\\bfseries\r\n\r\n"
		+"\\boldmath\r\n\r\n"
		+"\\normalsize\r\n";
//		+"\\large\r\n";
		
		latex+=exercicio.getEnunciadoSingular();

		latex+="\\vspace{10px} \r\n\r\n";

		addSizeFont();

		int p = 0;
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
		{
			if(paragrafo.isTipoImagem())
				latex+="\\includegraphics[width="+widthImagem(true)+"]{"+diretorio.getConfig().getImagens()+"/conta_"+p+".png}\r\n";
			else if(paragrafo.getTexto()!=null&&!paragrafo.getTexto().isEmpty())
				latex+=getTexto(paragrafo.getTexto())+" \\newline \r\n";
			p++;
		}
		
		latex+="\r\n" 
		
		+"\\end{center}\r\n\r\n" 
		+"\\vfill\r\n";
		
	}
	
	private void addSizeFont()
	{
		if(conta.getSizeFontTextLatex()!=null&&!conta.getSizeFontTextLatex().equals(""))
			latex+=conta.getSizeFontTextLatex()+"\r\n\r\n";
		else
		{
			if(exercicio.getQuantidade()>=10)
				latex+="\\large\r\n\r\n";
			else if(exercicio.getQuantidade()==8)
				latex+="\\large\r\n\r\n";
			else if(exercicio.getQuantidade()==6)
				latex+="\\normalsize\r\n\r\n";
			else
				latex+="\\small\r\n\r\n";
		}
	}
	
	public void resolucao()
	{
		if(exercicio.getQuantidade()>=10)
			latex+="\\normalsize\r\n\r\n";
		else if(exercicio.getQuantidade()==8)
			latex+="\\small\r\n\r\n";
		else if(exercicio.getQuantidade()==6)
			latex+="\\footnotesize\r\n\r\n";
		else
			latex+="\\fontsize{8}{8}\\selectfont\r\n\r\n";
			
		latex+=getTextoResolucao();
		
		latex+="\r\n" 
		+"\\vfill\r\n";
	}
	
	private String getTextoResolucao()
	{
		String texto="";

		boolean temImagem=false;
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
			if(paragrafo.isTipoImagem())
				temImagem=true;

		if(temImagem)
		{
			texto+="\\vspace{-10px}\r\n\r\n";
			texto+="\\begin{center}\r\n";
			int p = 0;
			for(ParagrafoExercicio paragrafo : conta.getParagrafos())
			{
				if(paragrafo.isTipoImagem())
					texto+="\\includegraphics[width="+widthImagem(false)+"]{"+diretorio.getConfig().getImagens()+"/conta_"+p+".png}\r\n";
				p++;
			}
			texto+="\\end{center}\r\n\r\n";
		}
		else
			texto+="\\vspace{10px}\r\n\r\n";

		if(conta.getResolucao()!=null&&!conta.getResolucao().isEmpty())
			texto+=addSpace(conta.getResolucao())+"\r\n";

		return texto;
	}

	private String getTexto(String texto)
	{
		if(texto != null && !texto.equals(""))
			return texto.replaceAll("\\$", "\\\\\\$").replaceAll("%", "\\\\%");
		else
			return "";
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
	
	private String addSizeNome()
	{
		if(programacaoPost.getConfigPost().getNome().length()<=33)
			return "\\normalsize";
		else
			return "\\small";
	}
	
	public void rodape(boolean resposta)
	{
		latex+="\\begin{flushright}\r\n" 
		+addSizeNome()+" \\BB{\\textbf{"+programacaoPost.getConfigPost().getNome()+"}}\r\n \\\\"
		+"\\end{flushright}\r\n";
	}
	
	public void gerarPDF()
	{
//		 -synctex=1 -interaction=nonstopmode --shell-escape
		ProcessBuilder pb; 
		if(diretorio.getConfig().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdflatex", diretorio.getConfig().getNome())
	        .inheritIO()
	        .directory(new File(diretorio.getEndereco()));
		else 
			pb = new ProcessBuilder("pdflatex", diretorio.getConfig().getNome())
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
//		 -synctex=1 -interaction=nonstopmode --shell-escape
		ProcessBuilder pb; 
		if(diretorio.getConfig().getSistemaOperacional()==SistemaOperacional.Linux)
			pb= new ProcessBuilder("sudo", "pdftopng","-r","300",
			diretorio.getConfig().getNome()+".pdf", diretorio.getConfig().getNome())
	        .inheritIO()
	        .directory(new File(diretorio.getEndereco()));
		else 
			pb = new ProcessBuilder("pdftopng","-r","300", 
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

//<subsystem xmlns="urn:jboss:domain:undertow:12.0" default-server="default-server" default-virtual-host="default-host" default-servlet-container="default" default-security-domain="other" statistics-enabled="${wildfly.undertow.statistics-enabled:${wildfly.statistics-enabled:false}}">
//<buffer-cache name="default"/>
//<server name="default-server">
//    <http-listener name="default" socket-binding="http" redirect-socket="https" enable-http2="true"/>
//    <https-listener name="https" socket-binding="https" ssl-context="applicationSSC" enable-http2="true"/>
//    <host name="default-host" alias="localhost">
//        <location name="/" handler="welcome-content"/>
//        <location name="/BancoDadosRH/images" handler="imagesDirHandler"/>
//        <http-invoker http-authentication-factory="application-http-authentication"/>
//    </host>
//</server>
//<servlet-container name="default">
//    <jsp-config/>
//    <websockets/>
//</servlet-container>
//<handlers>
//    <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
//    <file name="imagesDirHandler" path="/opt/bancodadosrh/fotos" cache-buffer-size="1024" cache-buffers="1024" directory-listing="true" follow-symlink="true"/>
//</handlers>
//<application-security-domains>
//    <application-security-domain name="other" security-domain="ApplicationDomain"/>
//</application-security-domains>
//</subsystem>
//
//<subsystem xmlns="urn:jboss:domain:undertow:12.0" default-server="default-server" default-virtual-host="default-host" default-servlet-container="default" default-security-domain="other" statistics-enabled="${wildfly.undertow.statistics-enabled:${wildfly.statistics-enabled:false}}">
//<buffer-cache name="default"/>
//<server name="default-server">
//    <http-listener name="default" socket-binding="http" redirect-socket="https" enable-http2="false"/>
//    <https-listener name="https" socket-binding="https" ssl-context="applicationSSC" enable-http2="false"/>
//    <host name="default-host" alias="localhost">
//        <location name="/" handler="welcome-content"/>
//		<location name="/PratiqueJa/image" handler="imagesDirHandler"/>
//        <http-invoker http-authentication-factory="application-http-authentication"/>
//    </host>
//</server>
//<servlet-container name="default">
//    <jsp-config/>
//    <websockets/>
//</servlet-container>
//<handlers>
//    <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
//	<file name="imagesDirHandler" path="C:\Servidor\wildfly-26.1.0.Final\images" cache-buffer-size="1024" cache-buffers="1024" directory-listing="true" follow-symlink="true"/>
//</handlers>
//<application-security-domains>
//    <application-security-domain name="other" security-domain="ApplicationDomain"/>
//</application-security-domains>
//</subsystem>
