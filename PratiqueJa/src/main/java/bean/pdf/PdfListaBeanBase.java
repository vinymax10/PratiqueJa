package bean.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.util.Mensagem;
import dao.pdf.PdfDAO;
import filtro.pdf.FiltroPdf;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.pdf.Pdf;
import modelo.pdf.TipoPdf;
import modelo.seguranca.PermissaoPadrao;
import service.pdf.ListaPdfException;

/**
 * Base comum das telas de PDF de lista (Questões e Exercícios), que só diferem no
 * {@link TipoPdf}, no serviço de geração em lote e no motivo de "ignorado". Toda a
 * lógica de download, CRUD de arquivo, remoção em massa e geração em lote mora aqui;
 * as subclasses só fornecem os três hooks.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class PdfListaBeanBase extends PaiBean<Pdf, PdfDAO, PermissaoPadrao<Pdf>>
{
	private static final Logger LOG = LoggerFactory.getLogger(PdfListaBeanBase.class);

	@Inject
	private FiltroPdf filtro;

	protected PdfListaBeanBase(String nome, String urlBase)
	{
		super(Pdf.class, nome);
		urlCadastro = urlBase + "form.xhtml";
		urlLista    = urlBase + "list.xhtml";
	}

	// ── Hooks das subclasses ──────────────────────────────────────────

	/** Tipo de PDF gerenciado por esta tela (define o filtro e o tipo ao salvar). */
	protected abstract TipoPdf getTipoPdf();

	/** Dispara a geração em lote do serviço específico e devolve o resumo. */
	protected abstract ResumoLote gerarLote();

	/** Texto do motivo pelo qual um item foi ignorado (ex.: "sem questões"). */
	protected abstract String motivoIgnorado();

	/** Resumo do lote gerado, independente do serviço de origem. */
	public record ResumoLote(int gerados, int ignorados, int erros) {}

	// ── Ações ─────────────────────────────────────────────────────────

	public StreamedContent download(Pdf pdf)
	{
		if (pdf == null || pdf.getCaminho() == null || pdf.getCaminho().isBlank())
			return new DefaultStreamedContent();

		Path path = Path.of(pdf.getCaminho());
		if (!Files.exists(path))
			return new DefaultStreamedContent();

		String nomeArquivo = path.getFileName().toString();
		return DefaultStreamedContent.builder()
			.name(nomeArquivo)
			.contentType("application/pdf")
			.stream(() ->
			{
				try { return Files.newInputStream(path); }
				catch (IOException e) { LOG.error("Falha ao abrir PDF {}", path, e); return InputStream.nullInputStream(); }
			})
			.build();
	}

	@Override
	public void personalizarAdicionar()
	{
		entidade.setTipo(getTipoPdf());
	}

	@Override
	public void personalizarSalvar()
	{
		entidade.setTipo(getTipoPdf());
	}

	@Override
	public void personalizarRemover()
	{
		apagarArquivo(entidade.getCaminho());
	}

	public void removerTodos()
	{
		if (lista == null || lista.isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Nenhum PDF listado para remover.");
			return;
		}

		int total = lista.size();
		for (Pdf pdf : new ArrayList<>(lista))
		{
			apagarArquivo(pdf.getCaminho());
			entidadeDAO.remover(pdf);
		}
		lista.clear();

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, total + " PDF(s) removido(s) com sucesso.");
	}

	public void gerarTodos()
	{
		try
		{
			ResumoLote resultado = gerarLote();

			String msg = resultado.gerados() + " PDF(s) gerado(s)";
			if (resultado.ignorados() > 0)
				msg += ", " + resultado.ignorados() + " ignorado(s) (" + motivoIgnorado() + ")";
			if (resultado.erros() > 0)
				msg += ", " + resultado.erros() + " erro(s)";
			msg += ".";

			FacesMessage.Severity severity = resultado.erros() > 0 ? FacesMessage.SEVERITY_WARN : FacesMessage.SEVERITY_INFO;
			Mensagem.send("growl", severity, msg);
			filtrar();
		}
		catch (ListaPdfException e)
		{
			Mensagem.send("growl", e.isErro() ? FacesMessage.SEVERITY_ERROR : FacesMessage.SEVERITY_WARN, e.getMessage());
		}
		catch (Exception e)
		{
			LOG.error("Erro ao gerar PDFs em lote", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Erro ao gerar PDFs: " + e.getMessage());
		}
	}

	public void filtrar()
	{
		filtro.setTipo(getTipoPdf());
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if (tabState.hasState(FiltroPdf.class))
			filtro = tabState.getState(FiltroPdf.class);
	}

	/** Apaga o arquivo físico do PDF, se existir. */
	private void apagarArquivo(String caminho)
	{
		if (caminho == null || caminho.isBlank())
			return;
		try { Files.deleteIfExists(Path.of(caminho)); }
		catch (IOException e) { LOG.error("Falha ao remover arquivo {}", caminho, e); }
	}
}
