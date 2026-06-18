package service.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import bean.download.Diretorio;
import dao.academico.AssuntoDAO;
import dao.configuracao.ConfigDAO;
import dao.pdf.PdfDAO;
import dao.questao.QuestaoDAO;
import filtro.questao.FiltroQuestao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.configuracao.Config;
import modelo.pdf.Pdf;
import modelo.pdf.TipoPdf;
import modelo.pdf.VisibilidadePdf;
import modelo.questao.Dificuldade;
import modelo.questao.Questao;
import pdf.questao.GeradorListaQuestoesPDF;
import pdf.questao.LayoutLista;
import pdf.questao.TipoGabarito;
import service.configuracao.DiretorioService;

/**
 * Gera listas de questões em PDF a partir das questões revisadas do banco,
 * grava o arquivo em {@code enderecoPdf/lista_questoes/} e persiste a entidade
 * {@link Pdf} correspondente.
 */
@RequestScoped
public class ListaQuestoesPdfService
{
	private static final Path TEX_NEW_DIR = Path.of("C:/Users/maximovrm/git/PratiqueJa/PratiqueJa/tex-new");

	private static final String XELATEX_EXE = "C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe";

	private static final String SUBPASTA = "lista_questoes";

	@Inject
	private QuestaoDAO questaoDAO;
	@Inject
	private AssuntoDAO assuntoDAO;
	@Inject
	private DiretorioService diretorioService;
	@Inject
	private ConfigDAO configDAO;
	@Inject
	private PdfDAO pdfDAO;

	/**
	 * Gera o PDF da lista de questões para um único assunto.
	 *
	 * @param dificuldade opcional — se informada, restringe às questões dessa
	 *        dificuldade. Quando {@code null}, considera todas as dificuldades.
	 * @throws ListaQuestoesPdfException quando não há questões suficientes ou
	 *         a Config não está configurada.
	 */
	public Pdf gerar(Assunto assunto, Dificuldade dificuldade, VisibilidadePdf visibilidade, boolean comAlternativas, TipoGabarito tipoGabarito)
	throws IOException, InterruptedException
	{
		Config config = configValido();

		LayoutLista layout = LayoutLista.PADRAO;
		List<Questao> questoes = buscarQuestoes(assunto, dificuldade, layout.total());
		if(questoes.size() < layout.total())
			throw new ListaQuestoesPdfException(
				"Não há questões suficientes para este assunto"
				+ (dificuldade != null ? " na dificuldade " + dificuldade.getNome() : "")
				+ ". Necessárias " + layout.total() + ", encontradas " + questoes.size() + ".");

		Path outputPath = resolverOutputPath(config, assunto, dificuldade, comAlternativas, visibilidade);
		Files.createDirectories(outputPath.getParent());

		boolean premium = visibilidade == VisibilidadePdf.Premium;
		byte[] bytes = gerarBytes(questoes, assunto, config, instrucao(comAlternativas), premium, comAlternativas, dificuldade, layout, tipoGabarito);
		Files.write(outputPath, bytes);

		return salvarEntidade(assunto, dificuldade, visibilidade, comAlternativas, outputPath);
	}

	/**
	 * Gera as listas para todas as combinações (assunto × dificuldade).
	 * A visibilidade é derivada da dificuldade:
	 * {@link Dificuldade#Facil} → {@link VisibilidadePdf#Basico},
	 * demais → {@link VisibilidadePdf#Premium}.
	 * Combinações sem questões suficientes são ignoradas.
	 */
	public ResultadoLote gerarTodos(boolean comAlternativas, TipoGabarito tipoGabarito)
	{
		List<Assunto> assuntos = assuntoDAO.todos();
		if(assuntos.isEmpty())
			throw new ListaQuestoesPdfException("Nenhum assunto habilitado.");

		Config config = configValido();

		int gerados = 0;
		int ignorados = 0;
		int erros = 0;

		LayoutLista layout = LayoutLista.PADRAO;

		for(Assunto assuntoAtual : assuntos)
		{
			for(Dificuldade dificuldade : Dificuldade.values())
			{
				VisibilidadePdf visibilidade = visibilidadeDe(dificuldade);
				try
				{
					List<Questao> questoes = buscarQuestoes(assuntoAtual, dificuldade, layout.total());
					if(questoes.size() < layout.total())
					{
						ignorados++;
						continue;
					}

					Path outputPath = resolverOutputPath(config, assuntoAtual, dificuldade, comAlternativas, visibilidade);
					Files.createDirectories(outputPath.getParent());

					boolean premium = visibilidade == VisibilidadePdf.Premium;
					byte[] bytes = gerarBytes(questoes, assuntoAtual, config, instrucao(comAlternativas), premium, comAlternativas, dificuldade, layout, tipoGabarito);
					Files.write(outputPath, bytes);

					salvarEntidade(assuntoAtual, dificuldade, visibilidade, comAlternativas, outputPath);
					gerados++;
				}
				catch(Exception | LinkageError e)
				{
					e.printStackTrace();
					erros++;
				}
			}
		}

		return new ResultadoLote(gerados, ignorados, erros);
	}

	/** Facil → Básico; Médio e Difícil → Premium. */
	private VisibilidadePdf visibilidadeDe(Dificuldade dificuldade)
	{
		return dificuldade == Dificuldade.Facil ? VisibilidadePdf.Basico : VisibilidadePdf.Premium;
	}

	private List<Questao> buscarQuestoes(Assunto assunto, Dificuldade dificuldade, int quantidade)
	{
		FiltroQuestao filtro = new FiltroQuestao();
		filtro.setAssunto(assunto);
		filtro.setRevisada(Boolean.TRUE);
		filtro.setResolucaoLatex(Boolean.TRUE);
		if(dificuldade != null)
			filtro.setDificuldade(dificuldade);

		List<Questao> todas = questaoDAO.filtrar(filtro);
		if(todas.size() <= quantidade)
			return todas;

		// Embaralha para variar a seleção entre execuções, depois recorta.
		Collections.shuffle(todas);
		return todas.subList(0, quantidade);
	}

	private Config configValido()
	{
		Config config = configDAO.buscar();
		if(config == null || config.getEnderecoPdf() == null)
			throw new ListaQuestoesPdfException("Endereço de PDF não configurado. Configure em Config.", true);
		return config;
	}

	private byte[] gerarBytes(List<Questao> questoes, Assunto assunto, Config config, String instrucao,
	                          boolean premium, boolean comAlternativas, Dificuldade dificuldade, LayoutLista layout, TipoGabarito tipoGabarito)
	throws IOException, InterruptedException
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		Path workDir = Path.of(config.getEndereco()).resolve(diretorio.getDiretorio());
		try
		{
			return new GeradorListaQuestoesPDF.Builder()
				.questoes(questoes)
				.titulo(assunto.getNome())
				.subtitulo(subtitulo(dificuldade))
				.categoria("Matemática · " + assunto.getModulo().getNome())
				.instrucao(instrucao)
				.pratiquejaStyDir(TEX_NEW_DIR)
				.xelatexExe(XELATEX_EXE)
				.premium(premium)
				.layout(layout)
				.comAlternativas(comAlternativas)
				.tipoGabarito(tipoGabarito)
				.build()
				.gerarBytes(workDir);
		}
		finally
		{
			diretorioService.freeDiretorio(diretorio);
		}
	}

	private Path resolverOutputPath(Config config, Assunto assunto, Dificuldade dificuldade, boolean comAlternativas, VisibilidadePdf visibilidade)
	{
		String assuntoDir = assunto.getChave().toLowerCase();
		String dif = dificuldade != null ? dificuldade.name().toLowerCase() : "todas";
		String tipo = comAlternativas ? "alt" : "disc";
		String vis = visibilidade == VisibilidadePdf.Premium ? "premium" : "basico";
		String filename = assuntoDir + "_" + dif + "_" + tipo + "_" + vis + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(SUBPASTA).resolve(assuntoDir).resolve(filename);
	}

	private Pdf salvarEntidade(Assunto assunto, Dificuldade dificuldade, VisibilidadePdf visibilidade, boolean comAlternativas, Path outputPath)
	{
		Pdf pdf = new Pdf();
		pdf.setAssunto(assunto);
		pdf.setTipo(TipoPdf.ListaQuestoes);
		pdf.setCaminho(outputPath.toString());
		pdf.setVisibilidade(visibilidade);
		pdf.setDescricao(descricaoPdf(assunto, dificuldade, comAlternativas));
		pdfDAO.adicionar(pdf);
		return pdf;
	}

	private String descricaoPdf(Assunto assunto, Dificuldade dificuldade, boolean comAlternativas)
	{
		String dif = dificuldade != null ? dificuldade.getNome() : "Todas dificuldades";
		return assunto.getNome() + " - " + dif + " - " + (comAlternativas ? "Com Alternativas" : "Discursivo");
	}

	private String subtitulo(Dificuldade dificuldade)
	{
		return dificuldade != null ? "Dificuldade " + dificuldade.getNome() : "Lista de Questões";
	}

	private String instrucao(boolean comAlternativas)
	{
		return comAlternativas
			? "Para cada questão, marque a alternativa correta e mostre o desenvolvimento."
			: "Resolva as questões abaixo mostrando o desenvolvimento completo.";
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
