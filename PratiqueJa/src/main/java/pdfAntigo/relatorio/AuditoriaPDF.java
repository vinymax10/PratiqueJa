package pdfAntigo.relatorio;

import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import filtro.auditoria.FiltroAuditoria;
import modelo.auditoria.AuditoriaEvento;
import pdfAntigo.base.PdfStyle;

public class AuditoriaPDF
{
	static PdfStyle style = new PdfStyle();

	public static void exportar(List<AuditoriaEvento> auditorias, FiltroAuditoria filtro, OutputStream out) throws Exception
	{
		Document document = new Document(PageSize.A4);
		document.setMargins(40, 40, 20, 50);

		PdfWriter pdfWriter =PdfWriter.getInstance(document, out);
		
		HeaderAuditoria header = new HeaderAuditoria();
		pdfWriter.setPageEvent(header);
		
		document.open();
		
		FiltroAuditoriaPDF.criarFiltro(document,filtro);

		document.add(TabelaAuditoriaPDF.criarTabela(auditorias));
		
		document.close();
	}
}
