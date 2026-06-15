package pdfAntigo.exercicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletContext;
import matematica.ExercicioFactory;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.Exercicio;
import pdfAntigo.util.Arquivo;

public class GerarLatexExercicio
{
	ExercicioPadrao exercicio;
	ConfigDownload configDownload;
	String latex;
	List<Exercicio> listaContas;
	Diretorio diretorioBean;
    private ServletContext servletContext;

	public GerarLatexExercicio(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean=diretorioBean;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ConfigDownload configDownload)
	{
		this.exercicio=exercicio;
		this.configDownload=configDownload;
		diretorioBean.limparDiretorios();
		gerarListaContas();
		
		gerarImagens();
		caput();
		cabecalho();
		listaExercicios();
		if(configDownload.isRespostas())
			rodape();
		
		if(configDownload.isResolucao() && !listaContas.isEmpty()
		&& listaContas.get(0).getResolucao() != null && !listaContas.get(0).getResolucao().isEmpty())
		{
			latex+="\\newpage \r\n";
			cabecalho();
			listaResolucao();
		}
		
		latex+="\\end{document}";
		
		Arquivo arq=new Arquivo(diretorioBean.getEnderecoTex());
		
		arq.escrever(latex);
		arq.finalizar();
		
	}
	
	private void gravarLogo()
	{
		File logo = new File(diretorioBean.getEnderecoLogo());
		if(!logo.exists())
		{
			File origem;
			if(FacesContext.getCurrentInstance()==null)
				origem = new File(servletContext.getRealPath("image") + "/logo.png");
			else
			{
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				origem = new File(externalContext.getRealPath("image") + "/logo.png");
			}
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
	
	private void gerarImagens()
	{
		gravarLogo();
		// TODO migrar geração de imagens para o novo Exercicio (parágrafos/alternativas + ImagemFile).
	}
	
	private void gerarListaContas()
	{
		listaContas = new ArrayList<Exercicio>();
		for(int i = 0; i < exercicio.getQuantidade(); i++)
			listaContas.add(ExercicioFactory.gerar(exercicio.getClasse(), i + 1));
	}
	
	public void cabecalho()
	{
		String nome="";
		if(configDownload.isIdentificacao())
			nome=configDownload.getUsuario().getNome();
		
		latex+=	
		"\\setstretch{1} \r\n" 
		+"\\normalsize \r\n" 
		+"\\setlength{\\tabcolsep}{0em} \r\n"
		+"\\setlength\\parindent{0pt} \r\n"
		+"\\begin{tabular}{p{0.19\\textwidth} p{0.61\\textwidth} p{0.2\\textwidth}}\r\n" 
		+"\\vspace{-0.5cm} \\includegraphics[width=2.7cm]{logo.png}  & "
		+"\\BB{\\Large Exercícios } & Nota:    \\\\  \r\n" 
		+"\\vspace{-0.5cm} \\small \\BC{pratiqueja.com} & \\vspace{-0.5cm} Nome: "+nome+" & \\vspace{-0.5cm} Data:  \\\\ "
		+"\\multicolumn{3}{l}{\\footnotesize "
		+ "\\textcolor{iris}{Módulo "+exercicio.getAssunto().getModulo().getNome()+"}"
		+" \\BL{-} \\textcolor{verde}{Assunto "+exercicio.getAssunto().getNome() +"} "
		+"\\BL{-} Nível "+exercicio.getNivelRomano()+" } \\\\"
		+ "\\arrayrulecolor{cinza} \\specialrule{1pt}{0.2em}{0.3em}\r\n" 
		+"\\end{tabular}\r\n" 
		+"\r\n" ;
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
		 +"\\usepackage{amsmath}\r\n" 
		 +"\\usepackage{amssymb,amsthm,amsmath,dsfont,mathrsfs}\r\n" 
		 +"\\usepackage[colorinlistoftodos]{todonotes}\r\n" 
		 +"\\usepackage{geometry}\r\n" 
		 +"\\geometry{\r\n" 
		 +"a4paper,\r\n" 
		 +"total={170mm,257mm},\r\n" 
		 +"left=15mm,\r\n" 
		 +"right=15mm,\r\n" 
		 +"top=15mm,\r\n" 
		 +"bottom=10mm,\r\n" 
		 +"}\r\n" 
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
		 +"\\definecolor{bluebell}{rgb}{0.39, 0.44, 1}\r\n" 
		 +"\\definecolor{laranja}{rgb}{1, 0.58, 0.33}\r\n" 
		 +"\\definecolor{verde}{rgb}{0, 0.46, 0.37}\r\n" 
		 +"\\definecolor{babypink}{rgb}{0.85, 0.65, 0.65} \r\n" 
		 +"\\definecolor{cinza}{rgb}{0.3, 0.3, 0.3} \r\n" 
		 +"\\definecolor{iris}{rgb}{0.39, 0.44, 1} \r\n" 
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
		 +"\\pagenumbering{gobble}\r\n" 
		 +"\\setstretch{1}\r\n"
		 +"\r\n";
	}
	
	public void listaExercicios()
	{
		// TODO migrar renderização do enunciado (parágrafos/alternativas) do novo Exercicio para LaTeX.
	}
	
	public void listaResolucao()
	{
		// TODO migrar renderização da resolução do novo Exercicio para LaTeX.
	}
	
	private String widthImagem()
	{
//		if(exercicio.isImagemQuadrada())
//			return "3cm";
//		else
		return "4.6cm";
	}
	
	private String getTextoResolucao(Exercicio conta,int index)
	{
		// TODO migrar para o novo Exercicio (conta.getResolucao()).
		return "";
	}
	
	private String addSpace(String texto)
	{
		if(!texto.contains("\\begin{array}"))
		{
			String linhas[]=texto.split("\\\\\\\\");
			String str,resultado="";
			for(int i = 0; i < linhas.length-1; i++)
			{
				str = linhas[i];
				if(linhas[i+1].contains("dfrac")||str.contains("dfrac")
				||linhas[i+1].contains("frac")||str.contains("frac")
				||linhas[i+1].contains("sqrt")||str.contains("sqrt")
				||linhas[i+1].contains("^")||str.contains("^"))
					str=str+" \\vspace{5px}";
				
				resultado+=str+" \\\\";
			}
			resultado+=linhas[linhas.length-1];
			return resultado;
		}
		return texto;
	}
	
	public void rodape()
	{
		latex+="\r\n" 
		+"\r\n" 
		+"\\setstretch{1}\r\n \r\n"
		+"\\scriptsize\r\n" 
		+"dobre ou recorte\r\n" 
		+"\r\n" 
		+"\\vspace{-0.1cm}\r\n" 
		+"\\hdashrule[0ex][x]{18.3cm}{1pt}{3mm 6pt}\r\n" 
		+"\r\n" 
		+"\r\n" 
		+"\\footnotesize\r\n" 
		+"\\BL{Gabarito} "
		+ "\\textcolor{iris}{Módulo "+exercicio.getAssunto().getModulo().getNome()+"} "
		+"\\BL{-} \\textcolor{verde}{ Assunto "+exercicio.getAssunto().getNome() +"} "
		+"\\BL{-} Nível "+exercicio.getNivelRomano()+" \r\n "
//		+ "
//		+ "\\BI{"+exercicio.getAssunto().getModulo().getNome()+"} "
//		+"\\BC{-} \\BV{"+exercicio.getAssunto().getNome()+"} \\BL{-} \\BC{"+exercicio.getNivelRomano()+"} \r\n" 
		+"\\vspace{0.2cm}\r\n" 
		+"\r\n" 
		+"\\scriptsize\r\n";
		
		latex+="\\begin{tabular}{";
		double size=0.93/(listaContas.size()/2);
		for(int i = 0; i < listaContas.size()/2; i++)
			latex+="p{"+size+"\\textwidth}";
		
		latex+="}\r\n"; 
		 
		// TODO migrar gabarito para o novo Exercicio (alternativa correta).
		latex+="\\end{tabular}\r\n"
		+"\r\n" 
		+"\r\n" 
		+"\r\n";
	}
	
	public void gerar()
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
		}
		catch(IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public ServletContext getServletContext()
	{
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	public static void main(String[] args)
	{
//		String nome="1-AdicaoNaturais.tex";
//		String endereco="C:\\Users\\maximovrm\\Documents\\latex";
//		GerarLatexExercicio gerarLatex=new GerarLatexExercicio(endereco,nome);
//		gerarLatex.gerarPDFExercicio(null,null);
//		gerarLatex.gerar();
	}
}
