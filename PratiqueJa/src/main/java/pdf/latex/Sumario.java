package pdf.latex;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import bean.download.Diretorio;
import modelo.configuracao.SistemaOperacional;

public class Sumario
{
	List<ItemSumario> itens;
	String latex;
	Diretorio diretorioBean;
	
	public Sumario(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean=diretorioBean;
	}

	public void gerarPDFExercicio(List<ItemSumario> itens, Resumo resumo)
	{
		this.itens=itens;
		diretorioBean.limparDiretorios();
		caput();
		sumario();
		resumo(resumo);
		
		latex+="\\end{document}";
		
		Arquivo arq=new Arquivo(diretorioBean.getEnderecoTex());
		
		arq.escrever(latex);
		arq.finalizar();
		
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
		 +"left=20mm,\r\n" 
		 +"top=15mm,\r\n" 
		 +"bottom=22mm,\r\n" 
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
		 +"\\setstretch{1.5}\r\n"
		 +"\r\n";
	}
	
	public void resumo(Resumo resumo)
	{
		latex+="\\vfill"
		+ "\\noindent \\textbf{Resumo do conteúdo:}\r\n"
		+ "\\begin{itemize}\r\n"
		+ "\\item \\textbf{Número de assuntos abordados: } \\BI{"+resumo.getAssuntos()+"}\r\n"
		+ "\\item \\textbf{Número de listas de Exercícios: } \\BI{"+resumo.getListas()+"}\r\n"
		+ "\\item \\textbf{Total de exercícios resolvidos: } \\BI{"+resumo.getExecicios()+"}\r\n"
		+ "\\item \\textbf{Total de questões comentadas: } \\BI{"+resumo.getQuestoes()+"}\r\n"
		+ "\\item \\textbf{Número total de páginas: } \\BI{"+resumo.getPaginas()+"}\r\n"
		+ "\\end{itemize}\r\n";
		
	}
	
	public void sumario()
	{
		latex+="\\begin{center}\r\n"
		+ "\\textbf{\\Huge \\textcolor{bluebell}{Sumário}}\r\n"
		+ "\\end{center}\r\n"
		+ "\\vspace{1cm}\r\n"
		+ "\\large\r\n";
		
		for (ItemSumario itemSumario : itens)
		{
			latex+="\\noindent \\textbf{"+itemSumario.index+". "+itemSumario.nome+"} \\dotfill \\ \\BI{"+itemSumario.pagina+"} \\\\";
		}
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
	
	public static void main(String[] args)
	{
//		String nome="1-AdicaoNaturais.tex";
//		String endereco="C:\\Users\\maximovrm\\Documents\\latex";
//		GerarLatexExercicio gerarLatex=new GerarLatexExercicio(endereco,nome);
//		gerarLatex.gerarPDFExercicio(null,null);
//		gerarLatex.gerar();
	}
}
