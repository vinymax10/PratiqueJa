package service.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import bean.download.Diretorio;
import dao.academico.AssuntoDAO;
import dao.configuracao.ConfigDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.pdf.ConfigPdfExercicioDAO;
import dao.pdf.PdfDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.configuracao.Config;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.pdf.ConfigPdfExercicio;
import modelo.pdf.Pdf;
import modelo.pdf.TipoPdf;
import modelo.pdf.Visibilidade;
import pdf.exercicio.GeradorListaPDF;
import pdf.exercicio.LayoutLista;
import service.configuracao.DiretorioService;

/**
 * Gera listas de exercícios em PDF a partir dos exercícios padrão, grava o
 * arquivo em {@code enderecoPdf/lista_exercicios/} e persiste a entidade
 * {@link Pdf} correspondente.
 */
@RequestScoped
public class ListaExerciciosPdfService
{
	private static final String SUBPASTA = "lista_exercicios";

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;
	@Inject
	private ConfigPdfExercicioDAO configPdfExercicioDAO;
	@Inject
	private AssuntoDAO assuntoDAO;
	@Inject
	private DiretorioService diretorioService;
	@Inject
	private ConfigDAO configDAO;
	@Inject
	private PdfDAO pdfDAO;

	/**
	 * Gera o PDF da lista de exercícios para um único assunto/nível.
	 *
	 * @throws ListaExerciciosPdfException se não houver exercício padrão ou
	 *         Config válida.
	 */
	public Pdf gerar(Assunto assunto, Nivel nivel, Visibilidade visibilidade, boolean comAlternativas)
	throws IOException, InterruptedException
	{
		ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assunto, nivel);
		if(padrao == null)
			throw new ListaExerciciosPdfException("Não existe exercício padrão cadastrado para este assunto e nível.");

		Config config = configValido();

		Path outputPath = resolverOutputPath(config, assunto, nivel, comAlternativas, visibilidade);
		Files.createDirectories(outputPath.getParent());

		boolean premium = visibilidade == Visibilidade.Premium;
		byte[] bytes = gerarBytes(padrao, assunto, config, instrucao(nivel, comAlternativas), premium, comAlternativas);
		Files.write(outputPath, bytes);

		return salvarEntidade(padrao, assunto, nivel, visibilidade, comAlternativas, outputPath);
	}

	/**
	 * Gera os PDFs de todos os assuntos para cada Config Exercício cadastrada.
	 *
	 * @throws ListaExerciciosPdfException se faltarem configs, assuntos ou
	 *         Config válida.
	 */
	public ResultadoLote gerarTodos()
	{
		List<ConfigPdfExercicio> configs = configPdfExercicioDAO.listarTudo();
		if(configs.isEmpty())
			throw new ListaExerciciosPdfException("Nenhuma Config Exercício cadastrada.");

		List<Assunto> assuntos = assuntoDAO.todos();
		if(assuntos.isEmpty())
			throw new ListaExerciciosPdfException("Nenhum assunto habilitado.");

		Config config = configValido();

		int gerados = 0;
		int ignorados = 0;
		int erros = 0;

		for(ConfigPdfExercicio cfg : configs)
		{
			for(Assunto assuntoAtual : assuntos)
			{
				ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assuntoAtual, cfg.getNivel());
				if(padrao == null)
				{
					ignorados++;
					continue;
				}

				try
				{
					Path outputPath = resolverOutputPath(config, assuntoAtual, cfg.getNivel(), cfg.isComAlternativas(), cfg.getVisibilidade());
					Files.createDirectories(outputPath.getParent());

					byte[] bytes = gerarBytes(padrao, assuntoAtual, config, instrucao(cfg.getNivel(), cfg.isComAlternativas()), cfg.isComResolucao(), cfg.isComAlternativas());
					Files.write(outputPath, bytes);

					salvarEntidade(padrao, assuntoAtual, cfg.getNivel(), cfg.getVisibilidade(), cfg.isComAlternativas(), outputPath);
					gerados++;
				}
				catch(Exception | LinkageError e)
				{
					// LinkageError (ex.: NoClassDefFoundError de um gerador ausente no deploy) é Error,
					// não Exception: sem isto, um único gerador quebrado abortaria o lote inteiro.
					e.printStackTrace();
					erros++;
				}
			}
		}

		return new ResultadoLote(gerados, ignorados, erros);
	}

	private Config configValido()
	{
		Config config = configDAO.buscar();
		if(config == null || config.getEnderecoPdf() == null)
			throw new ListaExerciciosPdfException("Endereço de PDF não configurado. Configure em Config.", true);
		return config;
	}

	private byte[] gerarBytes(ExercicioPadrao padrao, Assunto assunto, Config config, String instrucao, boolean premium, boolean comAlternativas)
	throws IOException, InterruptedException
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		Path workDir = Path.of(diretorio.getEndereco());
		try
		{
			return new GeradorListaPDF.Builder().gerador(padrao.getClasse()).titulo(assunto.getNome()).subtitulo("Nível " + padrao.getNivelRomano())
			.categoria("Matemática · " + assunto.getModulo().getNome()).instrucao(instrucao).pratiquejaStyDir(Path.of(config.getEnderecoTexNew())).xelatexExe("xelatex")
			.premium(premium).layout(padrao.getLayoutLista() != null ? padrao.getLayoutLista() : LayoutLista.PADRAO)
			.comAlternativas(comAlternativas).build().gerarBytes(workDir);
		}
		finally
		{
			diretorioService.freeDiretorio(diretorio);
		}
	}

	private Path resolverOutputPath(Config config, Assunto assunto, Nivel nivel, boolean comAlternativas, Visibilidade visibilidade)
	{
		String assuntoDir = assunto.getChave().toLowerCase();
		String n = nivel.name().replace("Nivel", "");
		String tipo = comAlternativas ? "alt" : "disc";
		String vis = visibilidade == Visibilidade.Premium ? "premium" : "basico";
		String filename = assuntoDir + "_nivel" + n + "_" + tipo + "_" + vis + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(SUBPASTA).resolve(assuntoDir).resolve(filename);
	}

	private Pdf salvarEntidade(ExercicioPadrao padrao, Assunto assunto, Nivel nivel, Visibilidade visibilidade, boolean comAlternativas, Path outputPath)
	{
		Pdf pdf = new Pdf();
		pdf.setAssunto(assunto);
		pdf.setTipo(TipoPdf.ListaExercicios);
		pdf.setNivel(nivel);
		pdf.setCaminho(outputPath.toString());
		pdf.setVisibilidade(visibilidade);
		pdf.setDescricao(descricaoPdf(padrao, assunto, nivel, comAlternativas));
		pdfDAO.adicionar(pdf);
		return pdf;
	}

	private String descricaoPdf(ExercicioPadrao padrao, Assunto assunto, Nivel nivel, boolean comAlternativas)
	{
		String descricao = padrao.getDescricao();
		if(descricao != null && !descricao.isBlank())
			return descricao;

		return assunto.getNome() + " - " + nivel.getNome() + " - " + (comAlternativas ? "Com Alternativas" : "Discursivo");
	}

	private String instrucao(Nivel nivel, boolean comAlternativas)
	{
		if(!comAlternativas)
			return "Resolva as questões abaixo mostrando o desenvolvimento completo.";
		return nivel == Nivel.Nivel3 ? "Resolva as questões abaixo mostrando o desenvolvimento completo."
		: "Para cada questão, marque a alternativa correta e mostre o desenvolvimento.";
	}

	@Getter
	@AllArgsConstructor
	public static class ResultadoLote
	{
		private final int gerados;
		private final int ignorados;
		private final int erros;
	}
}
