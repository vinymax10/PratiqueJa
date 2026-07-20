package auditoria;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import matematica.GeradorExercicio;
import modelo.academico.Modulo;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.Nivel;
import modelo.exercicio.ParagrafoExercicio;
import modelo.questao.ImagemFile;

/**
 * Gera, por assunto, uma lista para o grupo de WhatsApp: apostila de <b>teoria</b> (o PDF já
 * compilado em {@code tex-new/<modulo>/<pasta>/<pasta>.pdf}) no início, seguida de <b>30
 * exercícios</b> (10 por nível) com alternativas e, ao fim, o <b>gabarito com resolução completa</b>.
 *
 * <p>A teoria é embutida via {@code \\includepdf} (pacote {@code pdfpages}) — teoria + exercícios +
 * gabarito saem num único PDF compilado pelo XeLaTeX, sem etapa de merge externa.
 *
 * <p>Roda fora do container (JDBC puro no MySQL local, como os demais utilitários de {@code auditoria}).
 *
 * <pre>
 * Um assunto (padrão EquacaoSegundoGrau):
 *   mvn -q compile exec:java -Dexec.mainClass=auditoria.GeradorListaGrupoWhatsapp
 * Um assunto específico (pela chave):
 *   mvn -q compile exec:java -Dexec.mainClass=auditoria.GeradorListaGrupoWhatsapp -Dexec.args="Racionais"
 * Todos:
 *   mvn -q compile exec:java -Dexec.mainClass=auditoria.GeradorListaGrupoWhatsapp -Dexec.args="ALL"
 * </pre>
 */
public class GeradorListaGrupoWhatsapp
{
	private static final String JDBC_URL =
		"jdbc:mysql://localhost:3306/pratiqueja?serverTimezone=America/Sao_Paulo&useUnicode=true&characterEncoding=UTF-8";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "tiquinho10";

	private static final Path   TEX_NEW    = Path.of("tex-new");
	private static final Path   STY_DIR    = TEX_NEW; // pratiqueja.sty fica aqui
	private static final String XELATEX    =
		"C:/Program Files/MiKTeX/miktex/bin/x64/xelatex.exe";
	private static final String PDFUNITE   =
		"C:/Program Files/MiKTeX/miktex/bin/x64/miktex-pdfunite.exe";
	private static final Path   OUTPUT_DIR = Path.of("C:/Users/maximovrm/Documents/pdf/listaGrupoWhatsapp");
	private static final Path   WORK_ROOT  = TEX_NEW.resolve("listas/whatsapp_work");

	private static final int EXERCICIOS_POR_NIVEL = 10;

	// tex-new usa nomes de pasta que não batem com Assunto.chave nestes 2 casos (o resto bate por
	// comparação case-insensitive). Mesma tabela do ImportadorApostilasTexNew (aqui: chave -> pasta).
	private static final Map<String, String> CHAVE_PARA_PASTA = Map.of(
		"planocartesiano",        "planoCoordenado",
		"inequacoessegundograu",  "inequacoes2grau"
	);

	public static void main(String[] args) throws Exception
	{
		Files.createDirectories(OUTPUT_DIR);
		Files.createDirectories(WORK_ROOT);

		try(Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS))
		{
			List<String> chaves;
			if(args.length > 0 && args[0].equalsIgnoreCase("ALL"))
				chaves = listarChaves(con);
			else if(args.length > 0)
				chaves = List.of(args[0]);
			else
				chaves = List.of("EquacaoSegundoGrau"); // primeiro assunto (prova de conceito)

			int ok = 0, falhas = 0;
			for(String chave : chaves)
			{
				try
				{
					Path pdf = gerarAssunto(con, chave);
					System.out.println("OK  " + chave + " -> " + pdf);
					ok++;
				}
				catch(Exception e)
				{
					System.out.println("ERRO " + chave + ": " + e.getMessage());
					e.printStackTrace();
					falhas++;
				}
			}
			System.out.println("\n==== gerados: " + ok + " | falhas: " + falhas + " ====");
		}
	}

	// ── Dados do assunto (via JDBC) ───────────────────────────────────────

	private static List<String> listarChaves(Connection con) throws SQLException
	{
		List<String> chaves = new ArrayList<>();
		String sql = "SELECT DISTINCT a.chave FROM Assunto a "
			+ "JOIN ExercicioPadrao ep ON ep.assunto_id = a.id ORDER BY a.chave";
		try(PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery())
		{
			while(rs.next())
				chaves.add(rs.getString(1));
		}
		return chaves;
	}

	private static AssuntoData carregar(Connection con, String chave) throws SQLException
	{
		AssuntoData d = new AssuntoData();
		String sql = "SELECT a.nome, a.chave, a.modulo, ep.nivel, ep.nome "
			+ "FROM Assunto a JOIN ExercicioPadrao ep ON ep.assunto_id = a.id "
			+ "WHERE LOWER(a.chave) = LOWER(?) ORDER BY ep.nivel";
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, chave);
			try(ResultSet rs = ps.executeQuery())
			{
				while(rs.next())
				{
					d.nome   = rs.getString(1);
					d.chave  = rs.getString(2);
					d.modulo = Modulo.values()[rs.getInt(3)];
					Nivel nivel = Nivel.values()[rs.getInt(4)];
					String gerador = rs.getString(5);
					d.geradorPorNivel.put(nivel, fqcn(d, gerador));
				}
			}
		}
		if(d.chave == null)
			throw new IllegalArgumentException("Assunto não encontrado: " + chave);
		return d;
	}

	/** FQCN do gerador, igual a ExercicioPadrao.getClasse(): matematica.<modulo>.<chave>.<nome>. */
	private static String fqcn(AssuntoData d, String nomeGerador)
	{
		return "matematica." + d.modulo.name().toLowerCase(Locale.ROOT) + "."
			+ d.chave.toLowerCase(Locale.ROOT) + "." + nomeGerador;
	}

	private static Path localizarTeoria(AssuntoData d) throws IOException
	{
		Path moduloDir = TEX_NEW.resolve(d.modulo.name().toLowerCase(Locale.ROOT));
		String pastaAlias = CHAVE_PARA_PASTA.get(d.chave.toLowerCase(Locale.ROOT));
		try(Stream<Path> dirs = Files.list(moduloDir))
		{
			Path pasta = dirs.filter(Files::isDirectory)
				.filter(p -> {
					String nome = p.getFileName().toString();
					return nome.equalsIgnoreCase(d.chave)
						|| (pastaAlias != null && nome.equalsIgnoreCase(pastaAlias));
				})
				.findFirst()
				.orElseThrow(() -> new IOException("Pasta de teoria não encontrada para " + d.chave + " em " + moduloDir));
			Path pdf = pasta.resolve(pasta.getFileName().toString() + ".pdf");
			if(!Files.exists(pdf))
				throw new IOException("PDF de teoria não encontrado: " + pdf);
			return pdf;
		}
	}

	// ── Geração ───────────────────────────────────────────────────────────

	private static Path gerarAssunto(Connection con, String chave) throws Exception
	{
		AssuntoData d = carregar(con, chave);
		Path teoria = localizarTeoria(d);

		Path workDir = WORK_ROOT.resolve(d.chave.toLowerCase(Locale.ROOT));
		Files.createDirectories(workDir);
		copiarSty(workDir);
		Files.copy(teoria, workDir.resolve("teoria.pdf"), StandardCopyOption.REPLACE_EXISTING);

		// Gera 10 exercícios por nível (na ordem N1, N2, N3), numerados 1..30 continuamente.
		List<NivelExercicios> niveis = new ArrayList<>();
		for(Nivel nivel : new Nivel[]{Nivel.Nivel1, Nivel.Nivel2, Nivel.Nivel3})
		{
			String classe = d.geradorPorNivel.get(nivel);
			if(classe == null) continue;
			niveis.add(new NivelExercicios(nivel, gerarExercicios(classe, EXERCICIOS_POR_NIVEL)));
		}

		String tex = construirTex(d, niveis, workDir);
		String base = "lista_" + d.chave.toLowerCase(Locale.ROOT);
		Path texFile = workDir.resolve(base + ".tex");
		Files.writeString(texFile, tex, StandardCharsets.UTF_8);

		compilar(texFile);

		Path pdfExercicios = workDir.resolve(base + ".pdf");
		if(!Files.exists(pdfExercicios))
			throw new IOException("XeLaTeX não produziu o PDF: " + pdfExercicios);

		// Junta teoria (apostila) + exercícios/gabarito num único PDF final.
		Path pdfFinal = workDir.resolve(base + "_final.pdf");
		Path teoriaWork = workDir.resolve("teoria.pdf");
		unir(teoriaWork, pdfExercicios, pdfFinal);

		Path destino = OUTPUT_DIR.resolve(d.chave + ".pdf");
		Files.copy(pdfFinal, destino, StandardCopyOption.REPLACE_EXISTING);
		return destino;
	}

	private static List<Exercicio> gerarExercicios(String classeNome, int quantidade)
	{
		List<Exercicio> lista = new ArrayList<>();
		Class<?> classe;
		try
		{
			classe = Class.forName(classeNome);
		}
		catch(ClassNotFoundException e)
		{
			throw new IllegalStateException("Gerador não encontrado: " + classeNome, e);
		}
		for(int i = 0; i < quantidade; i++)
		{
			try
			{
				GeradorExercicio g = (GeradorExercicio) classe.getDeclaredConstructor().newInstance();
				lista.add(g.gerar());
			}
			catch(ReflectiveOperationException e)
			{
				throw new IllegalStateException("Falha ao instanciar " + classeNome, e);
			}
		}
		return lista;
	}

	private static void copiarSty(Path workDir) throws IOException
	{
		Path origem = STY_DIR.resolve("pratiqueja.sty");
		if(Files.exists(origem))
			Files.copy(origem, workDir.resolve("pratiqueja.sty"), StandardCopyOption.REPLACE_EXISTING);
	}

	// ── Montagem do .tex ─────────────────────────────────────────────────

	private static String construirTex(AssuntoData d, List<NivelExercicios> niveis, Path workDir)
	{
		String categoria = "Matemática · " + d.modulo.getNome();
		StringBuilder sb = new StringBuilder();
		sb.append(preambulo(d));
		sb.append("\\begin{document}\n\\pagenumbering{arabic}\n\\setstretch{1.2}\n\\color{bodytext}\n\n");

		// Remove os textos de continuação do longtblr ("Continued on next page"/"(Continued)").
		sb.append("\\DefTblrTemplate{caption}{default}{}\n")
		  .append("\\DefTblrTemplate{conthead}{default}{}\n")
		  .append("\\DefTblrTemplate{conthead-text}{default}{}\n")
		  .append("\\DefTblrTemplate{contfoot}{default}{}\n")
		  .append("\\DefTblrTemplate{contfoot-text}{default}{}\n\n");

		// A teoria (apostila já compilada) é anexada no início via pdfunite após a compilação.

		// Lista contínua: todos os exercícios (10 por nível, em ordem N1→N2→N3) numa única sequência,
		// sem cabeçalho por nível e sem quebra de página entre níveis. Numeração contínua 1..N.
		List<Exercicio> todos = new ArrayList<>();
		for(NivelExercicios ne : niveis) todos.addAll(ne.exercicios);

		sb.append("\\listheader{").append(esc(d.nome)).append("}{Níveis I a III}{").append(categoria)
			.append("}{").append(todos.size()).append(" questões · marque a alternativa correta}\n\n");

		sb.append(abrirGrade());
		for(int i = 0; i < todos.size(); i += 2)
		{
			String esq = macroExercicio(i + 1, todos.get(i), workDir);
			String dir = (i + 1 < todos.size()) ? macroExercicio(i + 2, todos.get(i + 1), workDir) : "";
			sb.append(esq).append(" &\n").append(dir).append(" \\\\\n");
		}
		sb.append(fecharGrade());
		sb.append("\n");

		// Gabarito com resolução completa (numeração contínua 1..N).
		sb.append("\\clearpage\n");
		sb.append("\\listheader{").append(esc(d.nome)).append("}{Gabarito}{").append(categoria)
			.append("}{Resolução completa}\n\n");
		sb.append(abrirGradeGabarito());
		for(int i = 0; i < todos.size(); i += 2)
		{
			String esq = macroGabarito(i + 1, todos.get(i));
			String dir = (i + 1 < todos.size()) ? macroGabarito(i + 2, todos.get(i + 1)) : "";
			sb.append(esq).append(" &\n").append(dir).append(" \\\\\n");
		}
		sb.append(fecharGradeGabarito());

		sb.append("\n\\end{document}\n");
		return sb.toString();
	}

	private static String preambulo(AssuntoData d)
	{
		return
			"% Gerado por GeradorListaGrupoWhatsapp — não editar manualmente\n"
			+ "\\documentclass[12pt]{article}\n"
			+ "\\usepackage{pratiqueja}\n"
			+ "\\usepackage{tabularray}\n"
			+ "\\usepackage{cancel}\n"
			+ "\\definecolor{iris}{rgb}{0.39, 0.44, 1}\n"
			+ "\\definecolor{babypink}{rgb}{1, 0.42, 0.52}\n\n"
			// Comandos matemáticos tolerantes a uso dentro de \text{} (mesma proteção do GeradorListaPDF).
			+ "\\let\\PJoverline\\overline\n\\renewcommand{\\overline}[1]{\\ensuremath{\\PJoverline{#1}}}\n"
			+ "\\let\\PJvec\\vec\n\\renewcommand{\\vec}[1]{\\ensuremath{\\PJvec{#1}}}\n"
			+ "\\let\\PJoverrightarrow\\overrightarrow\n\\renewcommand{\\overrightarrow}[1]{\\ensuremath{\\PJoverrightarrow{#1}}}\n"
			+ "\\let\\PJdfrac\\dfrac\n\\renewcommand{\\dfrac}[2]{\\ensuremath{\\PJdfrac{#1}{#2}}}\n"
			+ "\\let\\PJtfrac\\tfrac\n\\renewcommand{\\tfrac}[2]{\\ensuremath{\\PJtfrac{#1}{#2}}}\n"
			+ "\\let\\PJfrac\\frac\n\\renewcommand{\\frac}[2]{\\ensuremath{\\PJfrac{#1}{#2}}}\n\n"
			+ macros()
			+ "\\setsubject{" + esc(d.nome) + "}\n\n";
	}

	private static String macros()
	{
		return
			"\\newcommand{\\numex}[1]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1}\\end{tcolorbox}\\hspace{4pt}}\n"

			+ "\\newcommand{\\altline}[2]{%\n"
			+ "  \\def\\altph{---}\\def\\altarg{#2}%\n"
			+ "  \\ifx\\altarg\\altph\\else\n"
			+ "    \\noindent{\\bfseries\\color{darkblue}\\small(#1)}~{\\small#2}\\par\n"
			+ "  \\fi}\n"

			+ "\\newcommand{\\alts}[5]{%\n"
			+ "  \\altline{A}{#1}\\altline{B}{#2}\\altline{C}{#3}\\altline{D}{#4}\\altline{E}{#5}\n"
			+ "  \\vspace{0.2cm}}\n"

			// exercício com alternativas (altura natural — o longtblr fatia as páginas)
			+ "\\newcommand{\\exq}[7]{%\n"
			+ "  \\noindent\\numex{#1}{\\small\\color{bodytext}#2}\\par\\vspace{4pt}\n"
			+ "  \\alts{#3}{#4}{#5}{#6}{#7}}\n"

			// exercício com imagem no topo
			+ "\\newcommand{\\exqImg}[8]{%\n"
			+ "  \\noindent\\numex{#1}{\\small\\color{bodytext}#2}\\par\n"
			+ "  {\\centering #3\\par}\\vspace{4pt}\n"
			+ "  \\alts{#4}{#5}{#6}{#7}{#8}}\n"

			// exercício discursivo (sem alternativas)
			+ "\\newcommand{\\exqd}[2]{\\noindent\\numex{#1}{\\small\\color{bodytext}#2}}\n"

			+ "\\newcommand{\\resheader}[2]{%\n"
			+ "  \\begin{tcolorbox}[enhanced,arc=3pt,boxrule=0pt,colback=rulebg,colframe=rulebg,"
			+ "left=4pt,right=4pt,top=1pt,bottom=1pt,nobeforeafter,on line]"
			+ "{\\bfseries\\small\\color{darkblue}#1"
			+ "\\hspace{5pt}{\\color{mutedtext}\\tiny\\textbullet}\\hspace{5pt}"
			+ "\\color{vegreen}#2}\\end{tcolorbox}\\hspace{4pt}}\n"

			+ "\\newcommand{\\res}[3]{%\n"
			+ "  \\noindent\\resheader{#1}{#2}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#3\\par}"
			+ "\\vspace{0.4cm}}\n"

			+ "\\newcommand{\\resd}[2]{%\n"
			+ "  \\noindent\\numex{#1}\\hspace{0.3cm}"
			+ "{\\fontsize{10}{12}\\selectfont\\setlength{\\lineskip}{6pt}\\setlength{\\lineskiplimit}{2pt}\\setlength{\\jot}{8pt}\\color{bodytext}#2\\par}"
			+ "\\vspace{0.4cm}}\n\n";
	}

	private static String abrirGrade()
	{
		return
			"\\begin{longtblr}[label=none]{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"
			+ "  column{2} = {rightsep=0pt},\n"
			+ "  rows     = {valign=t, rowsep=12pt},\n"
			+ "}\n";
	}

	private static String fecharGrade()
	{
		return "\\end{longtblr}\n";
	}

	private static String abrirGradeGabarito()
	{
		return
			"\\begin{longtblr}[label=none]{\n"
			+ "  width    = \\textwidth,\n"
			+ "  colspec  = {X|[1.5pt,sepcolor]X},\n"
			+ "  columns  = {colsep=0pt, leftsep=7pt, rightsep=7pt},\n"
			+ "  column{1} = {leftsep=0pt},\n"
			+ "  column{2} = {rightsep=0pt},\n"
			+ "  rows     = {valign=t, rowsep=0pt},\n"
			+ "}\n";
	}

	private static String fecharGradeGabarito()
	{
		return "\\end{longtblr}\n";
	}

	// ── Exercício → macro LaTeX ──────────────────────────────────────────

	private static String macroExercicio(int num, Exercicio e, Path workDir)
	{
		String numStr = String.format("%02d", num);
		List<AlternativaExercicio> alts = e.getAlternativas();

		if(alts == null || alts.isEmpty())
			return "\\exqd{" + numStr + "}{" + enunciado(num, e, workDir) + "}";

		String[] textos = alternativasTexto(alts);
		String imagens = imagensCmd(num, e, workDir);

		if(!imagens.isEmpty())
			return "\\exqImg{" + numStr + "}{" + enunciadoSemImagem(e) + "}{" + imagens + "}"
				+ "{" + textos[0] + "}{" + textos[1] + "}{" + textos[2] + "}{" + textos[3] + "}{" + textos[4] + "}";

		return "\\exq{" + numStr + "}{" + enunciado(num, e, workDir) + "}"
			+ "{" + textos[0] + "}{" + textos[1] + "}{" + textos[2] + "}{" + textos[3] + "}{" + textos[4] + "}";
	}

	private static String macroGabarito(int num, Exercicio e)
	{
		String numStr = String.format("%02d", num);
		String resolucao = e.getResolucaoLatex() != null ? esc(e.getResolucaoLatex()) : "";
		AlternativaExercicio correta = e.correta();
		if(correta == null)
			return "\\resd{" + numStr + "}{" + resolucao + "}";
		return "\\res{" + numStr + "}{" + correta.getLetra() + "}{" + resolucao + "}";
	}

	// ── Helpers de enunciado/imagem/escape (mesma lógica do GeradorListaPDF) ──

	private static String enunciado(int num, Exercicio e, Path workDir)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for(ParagrafoExercicio p : e.getParagrafos())
		{
			String parte;
			if(p.isTipoImagem())
				parte = "{\\centering\\includegraphics[width=0.6\\linewidth]{" + gravarImagem(num, p, workDir) + "}\\par}";
			else if(p.getTexto() != null && !p.getTexto().isBlank())
				parte = esc(p.getTexto());
			else
				continue;
			if(!primeiro) sb.append("\\par ");
			sb.append(parte);
			primeiro = false;
		}
		return sb.toString();
	}

	private static String enunciadoSemImagem(Exercicio e)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for(ParagrafoExercicio p : e.getParagrafos())
		{
			if(p.isTipoImagem()) continue;
			if(p.getTexto() == null || p.getTexto().isBlank()) continue;
			if(!primeiro) sb.append("\\par ");
			sb.append(esc(p.getTexto()));
			primeiro = false;
		}
		return sb.toString();
	}

	private static String imagensCmd(int num, Exercicio e, Path workDir)
	{
		StringBuilder sb = new StringBuilder();
		boolean primeira = true;
		for(ParagrafoExercicio p : e.getParagrafos())
		{
			if(!p.isTipoImagem()) continue;
			if(!primeira) sb.append("\\par\\vspace{4pt}");
			sb.append("\\includegraphics[width=0.8\\linewidth]{").append(gravarImagem(num, p, workDir)).append("}");
			primeira = false;
		}
		return sb.toString();
	}

	private static String gravarImagem(int num, ParagrafoExercicio p, Path workDir)
	{
		String nome = String.format("img_%02d_%d.png", num, p.getOrdem());
		try
		{
			ImagemFile imf = p.getImagemFile();
			Blob blob = imf != null ? imf.getFile() : null;
			if(blob != null)
			{
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				Files.write(workDir.resolve(nome), bytes);
			}
		}
		catch(IOException | SQLException ex)
		{
			ex.printStackTrace();
		}
		return nome;
	}

	private static String[] alternativasTexto(List<AlternativaExercicio> alts)
	{
		String[] textos = {"---", "---", "---", "---", "---"};
		for(int i = 0; i < Math.min(alts.size(), 5); i++)
			textos[i] = alts.get(i).getTexto() != null ? esc(alts.get(i).getTexto()) : "---";
		return textos;
	}

	private static String esc(String s)
	{
		if(s == null) return "";
		return s.replaceAll("(?<!\\\\)\\\\(?=\\d)", "/")
		        .replaceAll("(?<!\\\\)%", "\\\\%")
		        .replaceAll("(?<!\\\\)\\$", "\\\\\\$");
	}

	// ── Compilação ────────────────────────────────────────────────────────

	private static void compilar(Path texFile) throws IOException, InterruptedException
	{
		for(int passagem = 1; passagem <= 2; passagem++)
		{
			ProcessBuilder pb = new ProcessBuilder(
				XELATEX, "-interaction=nonstopmode", texFile.getFileName().toString())
				.directory(texFile.getParent().toFile())
				.redirectOutput(ProcessBuilder.Redirect.DISCARD)
				.redirectError(ProcessBuilder.Redirect.DISCARD);
			pb.environment().put("TEXINPUTS", texFile.getParent().toAbsolutePath() + ";");
			int exit = pb.start().waitFor();
			if(exit != 0 && passagem == 2)
				throw new IOException("XeLaTeX falhou (código " + exit + ") em " + texFile
					+ " — ver " + texFile.resolveSibling(nomeSemExt(texFile) + ".log"));
		}
	}

	/** Une dois PDFs (teoria + exercícios) num único arquivo via miktex-pdfunite. */
	private static void unir(Path teoria, Path exercicios, Path saida) throws IOException, InterruptedException
	{
		ProcessBuilder pb = new ProcessBuilder(
			PDFUNITE, teoria.toAbsolutePath().toString(),
			exercicios.toAbsolutePath().toString(), saida.toAbsolutePath().toString())
			.redirectOutput(ProcessBuilder.Redirect.DISCARD)
			.redirectError(ProcessBuilder.Redirect.DISCARD);
		int exit = pb.start().waitFor();
		if(exit != 0 || !Files.exists(saida))
			throw new IOException("pdfunite falhou (código " + exit + ") ao unir " + teoria + " + " + exercicios);
	}

	private static String nomeSemExt(Path p)
	{
		String n = p.getFileName().toString();
		int dot = n.lastIndexOf('.');
		return dot > 0 ? n.substring(0, dot) : n;
	}

	// ── Estruturas auxiliares ─────────────────────────────────────────────

	private static class AssuntoData
	{
		String nome;
		String chave;
		Modulo modulo;
		final Map<Nivel, String> geradorPorNivel = new TreeMap<>();
	}

	private static class NivelExercicios
	{
		final Nivel nivel;
		final List<Exercicio> exercicios;
		NivelExercicios(Nivel nivel, List<Exercicio> exercicios)
		{
			this.nivel = nivel;
			this.exercicios = exercicios;
		}
	}
}
