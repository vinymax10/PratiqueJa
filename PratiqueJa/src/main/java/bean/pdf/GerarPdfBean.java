package bean.pdf;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import bean.download.Diretorio;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.configuracao.ConfigLatexDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.pdf.ConfigExercicioDAO;
import dao.pdf.PdfDAO;
import infra.Navegacao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.academico.Assunto;
import modelo.configuracao.ConfigLatex;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.pdf.ConfigExercicio;
import modelo.pdf.Pdf;
import modelo.pdf.VisibilidadePdf;
import pdf.exercicio.GeradorListaPDF;
import pdf.exercicio.LayoutLista;
import service.configuracao.DiretorioService;

@Data
@Named
@ViewScoped
public class GerarPdfBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Path TEX_NEW_DIR =
		Path.of("C:/Users/maximovrm/git/PratiqueJa/PratiqueJa/tex-new");

	private static final String XELATEX_EXE =
		"C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe";

	private Assunto assunto;
	private Nivel nivel;
	private boolean comAlternativas = true;
	private VisibilidadePdf visibilidade;

	@Inject private ExercicioPadraoDAO exercicioPadraoDAO;
	@Inject private ConfigExercicioDAO configExercicioDAO;
	@Inject private AssuntoDAO assuntoDAO;
	@Inject private DiretorioService diretorioService;
	@Inject private ConfigLatexDAO configLatexDAO;
	@Inject private PdfDAO pdfDAO;

	public void gerar()
	{
		if (assunto == null || nivel == null || visibilidade == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Preencha todos os campos obrigatórios.");
			return;
		}

		ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assunto, nivel);
		if (padrao == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
				"Não existe exercício padrão cadastrado para este assunto e nível.");
			return;
		}

		ConfigLatex config = configLatexDAO.buscar();
		if (config == null || config.getEnderecoPdf() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Endereço de PDF não configurado. Configure em ConfigLatex.");
			return;
		}

		try
		{
			Path outputPath = resolverOutputPath(config, padrao);
			Files.createDirectories(outputPath.getParent());

			byte[] bytes = gerarBytes(padrao, config);
			Files.write(outputPath, bytes);

			salvarEntidade(outputPath);

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "PDF gerado com sucesso.");
			Navegacao.redirect("/administracao/conteudo/pdf/list.xhtml");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Erro ao gerar PDF: " + e.getMessage());
		}
	}

	public void gerarTodos()
	{
		List<ConfigExercicio> configs = configExercicioDAO.listarTudo();
		if (configs.isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
				"Nenhuma Config Exercício cadastrada.");
			return;
		}

		List<Assunto> assuntos = assuntoDAO.todos();
		if (assuntos.isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
				"Nenhum assunto habilitado.");
			return;
		}

		ConfigLatex config = configLatexDAO.buscar();
		if (config == null || config.getEnderecoPdf() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Endereço de PDF não configurado. Configure em ConfigLatex.");
			return;
		}

		int gerados = 0;
		int ignorados = 0;
		int erros = 0;

		for (ConfigExercicio cfg : configs)
		{
			for (Assunto assuntoAtual : assuntos)
			{
				ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assuntoAtual, cfg.getNivel());
				if (padrao == null)
				{
					ignorados++;
					continue;
				}

				try
				{
					Path outputPath = resolverOutputPathLote(config, assuntoAtual, cfg);
					Files.createDirectories(outputPath.getParent());

					byte[] bytes = gerarBytesLote(padrao, assuntoAtual, cfg, config);
					Files.write(outputPath, bytes);

					salvarEntidadeLote(assuntoAtual, cfg, outputPath);
					gerados++;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					erros++;
				}
			}
		}

		String msg = gerados + " PDF(s) gerado(s)";
		if (ignorados > 0) msg += ", " + ignorados + " ignorado(s) (sem exercício padrão)";
		if (erros > 0)     msg += ", " + erros + " erro(s)";
		msg += ".";

		FacesMessage.Severity severity = erros > 0
			? FacesMessage.SEVERITY_WARN
			: FacesMessage.SEVERITY_INFO;
		Mensagem.send("growl", severity, msg);
	}

	private byte[] gerarBytesLote(ExercicioPadrao padrao, Assunto assuntoAtual,
		ConfigExercicio cfg, ConfigLatex config) throws IOException, InterruptedException
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		Path workDir = Path.of(config.getEndereco()).resolve(diretorio.getDiretorio());
		try
		{
			return new GeradorListaPDF.Builder()
				.gerador(padrao.getClasse())
				.titulo(assuntoAtual.getNome())
				.subtitulo("Nível " + padrao.getNivelRomano())
				.categoria("Matemática · " + assuntoAtual.getModulo().getNome())
				.instrucao(instrucaoLote(cfg))
				.pratiquejaStyDir(TEX_NEW_DIR)
				.xelatexExe(XELATEX_EXE)
				.premium(cfg.isComResolucao())
				.layout(padrao.getLayoutLista() != null ? padrao.getLayoutLista() : LayoutLista.PADRAO)
				.comAlternativas(cfg.isComAlternativas())
				.build()
				.gerarBytes(workDir);
		}
		finally
		{
			diretorioService.freeDiretorio(diretorio);
		}
	}

	private Path resolverOutputPathLote(ConfigLatex config, Assunto assuntoAtual, ConfigExercicio cfg)
	{
		String assuntoDir = assuntoAtual.getChave().toLowerCase();
		String n          = cfg.getNivel().name().replace("Nivel", "");
		String tipo       = cfg.isComAlternativas() ? "alt" : "disc";
		String vis        = cfg.getVisibilidade() == VisibilidadePdf.Premium ? "premium" : "basico";
		String filename   = assuntoDir + "_nivel" + n + "_" + tipo + "_" + vis + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(assuntoDir).resolve(filename);
	}

	private void salvarEntidadeLote(Assunto assuntoAtual, ConfigExercicio cfg, Path outputPath)
	{
		Pdf pdf = new Pdf();
		pdf.setAssunto(assuntoAtual);
		pdf.setCaminho(outputPath.toString());
		pdf.setVisibilidade(cfg.getVisibilidade());
		pdf.setDescricao(assuntoAtual.getNome() + " - " + cfg.getNivel().getNome()
			+ " - " + (cfg.isComAlternativas() ? "Com Alternativas" : "Discursivo"));
		pdfDAO.adicionar(pdf);
	}

	private String instrucaoLote(ConfigExercicio cfg)
	{
		if (!cfg.isComAlternativas())
			return "Resolva as questões abaixo mostrando o desenvolvimento completo.";
		return cfg.getNivel() == Nivel.Nivel3
			? "Resolva as questões abaixo mostrando o desenvolvimento completo."
			: "Para cada questão, marque a alternativa correta e mostre o desenvolvimento.";
	}

	private byte[] gerarBytes(ExercicioPadrao padrao, ConfigLatex config)
		throws IOException, InterruptedException
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		Path workDir = Path.of(config.getEndereco()).resolve(diretorio.getDiretorio());
		try
		{
			return new GeradorListaPDF.Builder()
				.gerador(padrao.getClasse())
				.titulo(assunto.getNome())
				.subtitulo("Nível " + padrao.getNivelRomano())
				.categoria("Matemática · " + assunto.getModulo().getNome())
				.instrucao(instrucao())
				.pratiquejaStyDir(TEX_NEW_DIR)
				.xelatexExe(XELATEX_EXE)
				.premium(visibilidade == VisibilidadePdf.Premium)
				.layout(padrao.getLayoutLista() != null ? padrao.getLayoutLista() : LayoutLista.PADRAO)
				.comAlternativas(comAlternativas)
				.build()
				.gerarBytes(workDir);
		}
		finally
		{
			diretorioService.freeDiretorio(diretorio);
		}
	}

	private Path resolverOutputPath(ConfigLatex config, ExercicioPadrao padrao)
	{
		String assuntoDir = assunto.getChave().toLowerCase();
		String n          = nivel.name().replace("Nivel", "");
		String tipo       = comAlternativas ? "alt" : "disc";
		String vis        = visibilidade == VisibilidadePdf.Premium ? "premium" : "basico";
		String filename   = assuntoDir + "_nivel" + n + "_" + tipo + "_" + vis + ".pdf";

		return Path.of(config.getEnderecoPdf()).resolve(assuntoDir).resolve(filename);
	}

	private void salvarEntidade(Path outputPath)
	{
		Pdf pdf = new Pdf();
		pdf.setAssunto(assunto);
		pdf.setCaminho(outputPath.toString());
		pdf.setVisibilidade(visibilidade);
		pdf.setDescricao(assunto.getNome() + " - " + nivel.getNome()
			+ " - " + (comAlternativas ? "Com Alternativas" : "Discursivo"));
		pdfDAO.adicionar(pdf);
	}

	private String instrucao()
	{
		if (!comAlternativas)
			return "Resolva as questões abaixo mostrando o desenvolvimento completo.";
		return nivel == Nivel.Nivel3
			? "Resolva as questões abaixo mostrando o desenvolvimento completo."
			: "Para cada questão, marque a alternativa correta e mostre o desenvolvimento.";
	}
}
