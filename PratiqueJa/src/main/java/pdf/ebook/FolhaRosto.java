package pdf.ebook;

import java.io.File;
import pdf.util.Arquivo;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.time.LocalDate;

import bean.download.Diretorio;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import modelo.assuntocurso.Modulo;
import modelo.configuracao.SistemaOperacional;

public class FolhaRosto
{
	String latex;
	Diretorio diretorioBean;
	
	public FolhaRosto(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean=diretorioBean;
	}

	public void gerarPDFExercicio(Modulo modulo, int edicao)
	{
		diretorioBean.limparDiretorios();
		gravarLogo();
		caput();
		folhaRosto(modulo,edicao);
		fichaIdentificacao();
		
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
	
	public void fichaIdentificacao()
	{
		latex+=""
		+ "\\begin{titlepage}\r\n"
		+ "\\pagenumbering{gobble}\r\n"
		+ "\\setstretch{2}\r\n"
		+ "\\begin{center}\r\n"
		+ "\\textbf{\\Huge \\textcolor{bluebell}{ Ficha de Identificação}}\r\n"
		+ "\\end{center}\r\n"
		+ "\\vspace{1cm}\r\n"
		+ "\\large\r\n"
		+ "\\bfseries\r\n"
		+ "\\begin{tblr}{\r\n"
		+ "colspec={p{.98\\textwidth}}}\r\n"
		+ "Nome completo:  \\\\ \\hline[2pt,laranja]\r\n"
		+ "Curso/Série: \\\\ \\hline[2pt,laranja]\r\n"
		+ "Instituição: \\\\ \\hline[2pt,laranja]\r\n"
		+ "Endereço: \\\\ \\hline[2pt,laranja]\r\n"
		+ "Cidade/Estado: \\\\ \\hline[2pt,laranja]\r\n"
		+ "Telefone: \\\\ \\hline[2pt,laranja]\r\n"
		+ "Data: \\\\ \\hline[2pt,laranja]\r\n"
		+ "\\end{tblr}\r\n"
		+ "\\end{titlepage}\r\n";		
	}
	
	public void folhaRosto(Modulo modulo, int edicao)
	{
		LocalDate hoje=LocalDate.now();
		latex+=""
		+ "\\begin{titlepage}\r\n"
		+ "\\begin{center}\r\n"
		+ "\\vspace*{1cm}\r\n"
		+ "\\includegraphics[width=0.3\\textwidth]{logo.png}\\\\[3cm]\r\n "
		+ "\\textbf{\\Huge Pratique Já}\\\\[0.5cm]\r\n"
		+ "\\textbf{\\Large Matemática com Exercícios}\\\\[2cm]\r\n"
		+ "\\textbf{\\Large Módulo: "+modulo.getNome()+"}\\\\[2cm]\r\n"
		+ "\\textbf{\\Large Autor: Equipe Pratique Já}\\\\[3cm]\r\n"
		+ "\\textbf{\\Large "+edicao+"ª edição}\\\\[3cm]\r\n"
		+ "\\textbf{\\Large São José dos Campos - SP}\\\\[1cm]\r\n"
		+ "\\textbf{\\Large "+hoje.getYear()+"}\r\n"
		+ "\\end{center}\r\n"
		+ "\\end{titlepage}\r\n";
	}
	
	private void gravarLogo()
	{
		File logo = new File(diretorioBean.getEnderecoLogo());
		if(!logo.exists())
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        File origem = new File(externalContext.getRealPath("image") + "/logo.png");
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
