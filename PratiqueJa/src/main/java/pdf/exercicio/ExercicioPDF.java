package pdf.exercicio;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import bean.exercicio.ConfigDownload;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import modelo.academico.AssuntoCurso;
import modelo.academico.Modulo;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.exercicio.TipoExercicio;
import modelo.matematica.Conta;
import modelo.usuario.Usuario;
import pdf.base.CustomDashedLineSeparator;
import pdf.util.Convert;

public class ExercicioPDF
{
	private static final Logger LOG = LoggerFactory.getLogger(ExercicioPDF.class);

	static int margin = 40;
	static BaseColor cor = new BaseColor(102, 102, 102);
	public static final Font BOLD = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
	public static Font small = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, cor);
	public static Font smallBold = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

	public static ByteArrayOutputStream gerarPDF(ExercicioPadrao exercicioPadrao, ConfigDownload configDownload)
	{
		List<Conta> listaContas = new ArrayList<Conta>();
		Conta conta;
		for(int i = 0; i < exercicioPadrao.getQuantidade(); i++)
		{
			try
			{
				conta = (Conta) Class.forName(exercicioPadrao.getClasse()).getConstructor(Integer.TYPE).newInstance(i + 1);
				listaContas.add(conta);
			}
			catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document();
		try
		{
			PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
			document.setPageSize(PageSize.A4);
			document.setMargins(margin, margin, margin - 10, margin - 10);
			document.open();

			cabecalho(document, exercicioPadrao, configDownload);

			listaExercicios(document, listaContas, exercicioPadrao, pdfWriter, configDownload);

			if(configDownload.isRespostas())
				rodape(document, listaContas, exercicioPadrao);

			if(configDownload.isResolucao()	&& listaContas.get(0).possuiResolucao())
			{
				document.newPage();
				cabecalho(document, exercicioPadrao, configDownload);
				listaResolucao(document, listaContas, exercicioPadrao, pdfWriter);
			}

		}
		catch(DocumentException de)
		{
			LOG.error("Erro ao gerar PDF de exercícios", de);
		}
		document.close();

		return baos;
	}

	private static void cabecalho(Document document, ExercicioPadrao exercicio, ConfigDownload configDownload)
	{
		PdfPTable table;
		PdfPCell cell;
		Paragraph p;

		try
		{
			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 20, 80 });
			Image imagem = getLogo();
			imagem.scaleAbsolute(100, 25);
//			imagem.setWidthPercentage(10);
			cell = new PdfPCell(imagem, false);
			cell.setBorder(0);
			table.addCell(cell);

			p = new Paragraph();
			p.add(new Phrase("   " + exercicio.getAssuntoCurso().getModulo().getNome(),
			new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(151, 162, 255))));
			p.add(new Phrase(" - ", new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(255, 147, 84))));
			p.add(new Phrase(exercicio.getAssuntoCurso().getNome(),
			new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(17, 199, 170))));

//			p.add(exercicio.getNivel().getNome());
			p.add(new Phrase("   Nota: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(151, 162, 255))));

			p.add(new Phrase("            Data: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD, cor)));
			p.setAlignment(Element.ALIGN_MIDDLE);
			cell = new PdfPCell(p);
			cell.setBorder(0);
			cell.setPaddingTop(5);
			table.addCell(cell);
			document.add(table);

			p = new Paragraph(10);
			p.add(new Phrase("www.pratiqueja.com   ", new Font(FontFamily.HELVETICA, 10, Font.BOLD, cor)));

			if(configDownload.isIdentificacao())
				p.add(new Phrase("     Nome: " + configDownload.getUsuario().getNome(), small));
			else
				p.add(new Phrase("     Nome: ", small));

			LineSeparator line = new LineSeparator(1, 100, cor, 0, -5);
			p.add(line);

			document.add(p);
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
	}

	private static Image getLogo()
	{
		Image imagem = null;
		try
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			imagem = Image.getInstance(externalContext.getRealPath("image") + "/logo.png");
		}
		catch(java.lang.NoSuchMethodError | BadElementException | IOException e)
		{
			try
			{
				imagem = Image.getInstance("src/main/webapp/image/logo.png");
			}
			catch(BadElementException | IOException e1)
			{
				e1.printStackTrace();
			}
		}

		return imagem;
	}

	private static void rodape(Document document, List<Conta> listaContas, ExercicioPadrao exercicio)
	{
		PdfPTable table;
		PdfPCell cell;
		Paragraph p;

		try
		{
			CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
			separator.setDash(10);
			separator.setGap(7);
			separator.setLineColor(cor);
			Chunk linebreak = new Chunk(separator);
			linebreak.setLineHeight(2);
			document.add(linebreak);

			p = new Paragraph(new Phrase("dobre ou recorte", small));
			p.setLeading(-2);
			document.add(p);

			p = new Paragraph();
			p.add(
			new Phrase("Gabarito     ", new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255, 147, 84))));

			p.add(new Phrase(exercicio.getAssuntoCurso().getModulo().getNome(),
			new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(151, 162, 255))));
			p.add(new Phrase(" - ", new Font(FontFamily.HELVETICA, 8, Font.BOLD, cor)));
			p.add(new Phrase(exercicio.getAssuntoCurso().getNome(),
			new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(17, 199, 170))));

			document.add(p);

			table = new PdfPTable(listaContas.size() / 2);
			table.setWidthPercentage(100);
			for(int i = 0; i < listaContas.size(); i++)
			{
				p = new Paragraph();
				p.add(
				new Phrase((i + 1) + ") ", new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(151, 162, 255))));

				if(exercicio.getAssuntoCurso().getChave().equals("Divisibilidade"))
					p.add(new Phrase(listaContas.get(i).resultadoCorretoBolTexto(), small));
				else
					p.add(new Phrase(listaContas.get(i).getResultadoCorreto(), small));

				cell = new PdfPCell(p);
				cell.setBorder(0);
				table.addCell(cell);
			}

			document.add(table);
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
	}

	private static void listaResolucao(Document document, List<Conta> listaContas, ExercicioPadrao exercicio,
	PdfWriter pdfWriter)
	{

		PdfPTable table;
		PdfPCell cell;
		Paragraph p;

		try
		{
			p = new Paragraph();
			p
			.add(new Phrase("Instruções: ", new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255, 147, 84))));
//			p.add(new Phrase("Instruções: ", smallBold));

			p.add(new Phrase(exercicio.getEnunciado(), small));
			p.setSpacingBefore(5);
			p.setLeading(10);
			p.setSpacingAfter(5);
			document.add(p);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);

			for(int i = 0; i < listaContas.size(); i++)
			{
				cell = new PdfPCell();

				PdfPTable tableMin = new PdfPTable(2);
				tableMin.setWidthPercentage(100);
				if(i < 9)
					tableMin.setWidths(new int[] { 6, 94 });
				else
					tableMin.setWidths(new int[] { 8, 92 });

				PdfPCell cellMin = new PdfPCell();
				cellMin.setBorder(0);
				p = new Paragraph(7);
				p.add(new Phrase((i + 1) + ")", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(151, 162, 255))));
				cellMin.addElement(p);
				tableMin.addCell(cellMin);

				if(listaContas.get(i).possuiResolucao())
				{
					cellMin = new PdfPCell();
					cellMin.setBorder(0);
					
					if(exercicio.isMostrarResolucao())
					{
						Image imageConta = getImageResolucao(listaContas.get(i),pdfWriter);
						cellMin.addElement(imageConta);
						p = new Paragraph(7);
						p.add(new Phrase(" ", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(151, 162, 255))));
						cellMin.addElement(p);
					}
					
					if(listaContas.get(i).possuiResolucaoLatex())
					{
						Image imageResolucao;
						imageResolucao = Convert.toSVGImgTemplate(listaContas.get(i).getResolucaoLatex(), pdfWriter, 260, true);
						imageResolucao.setWidthPercentage(100 * (float) imageResolucao.getWidth() / (523 / 2));
						cellMin.addElement(imageResolucao);
					}
					
					tableMin.addCell(cellMin);
					cell.addElement(tableMin);
				}

				cell.setFixedHeight(720 / (listaContas.size() / 2));
				cell.setVerticalAlignment(Element.ALIGN_TOP);

				cell.setBorder(0);
				cell.setBorderColor(cor);
				if(i % 2 == 0)
					cell.setBorderWidthRight(1);
				else
					cell.setPaddingLeft(5);

				table.addCell(cell);
			}

			document.add(table);
		}
		catch(DocumentException | IOException e)
		{
			e.printStackTrace();
		}
	}

	private static Image getImageConta(Conta conta, PdfWriter pdfWriter)
	{
		Image image=null;
		try
		{
			if(conta.getBaos() != null)
			{
				image = Image.getInstance(conta.getBaos().toByteArray());
				image.scalePercent(60);
			}
			else
			{
				image = Convert.toSVGImgTemplate(conta.getTextLatex(), pdfWriter, 200, false);
				image.setWidthPercentage(100 * (float) image.getWidth() / (523 / 2));
			}
		}
		catch(IOException | BadElementException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	
	private static Image getImageResolucao(Conta conta, PdfWriter pdfWriter)
	{
		Image image=null;
		try
		{
			if(conta.getBaosResolucao() != null)
			{
				image = Image.getInstance(conta.getBaosResolucao().toByteArray());
				image.setWidthPercentage(60);
			}
			else if(conta.getBaos() != null)
			{
				image = Image.getInstance(conta.getBaos().toByteArray());
				image.setWidthPercentage(60);
			}
			else
			{
				image = Convert.toSVGImgTemplate(conta.getTextLatex(), pdfWriter, 200, false);
				image.setWidthPercentage(100 * (float) image.getWidth() / (523 / 2));
			}
			
		}
		catch(IOException | BadElementException e)
		{
			e.printStackTrace();
		}
		return image;
	}
	
	private static void listaExercicios(Document document, List<Conta> listaContas, ExercicioPadrao exercicioPadrao,
	PdfWriter pdfWriter, ConfigDownload configDownload)
	{
		PdfPTable table;
		PdfPCell cell;
		Paragraph p;

		try
		{
			p = new Paragraph();
			p
			.add(new Phrase("Instruções: ", new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(255, 147, 84))));
//			p.add(new Phrase("Instruções: ", smallBold));

			p.add(new Phrase(exercicioPadrao.getEnunciado(), small));
			p.setSpacingBefore(5);
			p.setLeading(10);
			p.setSpacingAfter(5);
			document.add(p);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);

			for(int i = 0; i < listaContas.size(); i++)
			{
				cell = new PdfPCell();

				Image image;

				PdfPTable tableMin = new PdfPTable(2);
				tableMin.setWidthPercentage(100);
				if(i < 9)
					tableMin.setWidths(new int[] { 6, 94 });
				else
					tableMin.setWidths(new int[] { 8, 92 });

				PdfPCell cellMin = new PdfPCell();
				cellMin.setBorder(0);
				p = new Paragraph(7);
				p.add(
				new Phrase((i + 1) + ")", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(151, 162, 255))));
				cellMin.addElement(p);
				tableMin.addCell(cellMin);
				
				image = getImageConta(listaContas.get(i),pdfWriter);

				cellMin = new PdfPCell();
				cellMin.setBorder(0);
				cellMin.addElement(image);
				tableMin.addCell(cellMin);
				cell.addElement(tableMin);

				if(listaContas.get(i).getPergunta() != null)
				{
					image = Convert.toSVGImgTemplate(listaContas.get(i).getPergunta(), pdfWriter, 200, false);
					image.setWidthPercentage(100 * (float) image.getWidth() / (523 / 2));
					tableMin = new PdfPTable(1);
					tableMin.setWidthPercentage(100);
					cellMin = new PdfPCell();
					cellMin.setBorder(0);
					cellMin.setPaddingTop(10);
					cellMin.addElement(image);
					tableMin.addCell(cellMin);
					cell.addElement(tableMin);
				}

				int alturaTotal = 670;
				if(!configDownload.isRespostas())
					alturaTotal = 710;

				cell.setFixedHeight(alturaTotal / (listaContas.size() / 2));
				cell.setVerticalAlignment(Element.ALIGN_TOP);

				cell.setBorder(0);
				cell.setBorderColor(cor);
				if(i % 2 == 0)
					cell.setBorderWidthRight(1);
				else
					cell.setPaddingLeft(5);

				table.addCell(cell);
			}

			document.add(table);
		}
		catch(DocumentException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void gerarPDFFisico(ExercicioPadrao exercicioPadrao, ConfigDownload configDownload)
	{
		List<Conta> listaContas = new ArrayList<Conta>();
		Conta conta;
		for(int i = 0; i < exercicioPadrao.getQuantidade(); i++)
		{
			try
			{
				conta = (Conta) Class.forName(exercicioPadrao.getClasse()).getConstructor(Integer.TYPE).newInstance(i + 1);
				listaContas.add(conta);
			}
			catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		Document document = new Document();
		try
		{
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("PDF_LinhaCodigo.pdf"));
			document.open();
			document.setPageSize(PageSize.A4);
			document.setMargins(7, 7, 7, 7);

			cabecalho(document, exercicioPadrao, configDownload);

			listaExercicios(document, listaContas, exercicioPadrao, pdfWriter, configDownload);

			rodape(document, listaContas, exercicioPadrao);
		}
		catch(DocumentException de)
		{
			LOG.error("Erro ao gerar PDF de exercícios", de);
		}
		catch(IOException ioe)
		{
			LOG.error("Erro ao gerar PDF de exercícios", ioe);
		}
		document.close();
	}

	public static void main(String[] args)
	{

		Usuario usuario = new Usuario();
		usuario.setNome("Vinícius Rosa Máximo");
		ExercicioPadrao exercicio = new ExercicioPadrao();
		exercicio.setNivel(Nivel.Nivel2);
		exercicio.setNome("FuncaoAfimNivel2");
		exercicio.setQuantidade(6);
		exercicio.setTipoExercicio(TipoExercicio.Image);
		AssuntoCurso assuntoCurso = new AssuntoCurso();
		assuntoCurso.setChave("FuncaoAfim");
		assuntoCurso.setNome("Função Afim");
		assuntoCurso.setModulo(Modulo.Basico);
		exercicio.setAssuntoCurso(assuntoCurso);
		ConfigDownload configDownload = new ConfigDownload();
		ExercicioPDF.gerarPDFFisico(exercicio, configDownload);

	}
}
