package pdf.questao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.pdf.draw.LineSeparator;

import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import modelo.usuario.Usuario;

public class QuestoesPDF
{

	public static final Font BOLD = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
	public static Font small = new Font(FontFamily.HELVETICA, 8);
	public static Font smallBold = new Font(FontFamily.HELVETICA, 8, Font.BOLD);

//	public static ByteArrayOutputStream gerarPDF(List<Questao> questoes, Usuario usuario)
//	{
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		
//		Document document = new Document();
//		try 
//		{
//			PdfWriter pdfWriter=PdfWriter.getInstance(document, baos);
//			document.open();
//			document.setPageSize(PageSize.A4);
//			document.setMargins(7,7,7,7);
//			PdfPTable table;
//			PdfPCell cell;
//			Paragraph p;
//			
//			table = new PdfPTable(2);
//			table.setWidthPercentage(100);
//			table.setWidths(new int[]{20,80});
//			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//			Image imagem = Image.getInstance(externalContext.getRealPath("Image")+"/Logo.png");
//			imagem.scaleAbsolute(100, 25);
////			imagem.setWidthPercentage(10);
//			cell = new PdfPCell(imagem, false);
//			cell.setBorder(0);
//			table.addCell(cell);
//			
//			p = new Paragraph();
//			p.add(new Phrase(exercicio.getModulo().getNome(),new Font(FontFamily.HELVETICA, 12, Font.BOLD,new BaseColor(151,162,255))));
//			p.add(new Phrase(" - ", new Font(FontFamily.HELVETICA, 12, Font.BOLD,new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
//			p.add(new Phrase(exercicio.getAssunto().getNome(),new Font(FontFamily.HELVETICA, 12, Font.BOLD,new BaseColor(17,199,170))));
//
////			p.add(exercicio.getNivel().getNome());
//			p.add(new Phrase("   Nota: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD,new BaseColor(151,162,255))));
//			
//			p.add(new Phrase("            Data: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD,new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
//			p.setAlignment(Element.ALIGN_MIDDLE);
//			cell = new PdfPCell(p);
//			cell.setBorder(0);
//			cell.setPaddingTop(5);
//			table.addCell(cell);
//			document.add(table);
//
//			
//			p = new Paragraph(10);
//			p.add(new Phrase("www.pratiqueja.com   ",  new Font(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
//			p.add(new Phrase("Nome: "+usuario.getNome(), small));
//			LineSeparator line = new LineSeparator();
//			line.setOffset(-5);
//			p.add(line);
//			
//			document.add(p);
//			
//			p = new Paragraph();
//			p.add(new Phrase("Instruções: ",new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(255,147,84))));
////			p.add(new Phrase("Instruções: ", smallBold));
//
//			p.add(new Phrase(exercicio.getEnunciado(), small));
//			p.setSpacingBefore(5);
//			p.setLeading(10);
//			p.setSpacingAfter(5);
//	        document.add(p);
//	        
//			table = new PdfPTable(2);
//			table.setWidthPercentage(100);
//			
//			for (int i = 0; i < listaContas.size(); i++)
//			{
//				cell = new PdfPCell();
//				Image image;
//				
//				PdfPTable tableMin = new PdfPTable(2);
//				tableMin.setWidthPercentage(100);
//				if(i<9)
//					tableMin.setWidths(new int[]{6,94});
//				else
//					tableMin.setWidths(new int[]{8,92});
//				
//				PdfPCell cellMin = new PdfPCell();
//				cellMin.setBorder(0);
//				p = new Paragraph(9);
//				p.add(new Phrase((i+1)+")",  new Font(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(151,162,255))));
//				cellMin.addElement(p);
//				tableMin.addCell(cellMin);
//				
//				if(listaContas.get(i).getBaos()!=null)
//				{
//					image=Image.getInstance(listaContas.get(i).getBaos().toByteArray());
//					image.setWidthPercentage(60);
//				}
//				else
//				{
//					image=Convert.toSVGImgTemplate(listaContas.get(i).getTextLatex(), true,pdfWriter);
//					image.setWidthPercentage(100*(float)image.getWidth()/(523/2));
//				}
//				
//				cellMin = new PdfPCell();
//				cellMin.setBorder(0);
//				cellMin.addElement(image);
//				tableMin.addCell(cellMin);
//				cell.addElement(tableMin);
//				
//				cell.setFixedHeight(130);
//				cell.setVerticalAlignment(Element.ALIGN_TOP);
//				
//				cell.setBorder(0);
//				if(i%2==0)
//					cell.setBorderWidthRight(1);
//				else
//					cell.setPaddingLeft(5);
//
//				table.addCell(cell);
//			}
//			
//			document.add(table);
//			
//			CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
//			separator.setDash(10);
//			separator.setGap(7);
//			Chunk linebreak = new Chunk(separator);
//			linebreak.setLineHeight(2);
//			document.add(linebreak);
//	        
//			p = new Paragraph(new Phrase("dobre ou recorte", small));
//			p.setLeading(-2);
//			document.add(p);
//			
//	        p = new Paragraph();
////			p.add(new Phrase("Gabarito     ", smallBold));
//			p.add(new Phrase("Gabarito     ",new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(255,147,84))));
//
//			p.add(new Phrase(exercicio.getModulo().getNome(),new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(151,162,255))));
//			p.add(new Phrase(" - ", new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
//			p.add(new Phrase(exercicio.getAssunto().getNome(),new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(17,199,170))));
//			
////			p.add(new Phrase("     Exercício: ", smallBold));
////			p.add(new Phrase(exercicio.getAssunto().getNome(), small));
////			p.add(new Phrase("     Nível: ", smallBold));
////			p.add(new Phrase(exercicio.getNivel().getNome(), small));
//			
//			document.add(p);
//			
//			table = new PdfPTable(5);
//			table.setWidthPercentage(100);
//			for (int i = 0; i < listaContas.size(); i++)
//			{
//				if(exercicio.getAssunto()==Assunto.Divisibilidade)
//				{
//					p=new Paragraph();
//					p.add(new Phrase((i+1)+") ",new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(151,162,255))));
//					p.add(new Phrase(listaContas.get(i).resultadoCorretoBolTexto(),small));
//					cell = new PdfPCell(p);
//					
//				}
//				else
//				{
//					p=new Paragraph();
//					p.add(new Phrase((i+1)+") ",new Font(FontFamily.HELVETICA, 8, Font.BOLD,new BaseColor(151,162,255))));
//					p.add(new Phrase(listaContas.get(i).getResultadoCorreto(),small));
//					cell = new PdfPCell(p);
//				}
//				cell.setBorder(0);
//				table.addCell(cell);
//			}
//			
//			document.add(table);
//		}
//		catch(DocumentException de) 
//		{
//			System.err.println(de.getMessage());
//		}
//		catch(IOException ioe) 
//		{
//			System.err.println(ioe.getMessage());
//		}
//		document.close();
//		
//		return baos;
//	}

	public static void gerarPDFFisico(List<Questao> questoes, Usuario usuario, String assunto)
	{
		Document document = new Document();
		try
		{
			document.open();
			document.setPageSize(PageSize.A4);
			document.setMargins(7, 7, 7, 7);
			PdfPTable table;
			PdfPCell cell;
			Paragraph p;

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 20, 80 });
			Image imagem = Image.getInstance("src/main/webapp/image/Logo.png");
			imagem.scaleAbsolute(100, 25);
//			imagem.setWidthPercentage(10);
			cell = new PdfPCell(imagem, false);
			cell.setBorder(0);
			table.addCell(cell);

			p = new Paragraph();
			p.add(new Phrase("Questões: ", BOLD));
			p.add(assunto);
//			p.add(new Phrase("   Nível: ", BOLD));
//			p.add(nivel.getNome());
			p.add(new Phrase("   Nota: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));

			p.add(new Phrase("            Data: ", new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
			p.setAlignment(Element.ALIGN_MIDDLE);
			cell = new PdfPCell(p);
//			cell.setBorder(0);
			cell.setPaddingTop(5);
			table.addCell(cell);
			document.add(table);

			p = new Paragraph(10);
			p.add(new Phrase("www.pratiqueja.com   ", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(BaseColor.LIGHT_GRAY.getRGB()))));
			p.add(new Phrase("Nome: " + usuario.getNome(), small));
			LineSeparator line = new LineSeparator();
			line.setOffset(-5);
			p.add(line);

			document.add(p);

//			p = new Paragraph();
//			p.add(new Phrase("Instruções: ", smallBold));
//			p.add(new Phrase("Informe o número decimal (ou inteiro) resultante das seguintes frações. Se o número decimal não for exato, informe somente até a 3 casa decimal. Não efetue arredondamento do último dígito. Utilize a vígula para representar as casas decimais. ", small));
//			p.setSpacingBefore(5);
//			p.setLeading(10);
//			p.setSpacingAfter(5);
//	        document.add(p);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
//			for (int i = 0; i < questoes.size(); i++)
//			{
//				cell = new PdfPCell();
//				Image image;
//				
//				PdfPTable tableMin = new PdfPTable(2);
//				tableMin.setWidthPercentage(100);
//				if(i<9)
//					tableMin.setWidths(new int[]{6,94});
//				else
//					tableMin.setWidths(new int[]{8,92});
//				
//				PdfPCell cellMin = new PdfPCell();
//				cellMin.setBorder(0);
//				p = new Paragraph(9);
//				p.add(new Phrase((i+1)+")",  new Font(FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(151,162,255))));
//				cellMin.addElement(p);
//				tableMin.addCell(cellMin);
//				
//				p = new Paragraph(10);
//				p.add(new Phrase(questao., smallBold));
//				
//				cellMin = new PdfPCell();
//				cellMin.setBorder(0);
//				cellMin.addElement(image);
//				tableMin.addCell(cellMin);
//				cell.addElement(tableMin);
//				
//				cell.setFixedHeight(130);
//				cell.setVerticalAlignment(Element.ALIGN_TOP);
//				
//				cell.setBorder(0);
//				if(i%2==0)
//					cell.setBorderWidthRight(1);
//				else
//					cell.setPaddingLeft(5);
//
//				table.addCell(cell);
//			}

			document.add(table);

//			CustomDashedLineSeparator separator = new CustomDashedLineSeparator();
//			separator.setDash(10);
//			separator.setGap(7);
////			separator.setOffset(-10);
////			separator.setPercentage(10);
//			Chunk linebreak = new Chunk(separator);
//			linebreak.setLineHeight(2);
//			document.add(linebreak);
////			linebreak.append("dobre ou recorte");
////			p.add(linebreak);
//			
////	        document.add(new Chunk("\n"));
//	        
//			p = new Paragraph(new Phrase("dobre ou recorte", small));
////			p.setAlignment(Element.ALIGN_RIGHT);
//			p.setLeading(-2);
//			document.add(p);
//			
//	        p = new Paragraph();
//			p.add(new Phrase("Gabarito", smallBold));
//			p.add(new Phrase("     Exercício: ", smallBold));
//			p.add(new Phrase(assunto.getNome(), small));
//			p.add(new Phrase("     Nível: ", smallBold));
//			p.add(new Phrase(nivel.getNome(), small));
//			
////			document.add(new Chunk("\n"));
//			document.add(p);
//			
//			table = new PdfPTable(5);
//			table.setWidthPercentage(100);
//			for (int i = 0; i < listaContas.size(); i++)
//			{
//				if(assunto==Assunto.Divisibilidade)
//					cell = new PdfPCell(new Paragraph((i+1)+") "+listaContas.get(i).resultadoCorretoBolTexto(),small));
//				else
//					cell = new PdfPCell(new Paragraph((i+1)+") "+listaContas.get(i).getResultadoCorreto(),small));
//				cell.setBorder(0);
//				table.addCell(cell);
//			}
//			
//			document.add(table);
		}
		catch(DocumentException de)
		{
			System.err.println(de.getMessage());
		}
		catch(IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void main(String[] args)
	{

		Usuario usuario = new Usuario();
		usuario.setNome("Vinícius Rosa Máximo");
		Questao questao = new Questao();
		Paragrafo paragrafo = new Paragrafo();
		paragrafo.setTexto("Ola");

		List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();
		paragrafos.add(paragrafo);
		questao.setParagrafos(paragrafos);

		List<Questao> questoes = new ArrayList<Questao>();
		questoes.add(questao);

		QuestoesPDF.gerarPDFFisico(questoes, usuario, "Potência");

	}
}
