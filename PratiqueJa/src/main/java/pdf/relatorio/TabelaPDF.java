package pdf.relatorio;

import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class TabelaPDF
{
	public static void exportar(List<String[]> dados, String nome, OutputStream out) throws Exception
	{
		
		Rectangle retangle=PageSize.A4;
		if(dados.size()>0&&dados.get(0).length>4)
			retangle = PageSize.A4.rotate();
		Document document = new Document(retangle);
		document.setMargins(40, 40, 20, 50);
		
		PdfWriter pdfWriter =PdfWriter.getInstance(document, out);
		
		HeaderTabela header = new HeaderTabela();
		header.title=nome;
		pdfWriter.setPageEvent(header);
		
		document.open();

		document.add(TabelaDadosPDF.criarTabela(dados));
		
		document.close();
		
	}
	
}
