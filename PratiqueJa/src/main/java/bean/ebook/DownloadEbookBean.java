package bean.ebook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Data;
import bean.configuracao.EdicaoBean;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.exercicio.Nivel;
import service.ebook.EBookService;

@Data
@Named
@ViewScoped
public class DownloadEbookBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Modulo modulo = Modulo.Basico;
	private int numListas = 1;
	private int numEBook = 1;
	private boolean capa = false;
	private double porcentagem;
	private int numEBookAtual;

	@Inject
	private EdicaoBean edicaoBean;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	@Push(channel = "push")
	private PushContext push;

	@Inject
	private EBookService ebookService;

	public StreamedContent download()
	{
		InputStream inStream = new ByteArrayInputStream(construirEBook().toByteArray());
		String nomeFile = modulo.getNome() + "_" + edicaoBean.getEdicao().edicaoModulo(modulo);
		return DefaultStreamedContent.builder().name(nomeFile + ".pdf")
				.contentType("aplication/pdf").stream(() -> inStream).build();
	}

	public String gerarEBook()
	{
		numEBookAtual = 1;
		try
		{
			for(int i = 0; i < numEBook; i++)
			{
				byte[] bytes = construirEBook().toByteArray();
				String nomeFile = modulo.getNome() + "_" + (edicaoBean.getEdicao().edicaoModulo(modulo) - 1);
				File file = new File(ebookService.getDiretorio().getConfigLatex().getEnderecoEBook() + "/" + nomeFile + ".pdf");
				Files.copy(new ByteArrayInputStream(bytes), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				numEBookAtual++;
			}
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "E-book criado com sucesso.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	private java.io.ByteArrayOutputStream construirEBook()
	{
		edicaoBean.salvar();
		List<Assunto> assuntos = assuntoDAO.buscar(modulo);
		String basePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		int edicao = edicaoBean.getEdicao().edicaoModulo(modulo);
		Consumer<Double> onProgress = p -> { porcentagem = p; push.send("update"); };
		java.io.ByteArrayOutputStream result = ebookService.construirEBook(modulo, edicao, numListas, capa, assuntos, basePath, onProgress);
		edicaoBean.incrementa(modulo);
		return result;
	}

	public String gerarListasExercicios()
	{
		List<Assunto> assuntos = assuntoDAO.buscar(modulo);
		List<Nivel> niveis = Arrays.asList(Nivel.values());
		String basePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");

		int totalCalls = niveis.size() * assuntos.size();
		int[] callsDone = {0};
		porcentagem = 0;

		try
		{
			for(Assunto assunto : assuntos)
			{
				for(Nivel nivel : niveis)
				{
					final int base = callsDone[0]++;
					Consumer<Double> onProgress = p -> {
						porcentagem = (base + p) / totalCalls;
						push.send("update");
					};
					byte[] bytes = ebookService.construirListasExercicios(assunto, nivel, numListas, basePath, onProgress).toByteArray();
					String nomeFile = (assunto.getOrdem() + 1) + "_" + assunto.getNome() + "_" + nivel.getNome();
					File file = new File(ebookService.getDiretorio().getConfigLatex().getEnderecoEBook() + "/" + nomeFile + ".pdf");
					Files.copy(new ByteArrayInputStream(bytes), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Listas de exercícios criada com sucesso.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public int getPorcentagemInteiro()
	{
		return (int)(porcentagem * 100);
	}
}
