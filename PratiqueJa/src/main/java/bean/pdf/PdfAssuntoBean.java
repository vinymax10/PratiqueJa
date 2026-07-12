package bean.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import bean.usuario.ControleAcessoBean;
import dao.pdf.PdfDAO;
import filtro.pdf.FiltroPdf;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import modelo.academico.Assunto;
import modelo.pdf.Pdf;
import modelo.pdf.Visibilidade;

/**
 * Bean da tela pública /matematica/pdf — lista os PDFs de um assunto.
 */
@Named
@ViewScoped
public class PdfAssuntoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PdfDAO pdfDAO;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Getter
	private List<Pdf> pdfs = new ArrayList<>();

	/** PDF Premium indisponível para o usuário atual (não logado ou plano básico). */
	public boolean bloqueado(Pdf pdf)
	{
		return pdf != null
			&& pdf.getVisibilidade() == Visibilidade.Premium
			&& !controleAcessoBean.podeAcessarPremium();
	}

	public void filtrarPorAssunto(Assunto assunto)
	{
		if (assunto == null)
		{
			pdfs = new ArrayList<>();
			return;
		}

		FiltroPdf filtro = new FiltroPdf();
		filtro.setAssunto(assunto);
		pdfs = pdfDAO.buscar(filtro);
		pdfs.sort(Comparator
			.comparingInt((Pdf p) -> p.getTipo() != null ? p.getTipo().getOrdem() : 99)
			.thenComparingInt(Pdf::getOrdem));
	}

	public StreamedContent download(Pdf pdf)
	{
		if (pdf == null || bloqueado(pdf) || pdf.getCaminho() == null || pdf.getCaminho().isBlank())
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
}
