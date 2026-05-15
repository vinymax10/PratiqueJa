package bean.ebook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.Data;
import bean.configuracao.EdicaoBean;
import bean.download.Diretorio;
import service.configuracao.DiretorioService;
import bean.exercicio.ConfigDownload;
import bean.util.Mensagem;
import dao.academico.AssuntoCursoDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.questao.QuestaoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.academico.AssuntoCurso;
import modelo.academico.Modulo;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.questao.Questao;
import modelo.email.ConfigSpam;
import pdf.ebook.ExercicioEBook;
import pdf.ebook.FolhaRosto;
import pdf.exercicio.GerarLatexExercicio;
import pdf.ebook.ItemSumario;
import pdf.ebook.QuestaoEBook;
import pdf.ebook.Resumo;
import pdf.ebook.Sumario;

@Data
@Named
@ViewScoped
public class DownloadEbookBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DownloadEbookBean.class.getName());

	private Modulo modulo = Modulo.Basico;

	private int numListas = 1;

	private int numEBook = 1;

	private boolean capa = false;

	@Inject
	private EdicaoBean edicaoBean;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private DiretorioService diretorioService;

	private Diretorio diretorio;

	@Inject
	private AssuntoCursoDAO assuntoCursoDAO;

	@Inject
	@Push(channel = "push")
	private PushContext push;

	private double porcentagem;
	private int contPartes = 0;
	private int totalPartes;
	private int numExercicios;
	private int numQuestoes;
	private int numEBookAtual;
	private int totalPaginas, numPaginas;

	private Document document;
	private PdfContentByte pageContentByte;
	private PdfWriter pdfWriter;

	@Inject
	private QuestaoDAO questaoDAO;

	private List<AssuntoCurso> assuntosCurso;
	private List<ItemSumario> itens;

	private ByteArrayOutputStream finalOutputStream;
	private ByteArrayOutputStream baos;
	private PdfReader readerOriginal, reader;
	private HeaderEBook header;
	private PdfImportedPage page;
	private InputStream inputStream;

	public StreamedContent download()
	{
		finalOutputStream = construirEBook();
		InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());
		String nomeFile = modulo.getNome() + "_" + edicaoBean.getEdicao().edicaoModulo(modulo);

		return DefaultStreamedContent.builder().name(nomeFile + ".pdf").contentType("aplication/pdf").stream(() -> inStream).build();
	}

	private ByteArrayOutputStream construirEBook()
	{
		edicaoBean.salvar();
		assuntosCurso = assuntoCursoDAO.buscar(modulo);
		itens = new ArrayList<>();
		ExercicioPadrao exercicio;
		finalOutputStream = new ByteArrayOutputStream();

		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		iniciarPorcentagem();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			int passoPage = 3;

			if(capa)
				addDocumento("/pdf/ebook/capa" + modulo + ".pdf", true);
			else
				passoPage += 2;

			addFolhaRosto();

			int cont = 1;
			for(AssuntoCurso assuntoCurso : assuntosCurso)
			{
				ItemSumario itemSumario = new ItemSumario(assuntoCurso.getNome(), "" + cont++, "" + (pdfWriter.getPageNumber() + passoPage));
				logger.fine("itemSumario: " + itemSumario);
				itens.add(itemSumario);

				addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf", true);

				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel1);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel2);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assuntoCurso, Nivel.Nivel3);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				addQuestao(assuntoCurso);
			}

			if(capa)
				addDocumento("/pdf/ebook/contraCapa" + modulo + ".pdf", true);

			totalPaginas = pdfWriter.getPageNumber() + 2;
			if(!capa)
				totalPaginas += 4;

			document.close();
			pdfWriter.close();

			readerOriginal = new PdfReader(new ByteArrayInputStream(baos.toByteArray()));

			document = new Document();
			pdfWriter = PdfWriter.getInstance(document, finalOutputStream);
			document.open();

			header = new HeaderEBook();
			pdfWriter.setPageEvent(header);

			pageContentByte = pdfWriter.getDirectContent();

			int posicaoSumario = 2;
			if(capa)
				posicaoSumario += 2;
			else
				header.pagInicial += 2;

			for(int i = 1; i <= posicaoSumario; i++)
			{
				document.newPage();
				page = pdfWriter.getImportedPage(readerOriginal, i);
				pageContentByte.addTemplate(page, 0, 0);
			}

			header.addPage = true;

			addSumario();
			int indexAssunto = 0;

			for(int i = posicaoSumario + 1; i <= readerOriginal.getNumberOfPages(); i++)
			{
				if(itens.size() > (indexAssunto + 1) && itens.get(indexAssunto + 1).pagina.equals("" + (i + passoPage - 1)))
					indexAssunto++;

				AssuntoCurso assuntoCurso = assuntosCurso.get(indexAssunto);
				if(capa && i > (readerOriginal.getNumberOfPages() - 2))
				{
					header.assunto = "";
					header.addPage = false;
				}
				else
					header.assunto = assuntoCurso.getNome();

				document.newPage();
				page = pdfWriter.getImportedPage(readerOriginal, i);
				pageContentByte.addTemplate(page, 0, 0);
			}

			document.close();
			pdfWriter.close();
			readerOriginal.close();

			diretorioService.freeDiretorio(diretorio);
			edicaoBean.incrementa(modulo);

			return finalOutputStream;
		}
		catch(DocumentException | IOException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	public String gerarEBook()
	{
		numEBookAtual = 1;
		try
		{
			for(int i = 0; i < numEBook; i++)
			{
				finalOutputStream = construirEBook();
				InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());

				String nomeFile = modulo.getNome() + "_" + (edicaoBean.getEdicao().edicaoModulo(modulo) - 1);
				File file = new File(diretorio.getConfigLatex().getEnderecoEBook() + "/" + nomeFile + ".pdf");
				Files.copy(inStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

				numEBookAtual++;
			}

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "E-book criado com sucesso.");
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}

		return "";
	}

	private void iniciarPorcentagem()
	{
		int numeroNiveis = 3;
		totalPartes = assuntosCurso.size() + 2 + (assuntosCurso.size() * numeroNiveis * numListas);
		if(capa)
			totalPartes += 2;

		for(AssuntoCurso assuntoCurso : assuntosCurso)
		{
			if(assuntoCurso.isShowQuestao())
				totalPartes++;
		}
		numExercicios = 0;
		numQuestoes = 0;
		numPaginas = 0;
		contPartes = 0;
		porcentagem = 0;
	}

	private void addDocumento(String endereco, boolean addPagePar)
	{
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		File file = new File(externalContext.getRealPath(endereco));
		addDocumento(file, addPagePar);
	}

	private Resumo montarResumo()
	{
		Resumo resumo = new Resumo();
		resumo.setAssuntos(assuntosCurso.size() + "");
		resumo.setPaginas(totalPaginas + "");
		resumo.setExecicios(numExercicios + "");
		resumo.setQuestoes(numQuestoes + "");
		resumo.setListas(assuntosCurso.size() * numListas * 3 + "");

		return resumo;
	}

	private void addDocumento(File file, boolean addPagePar)
	{
		try
		{
			inputStream = new FileInputStream(file);
			reader = new PdfReader(inputStream);
			numPaginas += reader.getNumberOfPages();
			for(int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				page = pdfWriter.getImportedPage(reader, j);
				pageContentByte.addTemplate(page, 0, 0);
			}
			if(addPagePar && reader.getNumberOfPages() % 2 == 1)
			{
				document.newPage();
				document.add(new Paragraph("     "));
				numPaginas++;
			}

			pdfWriter.freeReader(reader);
			reader.close();
			inputStream.close();
		}
		catch(IOException | DocumentException e1)
		{
			e1.printStackTrace();
		}
		contPartes++;
		porcentagem = (double) contPartes / totalPartes;
		push.send("update");
		logger.fine(contPartes + "/" + totalPartes);
	}

	private void addFolhaRosto()
	{
		FolhaRosto gerarLatex = new FolhaRosto(diretorio);
		gerarLatex.gerarPDFExercicio(modulo, edicaoBean.getEdicao().edicaoModulo(modulo));
		gerarLatex.gerar();

		addDocumento(new File(diretorio.getEnderecoPdf()), false);
	}

	private void addSumario()
	{
		Resumo resumo = montarResumo();
		Sumario gerarLatex = new Sumario(diretorio);
		gerarLatex.gerarPDFExercicio(itens, resumo);
		gerarLatex.gerar();

		addDocumento(new File(diretorio.getEnderecoPdf()), true);
	}

	private void addExercicioEBook(ExercicioPadrao exercicio)
	{
		ExercicioEBook gerarLatex = new ExercicioEBook(diretorio);
		gerarLatex.gerarPDFExercicio(exercicio);
		gerarLatex.gerar();
		numExercicios += exercicio.getQuantidade();

		addDocumento(new File(diretorio.getEnderecoPdf()), false);
	}

	private void addQuestao(AssuntoCurso assuntoCurso)
	{
		List<Questao> questoes = questaoDAO.buscaAssuntoCurso(assuntoCurso, null, null);
		if(!questoes.isEmpty())
		{
			QuestaoEBook gerarLatex = new QuestaoEBook(diretorio);
			gerarLatex.gerarPDFQuestoes(questoes);
			gerarLatex.gerar();
			numQuestoes += questoes.size();
			addDocumento(new File(diretorio.getEnderecoPdf()), true);
		}
	}

	public String gerarListasExercicios()
	{
		assuntosCurso = assuntoCursoDAO.buscar(modulo);
		List<Nivel> niveis = Arrays.asList(Nivel.values());

		totalPartes = (niveis.size() * assuntosCurso.size()) + (assuntosCurso.size() * niveis.size() * numListas);

		numExercicios = 0;
		numPaginas = 0;
		contPartes = 0;
		porcentagem = 0;

		try
		{
			for(AssuntoCurso assuntoCurso : assuntosCurso)
			{
				for(Nivel nivel : niveis)
				{
					finalOutputStream = construirListasExercicios(assuntoCurso, nivel);
					InputStream inStream = new ByteArrayInputStream(finalOutputStream.toByteArray());

					String nomeFile = (assuntoCurso.getOrdem() + 1) + "_" + assuntoCurso.getNome() + "_" + nivel.getNome();
					File file = new File(diretorio.getConfigLatex().getEnderecoEBook() + "/" + nomeFile + ".pdf");
					Files.copy(inStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Listas de exercícios criada com sucesso.");
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}

		return "";
	}

	private ByteArrayOutputStream construirListasExercicios(AssuntoCurso assuntoCurso, Nivel nivel)
	{
		finalOutputStream = new ByteArrayOutputStream();

		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf", false);

			ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assuntoCurso, nivel);
			for(int i = 0; i < numListas; i++)
				addExercicioLista(exercicio);

			totalPaginas = pdfWriter.getPageNumber();

			document.close();
			pdfWriter.close();

			diretorioService.freeDiretorio(diretorio);

			return baos;
		}
		catch(DocumentException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	private ByteArrayOutputStream construirListasExercicios(AssuntoCurso assuntoCurso, ConfigSpam configSpam)
	{
		finalOutputStream = new ByteArrayOutputStream();

		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento("/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf", false);

			for(Nivel nivel : Arrays.asList(Nivel.values()))
			{
				ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assuntoCurso, nivel);
				for(int i = 0; i < configSpam.qtn(nivel); i++)
					addExercicioLista(exercicio);
			}

			totalPaginas = pdfWriter.getPageNumber();

			document.close();
			pdfWriter.close();

			diretorioService.freeDiretorio(diretorio);

			return baos;
		}
		catch(DocumentException e1)
		{
			e1.printStackTrace();
		}
		return null;
	}

	private void addExercicioLista(ExercicioPadrao exercicio)
	{
		GerarLatexExercicio gerarLatex = new GerarLatexExercicio(diretorio);
		ConfigDownload configDownload = new ConfigDownload();
		configDownload.setIdentificacao(false);

		gerarLatex.gerarPDFExercicio(exercicio, configDownload);
		gerarLatex.gerar();
		numExercicios += exercicio.getQuantidade();

		addDocumento(new File(diretorio.getEnderecoPdf()), false);
	}

	public int getPorcentagemInteiro()
	{
		return (int)(porcentagem * 100);
	}
}
