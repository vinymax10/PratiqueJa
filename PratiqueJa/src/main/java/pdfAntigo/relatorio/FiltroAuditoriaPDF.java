package pdfAntigo.relatorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import filtro.auditoria.FiltroAuditoria;
import modelo.auditoria.EntidadeAuditavel;
import pdfAntigo.base.PdfStyle;

public class FiltroAuditoriaPDF
{
	static PdfStyle style = new PdfStyle();

	public static void criarFiltro(Document document, FiltroAuditoria filtro) throws Exception
	{
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
//		table.setSpacingBefore(7f);

		table.addCell(addFiltro("Filtros utilizados:",""));
		table.addCell(addFiltro("",""));
		table.addCell(addFiltro("",""));
		
		int contFiltros=0;
		if(filtro.getEntidade()!= null && !filtro.getEntidade().isBlank())
		{
			table.addCell(addFiltro("Entidade: ",EntidadeAuditavel.getNomePorSimpleName(filtro.getEntidade())));
			contFiltros++;
		}
		
		if(filtro.getEntidadeId()!= null)
		{
			table.addCell(addFiltro("ID da Entidade: ",String.valueOf(filtro.getEntidadeId())));
			contFiltros++;
		}
		
		if(filtro.getIp()!= null && !filtro.getIp().isBlank())
		{
			table.addCell(addFiltro("IP: ", filtro.getIp()));
			contFiltros++;
		}
		
		if(filtro.getPeriodo()!= null && filtro.getPeriodo().size()==2)
		{
			LocalDate data1 = filtro.getPeriodo().get(0);
			LocalDate data2 = filtro.getPeriodo().get(1);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			table.addCell(addFiltro("Período: ", data1.format(formatter)+" e "+data2.format(formatter)));
			contFiltros++;
		}
		
		if(filtro.getPreset()!= null)
		{
			table.addCell(addFiltro("Período Comum: ",filtro.getPreset().getNome()));
			contFiltros++;
		}
		
		if(filtro.getResumo()!= null && !filtro.getResumo().isBlank())
		{
			table.addCell(addFiltro("Resumo: ",filtro.getResumo()));
			contFiltros++;
		}
		
		if(filtro.getTipo()!= null)
		{
			table.addCell(addFiltro("Tipo: ",filtro.getTipo().getNome()));
			contFiltros++;
		}
		
		if(filtro.getUserAgent()!= null && !filtro.getUserAgent().isBlank())
		{
			table.addCell(addFiltro("Navegador: ",filtro.getUserAgent()));
			contFiltros++;
		}
		
		if(filtro.getUsuario()!= null)
		{
			table.addCell(addFiltro("Usuário: ",filtro.getUsuario().getNome()));
			contFiltros++;
		}
		
		int colRest = (3 - (contFiltros % 3)) % 3;
		for(int i = 0; i < colRest; i++)
			table.addCell(addFiltro("",""));
		
		document.add(table);
		
	}
	
	private static PdfPCell addFiltro(String title, String texto)
	{
		Phrase fraseTitle = new Phrase(title, style.headerFont);
		Phrase fraseTexto = new Phrase(texto, style.textoFont);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(style.bgHeader);
		cell.setPadding(7);
		cell.setPaddingBottom(0);
		cell.setPaddingTop(0);
		cell.setBorder(Rectangle.NO_BORDER);

		Paragraph p = new Paragraph(0);
		p.setMultipliedLeading(1.5f);
		p.add(fraseTitle);
		p.add(fraseTexto);
		p.setSpacingAfter(7);
		cell.addElement(p);

		return cell;
	}
	
}
