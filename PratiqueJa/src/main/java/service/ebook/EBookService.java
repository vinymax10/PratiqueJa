package service.ebook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import bean.download.Diretorio;
import bean.ebook.HeaderEBook;
import bean.exercicio.ConfigDownload;
import dao.exercicio.ExercicioPadraoDAO;
import dao.questao.QuestaoDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.email.ConfigSpam;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.questao.Questao;
import pdfAntigo.ebook.ExercicioEBook;
import pdfAntigo.ebook.FolhaRosto;
import pdfAntigo.ebook.ItemSumario;
import pdfAntigo.ebook.QuestaoEBook;
import pdfAntigo.ebook.Resumo;
import pdfAntigo.ebook.Sumario;
import pdfAntigo.exercicio.GerarLatexExercicio;
import service.configuracao.DiretorioService;

@RequestScoped
public class EBookService
{
	private static final Logger logger = Logger.getLogger(EBookService.class.getName());

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private QuestaoDAO questaoDAO;

	@Inject
	private DiretorioService diretorioService;

	private Document document;
	private PdfContentByte pageContentByte;
	private PdfWriter pdfWriter;
	private ByteArrayOutputStream baos;
	private ByteArrayOutputStream finalOutputStream;
	private PdfReader readerOriginal, reader;
	private HeaderEBook header;
	private PdfImportedPage page;
	private InputStream inputStream;
	private Diretorio diretorio;
	private List<ItemSumario> itens;
	private List<Assunto> assuntos;
	private int numListas;
	private int contPartes, totalPartes;
	private int numExercicios, numQuestoes, numPaginas, totalPaginas;
	private Consumer<Double> onProgress;

	public ByteArrayOutputStream construirEBook(Modulo modulo, int edicao, int numListasParam, boolean capa,
	                                             List<Assunto> assuntosParam, String basePath,
	                                             Consumer<Double> progressCallback)
	{
		this.assuntos = assuntosParam;
		this.numListas = numListasParam;
		this.onProgress = progressCallback;
		itens = new ArrayList<>();
		finalOutputStream = new ByteArrayOutputStream();

		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		iniciarPorcentagem(capa);

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			int passoPage = 3;

			if(capa)
				addDocumento(basePath, "/pdf/ebook/capa" + modulo + ".pdf", true);
			else
				passoPage += 2;

			addFolhaRosto(modulo, edicao);

			int cont = 1;
			for(Assunto assunto : assuntos)
			{
				itens.add(new ItemSumario(assunto.getNome(), "" + cont++, "" + (pdfWriter.getPageNumber() + passoPage)));
				logger.fine("itemSumario: " + itens.get(itens.size() - 1));

				addDocumento(basePath, "/pdf/" + assunto.getModulo().toString().toLowerCase() + "/" + assunto.getChave() + ".pdf", true);

				ExercicioPadrao exercicio;
				exercicio = exercicioPadraoDAO.buscar(assunto, Nivel.Nivel1);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assunto, Nivel.Nivel2);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				exercicio = exercicioPadraoDAO.buscar(assunto, Nivel.Nivel3);
				for(int i = 0; i < numListas; i++)
					addExercicioEBook(exercicio);

				addQuestao(assunto);
			}

			if(capa)
				addDocumento(basePath, "/pdf/ebook/contraCapa" + modulo + ".pdf", true);

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
				header.setPagInicial(header.getPagInicial() + 2);

			for(int i = 1; i <= posicaoSumario; i++)
			{
				document.newPage();
				page = pdfWriter.getImportedPage(readerOriginal, i);
				pageContentByte.addTemplate(page, 0, 0);
			}

			header.setAddPage(true);

			addSumario();
			int indexAssunto = 0;

			for(int i = posicaoSumario + 1; i <= readerOriginal.getNumberOfPages(); i++)
			{
				if(itens.size() > (indexAssunto + 1) && itens.get(indexAssunto + 1).pagina.equals("" + (i + passoPage - 1)))
					indexAssunto++;

				Assunto assunto = assuntos.get(indexAssunto);
				if(capa && i > (readerOriginal.getNumberOfPages() - 2))
				{
					header.setAssunto("");
					header.setAddPage(false);
				}
				else
					header.setAssunto(assunto.getNome());

				document.newPage();
				page = pdfWriter.getImportedPage(readerOriginal, i);
				pageContentByte.addTemplate(page, 0, 0);
			}

			document.close();
			pdfWriter.close();
			readerOriginal.close();

			diretorioService.freeDiretorio(diretorio);

			return finalOutputStream;
		}
		catch(DocumentException | IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ByteArrayOutputStream construirListasExercicios(Assunto assunto, Nivel nivel, int numListasParam,
	                                                        String basePath, Consumer<Double> progressCallback)
	{
		this.numListas = numListasParam;
		this.onProgress = progressCallback;
		finalOutputStream = new ByteArrayOutputStream();
		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		totalPartes = 1 + numListas;
		contPartes = 0;
		numExercicios = 0;
		numPaginas = 0;

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento(basePath, "/pdf/" + assunto.getModulo().toString().toLowerCase() + "/" + assunto.getChave() + ".pdf", false);

			ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assunto, nivel);
			for(int i = 0; i < numListas; i++)
				addExercicioLista(exercicio);

			totalPaginas = pdfWriter.getPageNumber();
			document.close();
			pdfWriter.close();

			diretorioService.freeDiretorio(diretorio);

			return baos;
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public ByteArrayOutputStream construirListasExercicios(Assunto assunto, ConfigSpam configSpam,
	                                                        String basePath, Consumer<Double> progressCallback)
	{
		this.onProgress = progressCallback;
		finalOutputStream = new ByteArrayOutputStream();
		diretorio = diretorioService.criarDiretorio();
		baos = new ByteArrayOutputStream();
		document = new Document();

		List<Nivel> niveis = Arrays.asList(Nivel.values());
		totalPartes = 1 + niveis.stream().mapToInt(configSpam::qtn).sum();
		contPartes = 0;
		numExercicios = 0;
		numPaginas = 0;

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			addDocumento(basePath, "/pdf/" + assunto.getModulo().toString().toLowerCase() + "/" + assunto.getChave() + ".pdf", false);

			for(Nivel nivel : niveis)
			{
				ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assunto, nivel);
				for(int i = 0; i < configSpam.qtn(nivel); i++)
					addExercicioLista(exercicio);
			}

			totalPaginas = pdfWriter.getPageNumber();
			document.close();
			pdfWriter.close();

			diretorioService.freeDiretorio(diretorio);

			return baos;
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private void iniciarPorcentagem(boolean capa)
	{
		int numeroNiveis = 3;
		totalPartes = assuntos.size() + 2 + (assuntos.size() * numeroNiveis * numListas);
		if(capa)
			totalPartes += 2;

		for(Assunto assunto : assuntos)
			if(assunto.isShowQuestao())
				totalPartes++;

		numExercicios = 0;
		numQuestoes = 0;
		numPaginas = 0;
		contPartes = 0;
	}

	private Resumo montarResumo()
	{
		Resumo resumo = new Resumo();
		resumo.setAssuntos(assuntos.size() + "");
		resumo.setPaginas(totalPaginas + "");
		resumo.setExecicios(numExercicios + "");
		resumo.setQuestoes(numQuestoes + "");
		resumo.setListas(assuntos.size() * numListas * 3 + "");
		return resumo;
	}

	private void addDocumento(String basePath, String endereco, boolean addPagePar)
	{
		addDocumento(new File(basePath + endereco), addPagePar);
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
		catch(IOException | DocumentException e)
		{
			e.printStackTrace();
		}
		contPartes++;
		onProgress.accept((double) contPartes / totalPartes);
		logger.fine(contPartes + "/" + totalPartes);
	}

	private void addFolhaRosto(Modulo modulo, int edicao)
	{
		FolhaRosto gerarLatex = new FolhaRosto(diretorio);
		gerarLatex.gerarPDFExercicio(modulo, edicao);
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

	private void addQuestao(Assunto assunto)
	{
		List<Questao> questoes = questaoDAO.buscaAssunto(assunto, null, null);
		if(!questoes.isEmpty())
		{
			QuestaoEBook gerarLatex = new QuestaoEBook(diretorio);
			gerarLatex.gerarPDFQuestoes(questoes);
			gerarLatex.gerar();
			numQuestoes += questoes.size();
			addDocumento(new File(diretorio.getEnderecoPdf()), true);
		}
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

	public Diretorio getDiretorio() { return diretorio; }
	public int getNumExercicios() { return numExercicios; }
	public int getNumQuestoes() { return numQuestoes; }
	public int getTotalPaginas() { return totalPaginas; }
}
