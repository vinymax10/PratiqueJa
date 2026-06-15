package pdfAntigo.relatorio;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import pdfAntigo.base.PdfStyle;

public class TabelaDadosPDF
{
	static PdfStyle style = new PdfStyle();

	public static PdfPTable criarTabela(List<String[]> dados)
	{
		String[] colunas = dados.get(0);
		int numCol = colunas.length;
		PdfPTable table = new PdfPTable(numCol);
		table.setWidthPercentage(100);
//		table.setSpacingBefore(10f);
		float[] larguras = calcularLarguras(dados, 9);
		try
		{
			table.setWidths(larguras);
		}
		catch(DocumentException e)
		{
			throw new RuntimeException(e);
		}

		adicionarCabecalho(colunas, table);
		adicionarLinhas(table, dados);

		return table;
	}

	private static void adicionarCabecalho(String[] colunas, PdfPTable table)
	{
		for(String col : colunas)
		{
			PdfPCell cell = new PdfPCell(new Phrase(col, style.headerFont));
			cell.setBackgroundColor(style.bgHeader);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setPadding(7);
			table.addCell(cell);
		}
	}

	private static void adicionarLinhas(PdfPTable table, List<String[]> dados)
	{
		for(int i = 1; i < dados.size(); i++)
		{
			String[] linha = dados.get(i);
			BaseColor bg = (i % 2 == 0) ? style.bgLinhaImpar : style.bgLinhaPar;
			for(String texto : linha)
			{
				table.addCell(cell(new Phrase(String.valueOf(texto), style.textoFont), bg));
			}
		}
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
		p.setSpacingBefore(0);
		p.add(phrase);
		p.setSpacingAfter(7);
		cell.addElement(p);

		return cell;
	}

	private static float[] calcularLarguras(List<String[]> linhas, float fontSize)
	{
		BaseFont baseFont = null;
		try
		{
			baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		}
		catch(DocumentException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int colunas = linhas.get(0).length;
		float[] larguras = new float[colunas];

	    final float LARGURA_MINIMA = 100f; // aumentada
	    final float MARGEM_EXTRA = 12f;

		// Inicializa com largura mínima
		for(int i = 0; i < colunas; i++)
		{
			larguras[i] = LARGURA_MINIMA;
		}

		// Mede o maior texto real de cada coluna
		for(String[] linha : linhas)
		{
			for(int i = 0; i < colunas; i++)
			{
				String valor = linha[i];
				if(valor != null && !valor.isBlank())
				{
					float larguraTexto = baseFont.getWidthPoint(valor, fontSize) + MARGEM_EXTRA;
					larguras[i] = Math.max(larguras[i], larguraTexto);
				}
			}
		}

		return larguras;
	}

}
