package pdfERP.auditoria;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import modelo.auditoria.AuditoriaEvento;
import pdfERP.PdfStyle;

public class TabelaAuditoriaAntigoPDF
{
	public static PdfPTable criarTabela(List<AuditoriaEvento> auditorias, PdfStyle style)
	{
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		try
		{
			table.setWidths(new float[] { 0.5f, 0.7f, 1.3f, 1.2f,1f, 1.8f, 3.5f });
		}
		catch(DocumentException e)
		{
			throw new RuntimeException(e);
		}

		adicionarCabecalho(table, style);
		adicionarLinhas(table, auditorias, style);

		return table;
	}

	private static void adicionarCabecalho(PdfPTable table, PdfStyle style)
	{
		String[] colunas = { "ID", "Tipo", "Usuário", "Data do Evento", "IP", "Navegador", "Resumo" };
		for(String col : colunas)
		{
			PdfPCell cell = new PdfPCell(new Phrase(col, style.destaqueFont));
			cell.setBackgroundColor(style.corAzul);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setPadding(7);
			table.addCell(cell);
		}
	}

	private static void adicionarLinhas(PdfPTable table, List<AuditoriaEvento> auditorias, PdfStyle style)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		for(int i = 0; i < auditorias.size(); i++)
		{
			AuditoriaEvento a = auditorias.get(i);
			BaseColor bg = (i % 2 == 0) ? style.bgLinhaImpar : style.bgLinhaPar;

			table.addCell(cell(new Phrase(String.valueOf(a.getId()), style.textoFont),bg));
	        table.addCell(cell(new Phrase(a.getTipo().getNome(), style.textoFont),bg));
	        table.addCell(cell(new Phrase(a.getUsuario() != null ? a.getUsuario().getNome() : "", style.textoFont),bg));
	        table.addCell(cell(new Phrase(a.getDataEvento() != null ? a.getDataEvento().format(formatter) : "", style.textoFont),bg));
	        table.addCell(cell(new Phrase(a.getIp(), style.textoFont),bg));
	        table.addCell(cell(new Phrase(a.getUserAgent(), style.textoFont),bg));
	        table.addCell(resumo(a, bg, style.textoFont));
		}
	}

	private static String nullSafe(String valor) {
    return valor != null ? valor : "";
}
	
	private static PdfPCell cell(Phrase phrase, BaseColor bg)
	{
        PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(bg);
		cell.setPadding(7);
		cell.setPaddingBottom(0);
		cell.setPaddingTop(0);
        cell.setBorder(Rectangle.NO_BORDER);
        
        Paragraph p = new Paragraph(0);
        p.setMultipliedLeading(1.5f);
        p.add(phrase);
        p.setSpacingAfter(7);
	    cell.addElement(p);
        
        return cell;
	}
	
	private static PdfPCell resumo(AuditoriaEvento a, BaseColor bg, Font font)
	{
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(bg);
		cell.setPadding(7);
//		cell.setPaddingBottom(0);
		cell.setPaddingTop(0);
        cell.setBorder(Rectangle.NO_BORDER);
		
		List<String> itens=a.getItensResumo();
		
		Paragraph p;
		for (String texto : itens) 
		{
			p = new Paragraph(0);
			p.setMultipliedLeading(1.5f);
//			p.setSpacingAfter(7);
		    p.add(new Phrase(nullSafe(texto), font));
		    cell.addElement(p);
		}

        return cell;
	}
}
