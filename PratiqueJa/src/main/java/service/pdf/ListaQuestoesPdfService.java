package service.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bean.download.Diretorio;
import dao.academico.AssuntoDAO;
import dao.configuracao.ConfigDAO;
import dao.pdf.ConfigPdfQuestaoDAO;
import dao.pdf.PdfDAO;
import dao.questao.QuestaoDAO;
import filtro.questao.FiltroQuestao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.configuracao.Config;
import modelo.pdf.ConfigPdfQuestao;
import modelo.pdf.Pdf;
import modelo.pdf.TipoPdf;
import modelo.pdf.Visibilidade;
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
	@Inject
	private ConfigPdfQuestaoDAO configPdfQuestaoDAO;

	/**
	 * Gera o PDF da lista de questões para um único assunto.
	 *
	 * @param dificuldade opcional — se informada, restringe às questões dessa
	 *        dificuldade. Quando {@code null}, considera todas as dificuldades.
	 * @throws ListaQuestoesPdfException quando não há questões suficientes ou
	 *         a Config não está configurada.
	 */
	public Pdf gerar(Assunto assunto, Dificuldade dificuldade, Visibilidade visibilidade, boolean comAlternativas, TipoGabarito tipoGabarito)
	throws IOException, InterruptedException
	{
		Config config = configValido();

		LayoutLista layout = LayoutLista.PADRAO;
		List<Questao> questoes = buscarQuestoes(assunto, dificuldade, visibilidade, layout.total());
		if(questoes.size() < layout.total())
			throw new ListaQuestoesPdfException(
				"Não há questões suficientes para este assunto"
				+ (dificuldade != null ? " na dificuldade " + dificuldade.getNome() : "")
				+ ". Necessárias " + layout.total() + ", encontradas " + questoes.size() + ".");

		Path outputPath = resolverOutputPath(config, assunto, dificuldade, comAlternativas, visibilidade);
		Files.createDirectories(outputPath.getParent());

		boolean premium = visibilidade == Visibilidade.Premium;
		byte[] bytes = gerarBytes(questoes, assunto, config, instrucao(comAlternativas), premium, comAlternativas, dificuldade, layout, tipoGabarito);
		Files.write(outputPath, bytes);

		return salvarEntidade(assunto, dificuldade, visibilidade, comAlternativas, outputPath);
	}

	/**
	 * Gera as listas de questões em lotes para cada combinação (assunto × {@link ConfigPdfQuestao}).
	 * Para cada assunto/dificuldade, <b>todas</b> as questões revisadas entram nos PDFs (sem sorteio):
	 * são particionadas em lotes de no máximo {@code quantidade} questões (ou um único lote, quando
	 * {@code todos}). Ex.: 50 questões com quantidade 20 → 3 PDFs (20, 20 e 10).
	 *
	 * <p>Visibilidade: cada questão entra no lote da sua própria {@link Questao#getVisibilidade()}
	 * (gerenciada em Administração › Questões), formando lotes {@link Visibilidade#Basico} e
	 * {@link Visibilidade#Premium} separados por assunto/dificuldade. O gabarito traz resolução
	 * conforme {@code comResolucao}.
	 */
	public ResultadoLote gerarTodos()
	{
		List<Assunto> assuntos = assuntoDAO.todos();
		if(assuntos.isEmpty())
			throw new ListaQuestoesPdfException("Nenhum assunto habilitado.");

		List<ConfigPdfQuestao> configs = configPdfQuestaoDAO.todos();
		if(configs.isEmpty())
			throw new ListaQuestoesPdfException(
				"Nenhuma configuração cadastrada. Cadastre em Config PDF Questão.", true);

		Config config = configValido();

		boolean comAlternativas = true;

		int gerados = 0;
		int ignorados = 0;
		int erros = 0;

		for(Assunto assuntoAtual : assuntos)
		{
			for(ConfigPdfQuestao cfg : configs)
			{
				Dificuldade dificuldade   = cfg.getDificuldade();
				TipoGabarito tipoGabarito = cfg.isComResolucao()
					? TipoGabarito.COMPLETO : TipoGabarito.SOMENTE_ALTERNATIVAS;

				// Todas as questões do assunto/dificuldade, em ordem fixa (sem sorteio).
				List<Questao> todas = buscarTodasQuestoes(assuntoAtual, dificuldade);
				if(todas.isEmpty())
				{
					ignorados++;
					continue;
				}

				// Cada questão vai para o lote da sua própria visibilidade.
				List<Questao> basicas = new ArrayList<>();
				List<Questao> premium = new ArrayList<>();
				for(Questao q : todas)
					(q.getVisibilidade() == Visibilidade.Basico ? basicas : premium).add(q);

				int[] resBasico  = gerarLotesVisibilidade(assuntoAtual, cfg, dificuldade, tipoGabarito,
					comAlternativas, config, basicas, Visibilidade.Basico);
				int[] resPremium = gerarLotesVisibilidade(assuntoAtual, cfg, dificuldade, tipoGabarito,
					comAlternativas, config, premium, Visibilidade.Premium);

				gerados += resBasico[0] + resPremium[0];
				erros    += resBasico[1] + resPremium[1];
			}
		}

		return new ResultadoLote(gerados, ignorados, erros);
	}

	/** Particiona {@code questoes} em lotes de {@code cfg.getQuantidade()} e gera um PDF por lote. */
	private int[] gerarLotesVisibilidade(Assunto assuntoAtual, ConfigPdfQuestao cfg, Dificuldade dificuldade,
	                                      TipoGabarito tipoGabarito, boolean comAlternativas, Config config,
	                                      List<Questao> questoes, Visibilidade visibilidade)
	{
		if(questoes.isEmpty())
			return new int[] { 0, 0 };

		int tamanhoLote = (cfg.isTodos() || cfg.getQuantidade() <= 0)
			? questoes.size() : cfg.getQuantidade();
		int totalLotes = (questoes.size() + tamanhoLote - 1) / tamanhoLote;

		int gerados = 0;
		int erros = 0;
		for(int lote = 0; lote < totalLotes; lote++)
		{
			int inicio = lote * tamanhoLote;
			int fim    = Math.min(inicio + tamanhoLote, questoes.size());
			List<Questao> questoesLote = questoes.subList(inicio, fim);

			try
			{
				Path outputPath = resolverOutputPath(config, assuntoAtual, cfg, visibilidade, lote);
				Files.createDirectories(outputPath.getParent());

				boolean premium = visibilidade == Visibilidade.Premium;
				byte[] bytes = gerarBytes(questoesLote, assuntoAtual, config, instrucao(comAlternativas),
					premium, comAlternativas, dificuldade, LayoutLista.PADRAO, tipoGabarito);
				Files.write(outputPath, bytes);

				salvarEntidade(assuntoAtual, cfg, visibilidade, lote, outputPath);
				gerados++;
			}
			catch(Exception | LinkageError e)
			{
				e.printStackTrace();
				erros++;
			}
		}

		return new int[] { gerados, erros };
	}

	private List<Questao> buscarQuestoes(Assunto assunto, Dificuldade dificuldade, Visibilidade visibilidade, int quantidade)
	{
		FiltroQuestao filtro = new FiltroQuestao();
		filtro.setAssunto(assunto);
		filtro.setRevisada(Boolean.TRUE);
		filtro.setResolucaoLatex(Boolean.TRUE);
		filtro.setVisibilidade(visibilidade);
		if(dificuldade != null)
			filtro.setDificuldade(dificuldade);

		List<Questao> todas = questaoDAO.filtrar(filtro);
		if(todas.size() <= quantidade)
			return todas;

		// Embaralha para variar a seleção entre execuções, depois recorta.
		Collections.shuffle(todas);
		return todas.subList(0, quantidade);
	}

	/** Todas as questões revisadas (com resolução em LaTeX) do assunto/dificuldade, sem sorteio nem corte. */
	private List<Questao> buscarTodasQuestoes(Assunto assunto, Dificuldade dificuldade)
	{
		FiltroQuestao filtro = new FiltroQuestao();
		filtro.setAssunto(assunto);
		filtro.setRevisada(Boolean.TRUE);
		filtro.setResolucaoLatex(Boolean.TRUE);
		if(dificuldade != null)
			filtro.setDificuldade(dificuldade);

		return questaoDAO.filtrar(filtro);
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
		Path workDir = Path.of(diretorio.getEndereco());
		try
		{
			return new GeradorListaQuestoesPDF.Builder()
				.questoes(questoes)
				.titulo(assunto.getNome())
				.subtitulo(subtitulo(dificuldade))
				.categoria("Matemática · " + assunto.getModulo().getNome())
				.instrucao(instrucao)
				.pratiquejaStyDir(Path.of(config.getEnderecoTexNew()))
				.xelatexExe("xelatex")
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

	private Path resolverOutputPath(Config config, Assunto assunto, Dificuldade dificuldade, boolean comAlternativas, Visibilidade visibilidade)
	{
		String assuntoDir = assunto.getChave().toLowerCase();
		String dif = dificuldade != null ? dificuldade.name().toLowerCase() : "todas";
		String tipo = comAlternativas ? "alt" : "disc";
		String vis = visibilidade == Visibilidade.Premium ? "premium" : "basico";
		String filename = assuntoDir + "_" + dif + "_" + tipo + "_" + vis + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(SUBPASTA).resolve(assuntoDir).resolve(filename);
	}

	private Pdf salvarEntidade(Assunto assunto, Dificuldade dificuldade, Visibilidade visibilidade, boolean comAlternativas, Path outputPath)
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

	/** Caminho do PDF de um lote de uma {@link ConfigPdfQuestao} (único por assunto/dificuldade/lote). */
	private Path resolverOutputPath(Config config, Assunto assunto, ConfigPdfQuestao cfg, Visibilidade visibilidade, int lote)
	{
		String assuntoDir = assunto.getChave().toLowerCase();
		String dif = cfg.getDificuldade().name().toLowerCase();
		String vis = visibilidade == Visibilidade.Premium ? "premium" : "basico";
		String res = cfg.isComResolucao() ? "res" : "alt";
		String filename = assuntoDir + "_" + dif + "_" + vis + "_" + res + "_lote" + (lote + 1) + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(SUBPASTA).resolve(assuntoDir).resolve(filename);
	}

	private Pdf salvarEntidade(Assunto assunto, ConfigPdfQuestao cfg, Visibilidade visibilidade, int lote, Path outputPath)
	{
		Pdf pdf = new Pdf();
		pdf.setAssunto(assunto);
		pdf.setTipo(TipoPdf.ListaQuestoes);
		pdf.setCaminho(outputPath.toString());
		pdf.setVisibilidade(visibilidade);
		pdf.setDescricao(descricaoPdf(assunto, cfg, visibilidade, lote));
		pdfDAO.adicionar(pdf);
		return pdf;
	}

	private String descricaoPdf(Assunto assunto, ConfigPdfQuestao cfg, Visibilidade visibilidade, int lote)
	{
		String res = cfg.isComResolucao() ? "Com Resolução" : "Somente Alternativas";
		return assunto.getNome() + " - " + cfg.getDificuldade().getNome() + " - "
			+ visibilidade.getNome() + " - Lote " + (lote + 1) + " - " + res;
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
