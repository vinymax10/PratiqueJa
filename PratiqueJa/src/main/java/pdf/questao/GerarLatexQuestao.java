package pdf.questao;

import java.io.File;
import pdf.util.Arquivo;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import modelo.assuntocurso.Modulo;
import modelo.configuracao.SistemaOperacional;
import modelo.questao.Alternativa;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;

public class GerarLatexQuestao
{
	ConfigDownload configDownload;
	String latex;
	List<Questao> questoes;
	String assuntoTexto;
	String moduloTexto;
	Diretorio diretorioBean;
	
	public GerarLatexQuestao(Diretorio diretorioBean)
	{
		super();
		this.diretorioBean=diretorioBean;
	}

	public void gerarPDFQuestoes(List<Questao> questoes, ConfigDownload configDownload)
	{
		this.questoes=questoes;
		this.configDownload=configDownload;
		identificarAssunto();
		
		diretorioBean.limparDiretorios();
		
		gerarImagens();
		caput();
		cabecalho();
		listaQuestoes();
		if(configDownload.isRespostas())
			rodape();
		
		latex+="\\end{multicols*}\r\n";
		
		if(configDownload.isResolucao())
		{
			latex+="\\newpage \r\n";
			cabecalho();
			listaQuestoesResolucoes();
			latex+="\\end{multicols*}\r\n";
		}
		
		latex+="\\end{document}";
		
		Arquivo arq=new Arquivo(diretorioBean.getEnderecoTex());
		
		arq.escrever(latex);
		arq.finalizar();
	}
	
	private void identificarAssunto()
	{
		List<Modulo> modulos=new ArrayList<Modulo>();
		if(questoes.size()>0)
			assuntoTexto=questoes.get(0).getAssuntoCurso().getNome();
		
		for(Questao questao : questoes)
		{
			if(!assuntoTexto.equals(questao.getAssuntoCurso().getNome()))
				assuntoTexto="Diversos";
			
			if(!modulos.contains(questao.getAssuntoCurso().getModulo()))
				modulos.add(questao.getAssuntoCurso().getModulo());
		}
		
		Collections.sort(modulos, (a, b) -> 
		{
			return a.ordinal()-b.ordinal();
		});
		
		moduloTexto="";
		for(int i = 0; i < modulos.size(); i++)
		{
			Modulo modulo = modulos.get(i);
			if(i>0)
				moduloTexto+=", ";
			
			moduloTexto+=modulo.getNome();
		}
	}
	
// 	private void limparDiretorios()
//	{
//		gerarDiretorios(endereco);
//		gerarDiretorios(endereco+"/"+imagens);
//		gerarDiretorios(endereco+"/"+imagensResolucao);
//	}
//	
//	private void gerarDiretorios(String endereco)
//	{
//		File theDir = new File(endereco);
//		if(!theDir.exists())
//			theDir.mkdirs();
//
//		if(theDir.isDirectory())
//		{
//			File[] files = theDir.listFiles();
//			for(File file : files)
//			{
//				file.delete();
//			}
//		}
//	}
	
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
	
	private void gerarImagens()
	{
		gravarLogo();
		
		Questao questao;
		
		File outputFile;
		OutputStream outputStream;
		
		for(int i = 0; i < questoes.size(); i++)
		{
			questao=questoes.get(i);
			try
			{
				for(Paragrafo paragrafo : questao.getParagrafos())
				{
					if(paragrafo.getImagemFile()!=null)
					{
						Blob blob=paragrafo.getImagemFile().getFile();
						outputFile = new File(diretorioBean.getEnderecoImagens()+paragrafo.getId()+".jpeg");
						outputStream = new FileOutputStream(outputFile);
						outputStream.write(blob.getBytes(1,(int)blob.length()));
					}
				}
			}
			catch(IOException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	

	public void cabecalho()
	{
		String nome="";
		if(configDownload.isIdentificacao())
			nome=configDownload.getUsuario().getNome();
		
		latex+=	
		"\\setstretch{1} \r\n" 
		+"\\normalsize\r\n" 
		+"\\setlength{\\tabcolsep}{0em} \r\n"
		+"\\setlength\\parindent{0pt} \r\n"
		+"\\begin{tabular}{p{0.19\\textwidth} p{0.61\\textwidth} p{0.2\\textwidth}}\r\n" 
		+"\\vspace{-0.5cm} \\includegraphics[width=2.7cm]{logo.png}  & "
		+"\\BB{\\Large Questões } & Nota:    \\\\  \r\n" 
		+"\\vspace{-0.5cm} \\small \\BC{pratiqueja.com} & \\vspace{-0.5cm} Nome: "+nome+" & \\vspace{-0.5cm} Data:  \\\\ "
		+"\\multicolumn{3}{l}{\\footnotesize "
		+ "\\textcolor{iris}{Módulo "+moduloTexto+"}"
		+" \\BL{-} \\textcolor{verde}{Assunto "+assuntoTexto +"} }\\\\"
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
		 +"bottom=15mm,\r\n" 
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
		 +"\\definecolor{laranja}{rgb}{0.87, 0.48, 0.25}\r\n" 
		 +"\\definecolor{verde}{rgb}{0, 0.54, 0.44}\r\n" 
		 +"\\definecolor{babypink}{rgb}{1, 0.42, 0.52} \r\n" 
		 +"\\definecolor{cinza}{rgb}{0.3, 0.3, 0.3} \r\n" 
		 +"\\definecolor{iris}{rgb}{0.39, 0.44, 1} \r\n" 
		 +"\\definecolor{babyblue}{rgb}{0.13, 0.59, 0.95} \r\n"
		 +"\r\n" 
		 +"\\newcommand{\\C}[1]{\\textcolor{cinza}{#1}}\r\n" 
		 +"\\newcommand{\\BI}[1]{\\textbf{\\textcolor{iris}{#1}}}\r\n" 
		 +"\\newcommand{\\BL}[1]{\\textbf{\\textcolor{laranja}{#1}}}\r\n" 
		 +"\\newcommand{\\BV}[1]{\\textbf{\\textcolor{verde}{#1}}}\r\n" 
		 +"\\newcommand{\\BC}[1]{\\textbf{\\textcolor{cinza}{#1}}}\r\n" 
		 +"\\newcommand{\\BB}[1]{\\textbf{\\textcolor{babyblue}{#1}}}\r\n"
		 +"\r\n" 
		 +"\\color{cinza} \r\n \r\n"
		 +"\\begin{document}\r\n" 
		 +"\\pagenumbering{gobble}\r\n" 
		 +"\r\n";
	}
	
	public void listaQuestoes()
	{
		latex+="\\vspace{-8px}"
		+"\\footnotesize\r\n"
		+"\\setstretch{1.3}\r\n"
		+"\\begin{multicols*}{2}\r\n";
		Questao questao;

		for(int i = 0; i < questoes.size(); i++)
		{
			questao=questoes.get(i);
			
			latex+="\\footnotesize \\BL{"+(i+1)+")} \\BB{Q"+questao.getId()+"} \\BB{Ano:}"
			+ " "+questao.getAno().getNome()+" "
			+ " \\BB{Banca:} "+questao.getBanca().getSigla()
			+ " \\BB{Orgão:} "+questao.getOrgao().getSigla()+" \r\n \r\n \\footnotesize \r\n \r\n";
			
			for(Paragrafo paragrafo : questao.getParagrafos())
			{
				if(paragrafo.getImagemFile()!=null)
					latex+="\\includegraphics[valign=t,scale=0.5, max width=\\columnwidth]{"+diretorioBean.getConfigLatex().getImagens()+"/"+paragrafo.getId()+".jpeg}"
					+ " \r\n \\vspace{0.3cm} \r\n \r\n";
				else
					latex+=getTexto(paragrafo.getTexto())+" \r\n \r\n";
			}
			
			for(int j = 0; j < questao.getAlternativas().size(); j++)
			{
				Alternativa alternativa=questao.getAlternativas().get(j);
				latex+=addSpaceAlternativa("\\BC{"+((char)(j+65))+")} "+getTexto(alternativa.getTexto())) +"\r\n \r\n";
			}
			latex+="\\vspace{0.5cm}\r\n";
		}
	}
	
	private String getTexto(String texto)
	{
		if(texto!=null&&!texto.equals(""))
			return texto.replaceAll("\\$", "\\\\\\$").replaceAll("%", "\\\\%");
		else
			return "";
	}
	
	public void listaQuestoesResolucoes()
	{
		latex+="\\vspace{-8px}"
		+"\\footnotesize\r\n"
		+"\\setstretch{1.5}\r\n"
		+"\\begin{multicols*}{2}\r\n";
		Questao questao;

		for(int i = 0; i < questoes.size(); i++)
		{
			questao=questoes.get(i);
			
			latex+="\\footnotesize \\BL{"+(i+1)+")} \\BB{Q"+questao.getId()+"} \\BB{Ano:}"
			+ " "+questao.getAno().getNome()+" "
			+ " \\BB{Banca:} "+questao.getBanca().getSigla()
			+ " \\BB{Orgão:} "+questao.getOrgao().getSigla()+" \r\n \r\n \\footnotesize \r\n \r\n";
			
			for(Paragrafo paragrafo : questao.getParagrafos())
			{
				if(paragrafo.getImagemFile()!=null)
					latex+="\\includegraphics[valign=t,scale=0.5, max width=\\columnwidth]{"+diretorioBean.getConfigLatex().getImagens()+"/"+paragrafo.getId()+".jpeg}"
					+ " \r\n \\vspace{0.3cm} \r\n \r\n";
				else
					latex+=getTexto(paragrafo.getTexto())+" \r\n \r\n";
			}
			
			for(int j = 0; j < questao.getAlternativas().size(); j++)
			{
				Alternativa alternativa=questao.getAlternativas().get(j);
				latex+=addSpaceAlternativa("\\BC{"+((char)(j+65))+")} "+getTexto(alternativa.getTexto()))+" \r\n \r\n";
			}
			latex+="\\vspace{0.3cm}\r\n \r\n";
			latex+="\\footnotesize\r\n \r\n";

			latex+="\\BL{Resolução}\r\n \r\n";
			latex+="\\vspace{5px}\r\n \r\n";
			
			latex+=addSpace(questao.getResolucao())+"\r\n \r\n";
			latex+="\\vspace{5px}\r\n \r\n";

			latex+="\\BV{Alternativa "+questao.correta().getLetra()+"} \r\n \r\n";

			latex+="\\vspace{0.5cm}\r\n \r\n";

		}
	}
	
	private String addSpaceAlternativa(String texto)
	{
		if(texto!=null)
		{
			if(texto.contains("dfrac"))
				texto=texto+" \\vspace{5px}";
		}
		return texto;
	}
	
	private String addSpace(String texto)
	{
		String str,resultado="";
		if(texto!=null)
		{
			String linhas[]=texto.split("\\\\\\\\");
			for(int i = 0; i < linhas.length-1; i++)
			{
				str = linhas[i];
				if(linhas[i+1].contains("dfrac")||str.contains("dfrac"))
					str=str+" \\vspace{5px}";
				
				resultado+=str+" \\\\";
			}
			resultado+=linhas[linhas.length-1];
		}
		return resultado;
	}
	
	public void rodape()
	{
		latex+="\\vfill\r\n" 
		+"\\scriptsize\r\n" 
		+"\\textcolor{cinza}{ \\rule{8.5cm}{0.4mm} }\r\n"
		+"\\footnotesize\r\n" 
		+"\\BB{Gabarito Questões} \\vspace{0.2cm} \r\n"
		+"\r\n" 
		+"\\scriptsize\r\n";
		
		latex+="\\begin{tabular}{";
		double size=0.086;
		for(int i = 0; i < 5; i++)
			latex+="p{"+size+"\\textwidth}";
		
		latex+="}\r\n"; 
		 
		Questao questao;
		String resultado="";
		
		for(int i = 0; i < questoes.size(); i++)
		{
			questao=questoes.get(i);
			resultado="\\text{"+questao.correta().getLetra()+"}";
			
			latex+="\\BL{"+(i+1)+")} ~"+"$"+resultado+"$";
			if((i+1)%5==0)
				latex+="\\\\ \r\n";
			else
				latex+="&";
		}
		latex+="\\end{tabular}\r\n"
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
	
	public static void main(String[] args)
	{
//		String nome="1-AdicaoNaturais.tex";
//		String endereco="C:\\Users\\maximovrm\\Documents\\latex";
//		GerarLatexQuestao gerarLatex=new GerarLatexQuestao(endereco,nome);
//		gerarLatex.gerarPDFQuestoes(null,null);
//		gerarLatex.gerar();
	}
}
