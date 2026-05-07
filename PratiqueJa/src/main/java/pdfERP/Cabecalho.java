package pdfERP;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;

import session.Sessao;

public class Cabecalho
{
	static PdfStyle style = new PdfStyle();
	
	public static void criarCabecalho(Document document, String tituloTexto )
	{
		PdfPTable headerTable = new PdfPTable(2);
		headerTable.setWidthPercentage(100);
		try
		{
			headerTable.setWidths(new float[] { 0.7f, 4.3f });
			
			headerTable.setSpacingAfter(10f);

			// Logo
			Image logo = Image.getInstance(Sessao.externalContext().getRealPath("image") + "/logo.png");
			logo.scaleToFit(100, 100);
			PdfPCell logoCell = new PdfPCell(logo);
			logoCell.setBorder(Rectangle.NO_BORDER);
			logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			headerTable.addCell(logoCell);

			// Título
			Paragraph titulo = new Paragraph(tituloTexto, style.tituloFont);
			titulo.setAlignment(Element.ALIGN_CENTER);
			PdfPCell tituloCell = new PdfPCell();
			tituloCell.addElement(titulo);
			tituloCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tituloCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tituloCell.setBorder(Rectangle.NO_BORDER);
			headerTable.addCell(tituloCell);

			document.add(headerTable);
			
			LineSeparator line = new LineSeparator(1, 100, style.corTexto, 0, 5);

			Paragraph p = new Paragraph(0);
			p.add(line);
			document.add(p);
			
		}
		catch(DocumentException | IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
