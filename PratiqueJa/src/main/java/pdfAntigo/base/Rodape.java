package pdfAntigo.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.usuario.Usuario;
import web.session.Sessao;

public class Rodape
{
	static int margin = 40;
	static int rodape = 50;
	static PdfStyle style = new PdfStyle();

	public static void criarRodape(PdfWriter pdfWriter, Document document, Image total)
	{
		PdfContentByte canvas = pdfWriter.getDirectContent();
		canvas.setColorStroke(style.corTexto);

		canvas.moveTo(margin, rodape);
		canvas.lineTo(document.getPageSize().getWidth() - margin, rodape);
		canvas.stroke();

//			-----------------------------------------------------------------

		PdfPTable footer = new PdfPTable(3);
		try
		{
			// set defaults
			
			footer.setWidthPercentage(100);
			footer.setWidths(new int[] { 70, 25, 5 });
			footer.setTotalWidth(document.getPageSize().getWidth() - (2 * margin));
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(30);
			footer.getDefaultCell().setBorder(0);
			footer.getDefaultCell().setPaddingTop(7);

			footer.getDefaultCell().setBorderColor(style.corTexto);

			Usuario usuario = Sessao.getUsuarioLogado();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String texto="Emitido por: "+usuario.getNome()+" em "+formatter.format(now);
			footer.addCell(new Phrase(texto, style.textoFont));

			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footer.addCell(new Phrase(String.format("página %d de", pdfWriter.getPageNumber()), style.textoFont));

			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(style.corTexto);
			footer.addCell(totalPageCount);

			canvas = pdfWriter.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, margin, rodape, canvas);
			canvas.endMarkedContentSequence();

		}
		catch(DocumentException de)
		{
			throw new ExceptionConverter(de);
		}
	}
}
