package pdfERP.tabela;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import pdfERP.Cabecalho;
import pdfERP.PdfStyle;
import pdfERP.Rodape;

public class HeaderTabela extends PdfPageEventHelper
{
	PdfStyle style = new PdfStyle();

	Image total;
	PdfTemplate t;
	public String title;
	
	public HeaderTabela()
	{
		super();
	}

	public void onStartPage(PdfWriter pdfWriter, Document document)
	{
		Cabecalho.criarCabecalho(document,title);
	}

	public void onEndPage(PdfWriter pdfWriter, Document document)
	{
		Rodape.criarRodape(pdfWriter,document,total);
	}

	public void onOpenDocument(PdfWriter writer, Document document)
	{
		t = writer.getDirectContent().createTemplate(30, 16);
		try
		{
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		}
		catch(DocumentException de)
		{
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter pdfWriter, Document document)
	{
		int totalLength = String.valueOf(pdfWriter.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT, new Phrase(String.valueOf(pdfWriter.getPageNumber()), style.textoFont), totalWidth, 1, 0);
	}
}
