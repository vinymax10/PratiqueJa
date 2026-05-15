package bean.ebook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderEBook extends PdfPageEventHelper
{
	static final BaseColor cor   = new BaseColor(102, 102, 102);
	static final BaseColor verde = new BaseColor(0, 117, 94);
	static final BaseColor iris  = new BaseColor(99, 112, 255);

	static final Font fontAssunto = new Font(FontFamily.HELVETICA, 10, Font.BOLD, verde);
	static final Font fontPage    = new Font(FontFamily.HELVETICA, 10, Font.BOLD, iris);

	static final int margin = 50;
	static final int rodape = 50;

	String assunto;
	boolean addPage = false;
	int pagInicial = 0;

	@Override
	public void onStartPage(PdfWriter pdfWriter, Document document)
	{
		if(!addPage)
			return;

		PdfContentByte canvas = pdfWriter.getDirectContent();
		canvas.moveTo(margin, rodape);

		PdfPTable footer = new PdfPTable(1);
		try
		{
			footer.setWidthPercentage(100);
			footer.setWidths(new int[] { 100 });
			footer.setTotalWidth(document.getPageSize().getWidth() - (2 * margin));
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(30);
			footer.getDefaultCell().setBorder(0);
			footer.getDefaultCell().setPaddingTop(7);
			footer.getDefaultCell().setBorderColor(cor);
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

			Phrase assunto = new Phrase(this.assunto, fontAssunto);
			Phrase page    = new Phrase("      " + (pdfWriter.getPageNumber() + pagInicial), fontPage);
			Paragraph p = new Paragraph();
			p.add(assunto);
			p.add(page);

			footer.addCell(p);

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
