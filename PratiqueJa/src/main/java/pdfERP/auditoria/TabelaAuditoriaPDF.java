package pdfERP.auditoria;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import modelo.auditoria.AuditoriaEvento;
import modelo.auditoria.EntidadeAuditavel;
import pdfERP.PdfStyle;

public class TabelaAuditoriaPDF
{
	static PdfStyle style = new PdfStyle();

	public static PdfPTable criarTabela(List<AuditoriaEvento> auditorias)
	{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setSpacingBefore(7f);

		for(int i = 0; i < auditorias.size(); i++)
		{
			AuditoriaEvento a = auditorias.get(i);
			BaseColor bg = (i % 2 == 0) ? style.bgLinhaImpar : style.bgLinhaPar;
			
			PdfPCell linha1 = new PdfPCell();
			linha1.setBorder(Rectangle.NO_BORDER);
			linha1.setPadding(0);
			linha1.addElement(criarTabela1(a, bg));
			table.addCell(linha1);

			PdfPCell linha2 = new PdfPCell();
			linha2.setBorder(Rectangle.NO_BORDER);
//			linha2.setBorderColor(style.bgHeader);
//			linha2.setBorderWidth(1);
			linha2.addElement(criarTabela2(a, bg));
			linha2.setPadding(0);
			table.addCell(linha2);
		}

		return table;
	}

	public static PdfPTable criarTabela1(AuditoriaEvento evento, BaseColor bg)
	{
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);

		try
		{
			table.setWidths(new float[] { 4f, 4f, 2f });
		}
		catch(DocumentException e)
		{
			throw new RuntimeException(e);
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		table.addCell(cell("Tipo: ", evento.getTipo().getNome(), bg));

		table.addCell(cell("Entidade: ", EntidadeAuditavel.getNomePorSimpleName(evento.getEntidade()), bg));
		
		table.addCell(cell("ID da Entidade: ", String.valueOf(evento.getEntidadeId()), bg));

		table.addCell(cell("Data: ", evento.getDataEvento() != null ? evento.getDataEvento().format(formatter) : "", bg));

		table.addCell(cell("Usuário: ", evento.getUsuario() != null ? evento.getUsuario().getNome() : "", bg));
		
		table.addCell(cell("IP: ", evento.getIp(), bg));

		table.addCell(cell("","", bg));

		return table;
	}

	public static PdfPTable criarTabela2(AuditoriaEvento evento, BaseColor bg)
	{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
//		table.setSpacingBefore(10f);

		table.addCell(cell("Navegador: ", evento.getUserAgent(), bg));

		table.addCell(resumo(evento, bg, style));

		return table;
	}


	private static PdfPCell cell(String title, String texto, BaseColor bg)
	{
		Phrase fraseTitle = new Phrase(title, style.destaqueFont);
		Phrase fraseTexto = new Phrase(texto, style.textoFont);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(bg);
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

	private static PdfPCell resumo(AuditoriaEvento a, BaseColor bg, PdfStyle style)
	{
		Phrase fraseTitle = new Phrase("Resumo: ", style.destaqueFont);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(bg);
		cell.setPadding(7);
//		cell.setPaddingBottom(0);
		cell.setPaddingTop(0);
		cell.setBorder(Rectangle.NO_BORDER);
		
		Paragraph p = new Paragraph(0);
		p.setMultipliedLeading(1.5f);
		p.add(fraseTitle);
//		p.setSpacingAfter(7);
		cell.addElement(p);
		
		List<String> itens = a.getItensResumo();

		for(String texto : itens)
		{
			p = new Paragraph(0);
			p.setMultipliedLeading(1.5f);
//			p.setSpacingAfter(7);
			p.add(new Phrase(nullSafe(texto), style.textoFont));
			cell.addElement(p);
		}

		return cell;
	}

	private static String nullSafe(String valor)
	{
		return valor != null ? valor : "";
	}
}
