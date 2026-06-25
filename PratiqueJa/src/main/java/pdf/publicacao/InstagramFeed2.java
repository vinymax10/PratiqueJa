package pdf.publicacao;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Random;

import bean.download.Diretorio;
import matematica.GeradorExercicio;
import modelo.configuracao.SistemaOperacional;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.exercicio.ParagrafoExercicio;
import modelo.exercicio.ParagrafoResolucao;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.ImagemFile;
import pdf.util.Arquivo;
import util.CorAux;

/**
 * Post do Feed (formato retrato 4:5, menor que o Reel) com o mesmo visual do
 * {@link TikTok2} — foto de fundo vibrante como moldura, conteúdo sobre painel
 * branco translúcido, gancho de atenção, selo de nível e resolução destacada —
 * porém SEM alternativas: a página 1 traz apenas a pergunta. A classe antiga
 * {@link InstagramFeed} permanece intacta.
 */
public class InstagramFeed2
{
	/** Frases de gancho sorteadas por post (única parte sem dado no modelo). */
	private static final String[] GANCHOS = {
		"90\\% ERRAM ISSO",
		"VOCÊ CONSEGUE EM 10s?",
		"SEM CALCULADORA!",
		"SÓ GÊNIOS ACERTAM",
		"RÁPIDO: 10 SEGUNDOS!",
		"PENSE ANTES DE VIRAR",
		"QUASE NINGUÉM ACERTA",
		"DESAFIO DO DIA",
		"PARECE FÁCIL, MAS NÃO É",
		"CUIDADO COM A PEGADINHA",
		"RESOLVE DE CABEÇA?",
		"ISSO CAI NO ENEM",
		"TESTE SEU CÉREBRO",
		"SÓ 1 EM 10 ACERTA",
		"MOSTRE QUE VOCÊ SABE",
		"ACERTE EM 1 TENTATIVA",
		"DESAFIO RELÂMPAGO",
		"VOCÊ AINDA LEMBRA?",
		"NÃO VALE CALCULADORA",
		"COMENTA SUA RESPOSTA"
	};

	ExercicioPadrao exercicio;
	String latex;
	Exercicio conta;
	Diretorio diretorio;
	ProgramacaoPost programacaoPost;

	public InstagramFeed2(Diretorio diretorio)
	{
		super();
		this.diretorio = diretorio;
	}

	public void gerarPDFExercicio(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost)
	{
		this.exercicio = exercicio;
		this.programacaoPost = programacaoPost;
		gerarConta();

		diretorio.limparDiretorios();
		gerarImagem();

		caput();
		paginaExercicio();
		latex += "\\newpage\r\n";
		paginaResolucao();
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

	// ───────────────────────── imagens (logo / fundo / enunciado) ─────────────

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
			File origem = new File(diretorio.getConfig().getEndereco() + programacaoPost.getPadraoFeed().getEndereco());
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
			File origem = new File(diretorio.getConfig().getEndereco() + programacaoPost.getBackgroundFeed().getEndImagem());
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

	// ───────────────────────────── preâmbulo ─────────────────────────────────

	public void caput()
	{
		String corFonte  = CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorFonte());
		String corTitulo = CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorTitulo());
		String corNome   = CorAux.convertHexPorc(programacaoPost.getConfigPost().getCorNome());

		latex = "\\documentclass[12pt]{article}\r\n"
		+ "\\usepackage[utf8]{inputenc}\r\n"
		+ "\\usepackage[T1]{fontenc}\r\n"
		+ "\\usepackage{sansmathfonts}\r\n"
		+ "\\renewcommand*\\familydefault{\\sfdefault}\r\n"
		+ "\\usepackage{amsmath,amssymb,bm}\r\n"
		+ "\\usepackage{graphicx}\r\n"
		+ "\\usepackage{xcolor}\r\n"
		+ "\\usepackage{tikz}\r\n"
		+ "\\usetikzlibrary{calc,positioning}\r\n"
		+ "\\usepackage[paperwidth=259px,paperheight=323.75px,margin=0px]{geometry}\r\n"
		+ "\\pagestyle{empty}\r\n"
		+ "\r\n"
		+ "\\definecolor{iris}{rgb}{" + corTitulo + "}\r\n"
		+ "\\definecolor{cinza}{rgb}{" + corFonte + "}\r\n"
		+ "\\definecolor{nomecor}{rgb}{" + corNome + "}\r\n"
		+ "\\definecolor{laranja}{rgb}{0.87,0.48,0.25}\r\n"
		+ "\\definecolor{verde}{rgb}{0,0.54,0.44}\r\n"
		+ "\\definecolor{amarelo}{rgb}{0.96,0.66,0.13}\r\n"
		+ "\\definecolor{vermelho}{rgb}{0.86,0.27,0.27}\r\n"
		+ "\r\n"
		+ "\\tikzset{\r\n"
		+ "  nivel/.style={fill=" + corNivel() + ",text=white,rounded corners=4pt,inner xsep=6pt,inner ysep=2pt,font=\\bfseries\\scriptsize},\r\n"
		+ "  chip/.style={fill=iris,text=white,rounded corners=7pt,inner xsep=8pt,inner ysep=3pt,font=\\bfseries\\small},\r\n"
		+ "  hook/.style={text=laranja,font=\\bfseries,align=center},\r\n"
		+ "  cta/.style={fill=verde,text=white,rounded corners=9pt,inner xsep=12pt,inner ysep=5pt,font=\\bfseries\\small},\r\n"
		+ "}\r\n"
		+ "\\newcommand{\\painel}{%\r\n"
		+ "  \\node[inner sep=0] at (current page.center){\\includegraphics[width=\\paperwidth,height=\\paperheight]{background.png}};\r\n"
		+ "  \\coordinate (PNW) at ([shift={(14px,-14px)}]current page.north west);\r\n"
		+ "  \\coordinate (PSE) at ([shift={(-14px,14px)}]current page.south east);\r\n"
		+ "  \\coordinate (PNE) at ([shift={(-14px,-14px)}]current page.north east);\r\n"
		+ "  \\coordinate (PN) at ([yshift=-14px]current page.north);\r\n"
		+ "  \\coordinate (PS) at ([yshift=14px]current page.south);\r\n"
		+ "  \\fill[white,opacity=0.82,rounded corners=16pt] (PNW) rectangle (PSE);\r\n"
		+ "}\r\n"
		+ "\\begin{document}\r\n\r\n";
	}

	// ───────────────────────────── página 1 ──────────────────────────────────

	public void paginaExercicio()
	{
		String enunciado = enunciado(true);

		// Sem alternativas: o enunciado é o protagonista, centralizado na página.
		latex += "\\begin{tikzpicture}[remember picture,overlay]\r\n"
		+ "\\painel\r\n"
		+ cabecalho(labelNivel())
		+ "\\node[chip,anchor=north] (chip) at ([yshift=-34px]PN){" + sizeChip() + exercicio.getAssunto().getNome() + "};\r\n"
		+ "\\node[hook,below=5px of chip] (hook){" + gancho() + "};\r\n"
		+ "\\node[anchor=center,align=center,text width=216px,text=iris,font=\\bfseries] (enun)"
		+ " at ([yshift=-178px]current page.north){" + fontsize(ptEnunciado(enunciado)) + "\r\n" + enunciado + "};\r\n"
		+ "\\node[cta,anchor=south] at ([yshift=14px]PS){Comente sua resposta!};\r\n"
		+ "\\end{tikzpicture}\r\n";
	}

	// ───────────────────────────── página 2 ──────────────────────────────────

	public void paginaResolucao()
	{
		String enunciado = enunciado(false);
		String texto = getTextoResolucao();

		latex += "\\begin{tikzpicture}[remember picture,overlay]\r\n"
		+ "\\painel\r\n"
		+ cabecalho("RESOLUÇÃO")
		+ "\\node[chip,anchor=north] (chip) at ([yshift=-34px]PN){" + sizeChip() + exercicio.getAssunto().getNome() + "};\r\n"
		+ "\\node[anchor=north,align=center,text width=216px,text=cinza,font=\\bfseries] (enun)"
		+ " at ([yshift=-6px]chip.south){" + fontsize(Math.max(8, ptEnunciado(enunciado) - 5)) + "\r\n" + enunciado + "};\r\n"
		+ "\\node[anchor=north,align=center,text width=216px,text=cinza,font=\\bfseries] (res)"
		+ " at ([yshift=-12px]enun.south){" + fontsize(ptResolucao(texto)) + "\r\n"
		+ "\\setlength{\\lineskip}{4pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{4pt}\r\n" + texto + "};\r\n"
		+ resposta()
		+ "\\end{tikzpicture}\r\n";
	}

	/** Cabeçalho comum: logo + selo (nível ou "RESOLUÇÃO") + marca de teste. */
	private String cabecalho(String selo)
	{
		String s = "\\node[anchor=north west,inner sep=0] at ([shift={(10px,-10px)}]PNW){\\includegraphics[width=66px]{logo.png}};\r\n"
		+ "\\node[nivel,anchor=north east] at ([shift={(-10px,-12px)}]PNE){" + selo + "};\r\n";
		if(programacaoPost.isTeste())
			s += "\\node[anchor=north west,font=\\bfseries\\tiny,text=cinza] at ([shift={(12px,-38px)}]PNW){Imagem de Teste};\r\n";
		return s;
	}

	/** Pílula verde com a resposta (sem alternativas: só o valor correto). */
	private String resposta()
	{
		if(conta.correta() == null)
			return "";

		return "\\node[fill=verde,text=white,rounded corners=9pt,inner xsep=14pt,inner ysep=6pt,font=\\bfseries,anchor=south]"
		+ " at ([yshift=12px]PS){Resposta:\\; " + escapar(conta.correta().getTexto()) + "\\;$\\checkmark$};\r\n";
	}

	// ───────────────────────── enunciado / resolução ─────────────────────────

	private String enunciado(boolean primeiraPage)
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoExercicio paragrafo : conta.getParagrafos())
		{
			if(paragrafo.isTipoImagem())
				sb.append("\\includegraphics[width=" + widthImagem(primeiraPage) + "]{"
					+ diretorio.getConfig().getImagens() + "/" + nomeImagem(paragrafo) + "}\r\n\r\n");
			else if(paragrafo.getTexto() != null && !paragrafo.getTexto().isBlank())
				sb.append(escapar(paragrafo.getTexto())).append("\r\n\r\n");
		}
		return sb.toString();
	}

	private String widthImagem(boolean primeiraPage)
	{
		return primeiraPage ? "120px" : "90px";
	}

	/** Junta os parágrafos de resolução, um por linha (\\ em modo texto). */
	private String getTextoResolucao()
	{
		StringBuilder sb = new StringBuilder();
		for(ParagrafoResolucao paragrafo : conta.getResolucaoParagrafos())
		{
			if(paragrafo.getTexto() == null || paragrafo.getTexto().isBlank())
				continue;
			if(sb.length() > 0)
				sb.append(" \\\\\r\n");
			sb.append(escapar(paragrafo.getTexto()));
		}
		return sb.toString();
	}

	// ─────────────────────────── tamanhos / cores ────────────────────────────

	/** Corpo do enunciado na página 1 (protagonista, sem alternativas). */
	private int ptEnunciado(String texto)
	{
		int len = texto.length();
		if(len <= 25)  return 22;
		if(len <= 50)  return 18;
		if(len <= 90)  return 14;
		if(len <= 150) return 11;
		if(len <= 240) return 9;
		return 8;
	}

	/** Corpo da resolução na página 2 (página do feed é baixa → fonte menor). */
	private int ptResolucao(String texto)
	{
		int len = texto.length();
		if(len <= 100) return 12;
		if(len <= 200) return 10;
		if(len <= 350) return 9;
		if(len <= 550) return 8;
		if(len <= 800) return 7;
		return 6;
	}

	private String fontsize(int pt)
	{
		return "\\fontsize{" + pt + "}{" + (pt + 3) + "}\\selectfont";
	}

	/** Reduz a fonte do chip quando o nome do assunto é longo. */
	private String sizeChip()
	{
		int len = exercicio.getAssunto().getNome().length();
		if(len <= 24)
			return "";
		if(len <= 32)
			return "\\footnotesize ";
		return "\\scriptsize ";
	}

	/** Cor do selo de nível: Fácil=verde, Médio=amarelo, Difícil=vermelho. */
	private String corNivel()
	{
		if(exercicio.getNivel() == Nivel.Nivel1)
			return "verde";
		if(exercicio.getNivel() == Nivel.Nivel2)
			return "amarelo";
		return "vermelho";
	}

	/** Rótulo do selo de nível. */
	private String labelNivel()
	{
		if(exercicio.getNivel() == Nivel.Nivel1)
			return "NÍVEL FÁCIL";
		if(exercicio.getNivel() == Nivel.Nivel2)
			return "NÍVEL MÉDIO";
		return "NÍVEL DIFÍCIL";
	}

	/** Gancho sorteado do pool. */
	private String gancho()
	{
		return GANCHOS[new Random().nextInt(GANCHOS.length)];
	}

	/**
	 * Escapa caracteres que nunca aparecem "crus" de forma legítima no texto: "%"
	 * (comentário) e "$" (alterna math); converte "\\<dígito>" (erro de "/") em "/".
	 */
	private String escapar(String texto)
	{
		if(texto == null)
			return "";
		return texto.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		            .replaceAll("(?<!\\\\)%", "\\\\%")
		            .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	// ──────────────────────────── compilação ─────────────────────────────────

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
			pb = new ProcessBuilder("sudo", "pdftoppm", "-png", "-r", "300", diretorio.getConfig().getNome() + ".pdf",
			diretorio.getConfig().getNome()).inheritIO().directory(new File(diretorio.getEndereco()));
		else
			pb = new ProcessBuilder("pdftoppm", "-png", "-r", "300", diretorio.getConfig().getNome() + ".pdf",
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
