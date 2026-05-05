package Infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class EditPdf
{
	public static void main(String[] args)
	{
		String file = "Anotacao.pdf";
		String fileNew = "AnotacaoNew.pdf";
		PdfReader reader;
		try
		{

			reader = new PdfReader(new FileInputStream(file));
			System.out.println("NÚMERO DE PAGINAS: " + reader.getNumberOfPages() + " - " + reader.getInfo() + " - " + reader.getFileLength());

			PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(fileNew));

			int totalPaginas = reader.getNumberOfPages() + 1;

			for(int i = 1; i < totalPaginas; i++)
			{
				PdfContentByte pdfContentByte = pdfStamper.getUnderContent(i);
				pdfContentByte.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, true), 12);

				// inserindo textos no arquivo.
				pdfContentByte.beginText();
				pdfContentByte.setTextMatrix(250, 50);
				pdfContentByte.newlineShowText(0, 0, "Vinícius Rosa Máximo");
				pdfContentByte.endText();

			}
			pdfStamper.close();

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}

	}

}
