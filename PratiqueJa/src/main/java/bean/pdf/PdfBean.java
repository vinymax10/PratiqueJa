package bean.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import bean.PaiBean;
import bean.util.Mensagem;
import dao.pdf.PdfDAO;
import filtro.pdf.FiltroPdf;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.pdf.Pdf;
import modelo.seguranca.PermissaoPadrao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PdfBean extends PaiBean<Pdf, PdfDAO, PermissaoPadrao<Pdf>>
{
	@Inject
	private FiltroPdf filtro;

	public PdfBean()
	{
		super(Pdf.class, "PDF");

		urlCadastro = "/administracao/conteudo/pdf/form.xhtml";
		urlLista    = "/administracao/conteudo/pdf/list.xhtml";
	}

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
				catch (IOException e) { e.printStackTrace(); return InputStream.nullInputStream(); }
			})
			.build();
	}

	@Override
	public void personalizarRemover()
	{
		String caminho = entidade.getCaminho();
		if (caminho != null && !caminho.isBlank())
		{
			try
			{
				Files.deleteIfExists(Path.of(caminho));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void removerTodos()
	{
		if (lista == null || lista.isEmpty())
		{
			Mensagem.send("growl", jakarta.faces.application.FacesMessage.SEVERITY_WARN,
				"Nenhum PDF listado para remover.");
			return;
		}

		int total = lista.size();
		for (Pdf pdf : new java.util.ArrayList<>(lista))
		{
			String caminho = pdf.getCaminho();
			if (caminho != null && !caminho.isBlank())
			{
				try { Files.deleteIfExists(Path.of(caminho)); }
				catch (IOException e) { e.printStackTrace(); }
			}
			entidadeDAO.remover(pdf);
		}
		lista.clear();

		Mensagem.send("growl", jakarta.faces.application.FacesMessage.SEVERITY_INFO,
			total + " PDF(s) removido(s) com sucesso.");
	}

	public void filtrar()
	{
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
}
