package service.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import bean.download.Diretorio;
import bean.download.SetDownload;
import bean.exercicio.ConfigDownload;
import dao.exercicio.ExercicioPadraoDAO;
import dao.questao.QuestaoDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import modelo.academico.Assunto;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ProgramacaoPost;
import modelo.questao.Questao;
import pdf.exercicio.GerarLatexExercicio;
import pdf.questao.GerarLatexQuestao;
import pdf.social.InstagramFeed;
import pdf.social.TikTok;

@RequestScoped
public class MontadorPdfService
{
	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private QuestaoDAO questaoDAO;

	private Document document;
	private PdfContentByte pageContentByte;
	private PdfWriter pdfWriter;
	private ConfigDownload configDownloadExercicio;
	private ConfigDownload configDownloadQuestao;
	private Diretorio diretorio;
	private int contPartes;
	private int totalPartes;
	private int totalExercicios;
	private int totalQuestoes;
	private int totalPaginas;
	private Consumer<Double> onProgress;

	public byte[] montar(SetDownload setDownload, ConfigDownload exercicio, ConfigDownload questao,
	                     Diretorio diretorio, String basePath, Consumer<Double> onProgress)
	{
		this.configDownloadExercicio = exercicio;
		this.configDownloadQuestao = questao;
		this.diretorio = diretorio;
		this.onProgress = onProgress;

		totalPartes = calcularTotalPartes(setDownload);
		totalExercicios = 0;
		totalQuestoes = 0;
		totalPaginas = 0;
		contPartes = 0;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		document = new Document();

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			for (Assunto assunto : setDownload.getAssuntos())
			{
				if (setDownload.isAnotacao())
					addAnotacao(assunto, basePath);

				addNivel(assunto, Nivel.Nivel1, setDownload.getQuantidadeNivel1());
				addNivel(assunto, Nivel.Nivel2, setDownload.getQuantidadeNivel2());
				addNivel(assunto, Nivel.Nivel3, setDownload.getQuantidadeNivel3());

				if (setDownload.isQuestao())
					addQuestao(assunto);
			}

			document.close();
			pdfWriter.close();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}

		return baos.toByteArray();
	}

	public byte[] montarInstagram(SetDownload setDownload, ConfigDownload exercicio,
	                               Diretorio diretorio, ProgramacaoPost programacaoPost,
	                               boolean feed, Consumer<Double> onProgress)
	{
		this.configDownloadExercicio = exercicio;
		this.diretorio = diretorio;
		this.onProgress = onProgress;

		totalPartes = 2 * setDownload.getAssuntos().size() *
				(setDownload.getQuantidadeNivel1() + setDownload.getQuantidadeNivel2() + setDownload.getQuantidadeNivel3());
		totalExercicios = 0;
		totalQuestoes = 0;
		totalPaginas = 0;
		contPartes = 0;

		float larguraPixels = 259f;
		float alturaPixels = feed ? 323.75f : 460.5f;
		Rectangle tamanhoPagina = new Rectangle(larguraPixels, alturaPixels);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		document = new Document(tamanhoPagina);
		document.setPageSize(tamanhoPagina);

		try
		{
			pdfWriter = PdfWriter.getInstance(document, baos);
			document.open();
			pageContentByte = pdfWriter.getDirectContent();

			for (Assunto assunto : setDownload.getAssuntos())
			{
				addNivelInstagram(assunto, Nivel.Nivel1, setDownload.getQuantidadeNivel1(), programacaoPost, feed);
				addNivelInstagram(assunto, Nivel.Nivel2, setDownload.getQuantidadeNivel2(), programacaoPost, feed);
				addNivelInstagram(assunto, Nivel.Nivel3, setDownload.getQuantidadeNivel3(), programacaoPost, feed);
			}

			document.close();
			pdfWriter.close();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}

		return baos.toByteArray();
	}

	public int calcularTotalPartes(SetDownload setDownload)
	{
		int total = setDownload.getAssuntos().size() *
				(setDownload.getQuantidadeNivel1() + setDownload.getQuantidadeNivel2() + setDownload.getQuantidadeNivel3());

		if (setDownload.isResolucaoExercicio())
			total *= 2;

		if (setDownload.isAnotacao())
			total += setDownload.getAssuntos().size();

		if (setDownload.isQuestao())
			for (Assunto a : setDownload.getAssuntos())
				if (a.isShowQuestao())
					total++;

		return total;
	}

	private void addNivel(Assunto assunto, Nivel nivel, int quantidade)
	{
		if (quantidade <= 0)
			return;

		ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assunto, nivel);
		for (int i = 0; i < quantidade; i++)
			addExercicio(exercicio);
	}

	private void addNivelInstagram(Assunto assunto, Nivel nivel, int quantidade,
	                                ProgramacaoPost programacaoPost, boolean feed)
	{
		if (quantidade <= 0)
			return;

		ExercicioPadrao exercicio = exercicioPadraoDAO.buscar(assunto, nivel);
		for (int i = 0; i < quantidade; i++)
			addExercicioInstagram(exercicio, programacaoPost, feed);
	}

	private void addAnotacao(Assunto assunto, String basePath)
	{
		File file = new File(basePath,
				"pdf" + File.separator + assunto.getModulo().toString().toLowerCase()
				+ File.separator + assunto.getChave() + ".pdf");

		try (InputStream inputStream = new FileInputStream(file))
		{
			PdfReader reader = new PdfReader(inputStream);
			totalPaginas += reader.getNumberOfPages();
			for (int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				PdfImportedPage page = pdfWriter.getImportedPage(reader, j);
				pageContentByte.addTemplate(page, 0, 0);
			}
			pdfWriter.freeReader(reader);
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		notificarProgresso(1);
	}

	private void addExercicio(ExercicioPadrao exercicio)
	{
		GerarLatexExercicio gerarLatex = new GerarLatexExercicio(diretorio);
		gerarLatex.gerarPDFExercicio(exercicio, configDownloadExercicio);
		gerarLatex.gerar();
		totalExercicios += exercicio.getQuantidade();
		appendPdf(diretorio.getEnderecoPdf());
		notificarProgresso(configDownloadExercicio.isResolucao() ? 2 : 1);
	}

	private void addExercicioInstagram(ExercicioPadrao exercicio, ProgramacaoPost programacaoPost, boolean feed)
	{
		if (feed)
		{
			InstagramFeed gerarLatex = new InstagramFeed(diretorio);
			gerarLatex.gerarPDFExercicio(exercicio, programacaoPost);
			gerarLatex.gerarPDF();
		}
		else
		{
			TikTok gerarLatex = new TikTok(diretorio);
			gerarLatex.gerarPDFExercicio(exercicio, programacaoPost);
			gerarLatex.gerarPDF();
		}
		totalExercicios++;
		appendPdf(diretorio.getEnderecoPdf());
		notificarProgresso(2);
	}

	private void addQuestao(Assunto assunto)
	{
		List<Questao> questoes = questaoDAO.buscaAssunto(assunto, null, null);
		if (questoes.isEmpty())
			return;

		GerarLatexQuestao gerarLatex = new GerarLatexQuestao(diretorio);
		gerarLatex.gerarPDFQuestoes(questoes, configDownloadQuestao);
		gerarLatex.gerar();
		totalQuestoes += questoes.size();
		appendPdf(diretorio.getEnderecoPdf());
		notificarProgresso(1);
	}

	private void appendPdf(String caminho)
	{
		try (InputStream inputStream = new FileInputStream(new File(caminho)))
		{
			PdfReader reader = new PdfReader(inputStream);
			totalPaginas += reader.getNumberOfPages();
			for (int j = 1; j <= reader.getNumberOfPages(); j++)
			{
				document.newPage();
				PdfImportedPage page = pdfWriter.getImportedPage(reader, j);
				pageContentByte.addTemplate(page, 0, 0);
			}
			pdfWriter.freeReader(reader);
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void notificarProgresso(int incremento)
	{
		contPartes += incremento;
		onProgress.accept((double) contPartes / totalPartes);
	}

	public int getTotalExercicios() { return totalExercicios; }
	public int getTotalQuestoes() { return totalQuestoes; }
	public int getTotalPaginas() { return totalPaginas; }
}
