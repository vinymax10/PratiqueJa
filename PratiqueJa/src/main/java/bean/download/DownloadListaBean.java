package bean.download;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.nio.file.Path;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.exercicio.ExercicioPadraoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.usuario.PerfilUsuario;
import pdf.exercicio.GeradorListaPDF;
import pdf.exercicio.LayoutLista;
import service.configuracao.DiretorioService;
import web.session.Sessao;

/**
 * Bean JSF para download de lista de exercícios em PDF.
 *
 * Padrão: p:fileDownload com value chamando método diretamente (sem action separado).
 *
 * Uso na view:
 * <pre>{@code
 * <p:commandButton value="Nível I" ajax="false">
 *   <p:fileDownload value="#{downloadListaBean.baixarLista(assuntoBean.entidade, 'Nivel1')}" />
 * </p:commandButton>
 * }</pre>
 */
@Getter
@Named
@ViewScoped
public class DownloadListaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DownloadListaBean.class);

	private static final Path TEX_NEW_DIR =
		Path.of("C:/Users/maximovrm/git/PratiqueJa/PratiqueJa/tex-new");

	private static final String XELATEX_EXE =
		"C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe";

	@Inject private DiretorioService diretorioService;
	@Inject private ControleAcessoBean controleAcessoBean;
	@Inject private ExercicioPadraoDAO exercicioPadraoDAO;

	// ── API da view ───────────────────────────────────────────────

	/**
	 * Chamado pelo value do p:fileDownload.
	 * O nível vem como String do EL: "Nivel1", "Nivel2" ou "Nivel3".
	 */
	public StreamedContent baixarLista(Assunto assunto, String nivelStr)
	{
		Nivel nivel = Nivel.valueOf(nivelStr);

		if (!verificarAcesso(nivel))
			return new DefaultStreamedContent();

		try
		{
			ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assunto, nivel);
			if (padrao == null)
			{
				Mensagem.send("growl", FacesMessage.SEVERITY_WARN,
					"Lista não disponível para este assunto/nível.");
				return new DefaultStreamedContent();
			}

			boolean premium = isPremium();
			byte[] bytes = gerarPdf(padrao, assunto, nivel, premium);
			controleAcessoBean.registrarDownload(premium ? 4 : 2);

			return DefaultStreamedContent.builder()
				.name(nomeArquivo(assunto, nivel, premium) + ".pdf")
				.contentType("application/pdf")
				.stream(() -> new ByteArrayInputStream(bytes))
				.build();
		}
		catch (Exception e)
		{
			LOG.error("Erro ao gerar lista PDF: {}", e.getMessage(), e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Não foi possível gerar o PDF. Tente novamente.");
			return new DefaultStreamedContent();
		}
	}

	// ── Geração ──────────────────────────────────────────────────

	private byte[] gerarPdf(ExercicioPadrao padrao, Assunto assunto, Nivel nivel, boolean premium)
		throws Exception
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		Path workDir = Path.of(diretorio.getConfigLatex().getEndereco())
			.resolve(diretorio.getDiretorio());
		try
		{
			return new GeradorListaPDF.Builder()
				.gerador(padrao.getClasse())
				.titulo(assunto.getNome())
				.subtitulo(nivel.getNome())
				.categoria("Matemática · " + assunto.getModulo().getNome())
				.instrucao(instrucao(nivel))
				.pratiquejaStyDir(TEX_NEW_DIR)
				.xelatexExe(XELATEX_EXE)
				.premium(premium)
				.layout(padrao.getLayoutLista() != null ? padrao.getLayoutLista() : LayoutLista.PADRAO)
				.build()
				.gerarBytes(workDir);
		}
		finally
		{
			diretorioService.freeDiretorio(diretorio);
		}
	}

	// ── Controle de acesso ────────────────────────────────────────

	private boolean isPremium()
	{
		PerfilUsuario perfil = Sessao.getUsuarioLogado().getPerfil();
		return perfil == PerfilUsuario.Prata
			|| perfil == PerfilUsuario.Ouro
			|| perfil == PerfilUsuario.Admin;
	}

	private boolean verificarAcesso(Nivel nivel)
	{
		if (!controleAcessoBean.verificaEstaLogado())
			return false;

		if (nivel != Nivel.Nivel1 && !isPremium())
		{
			controleAcessoBean.showUpgrade(
				"Listas de " + nivel.getNome() + " são exclusivas do plano Premium.");
			return false;
		}

		if (!controleAcessoBean.podeFazerDownload())
		{
			controleAcessoBean.showUpgrade(
				"Limite mensal de páginas baixadas foi excedido. Faça o upgrade de sua conta.");
			return false;
		}

		return true;
	}

	// ── Helpers ───────────────────────────────────────────────────

	private String instrucao(Nivel nivel)
	{
		return nivel == Nivel.Nivel3
			? "Resolva as questões abaixo mostrando o desenvolvimento completo."
			: "Para cada questão, marque a alternativa correta e mostre o desenvolvimento.";
	}

	private String nomeArquivo(Assunto assunto, Nivel nivel, boolean premium)
	{
		String chave = assunto.getChave().toLowerCase().replaceAll("[^a-z0-9]", "_");
		return (premium ? "" : "gratis_") + chave + "_nivel" + nivel.name().replace("Nivel", "");
	}
}
