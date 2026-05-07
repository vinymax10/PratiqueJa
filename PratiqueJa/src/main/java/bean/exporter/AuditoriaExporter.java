package bean.exporter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelo.auditoria.AuditoriaEvento;
import modelo.auditoria.EntidadeAuditavel;

public class AuditoriaExporter
{

	public static void exportarExcel(List<AuditoriaEvento> auditorias, OutputStream out) throws Exception
	{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Auditoria");

		// Cabeçalho
		Row header = sheet.createRow(0);
		String[] colunas = { "ID", "Entidade", "Entidade ID", "Tipo", "Usuário"
		, "Empresa", "Loja", "Data do Evento", "IP", "Navegador", "Resumo" };

		for(int i = 0; i < colunas.length; i++)
		{
			Cell cell = header.createCell(i);
			cell.setCellValue(colunas[i]);
			sheet.autoSizeColumn(i);
		}

		// Formato de data
		CellStyle dataStyle = workbook.createCellStyle();
		CreationHelper creationHelper = workbook.getCreationHelper();
		dataStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm"));

		// Conteúdo
		int rowNum = 1;
		for(AuditoriaEvento a : auditorias)
		{
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(a.getId());
			row.createCell(1).setCellValue(EntidadeAuditavel.getNomePorSimpleName(a.getEntidade()));
			row.createCell(2).setCellValue(a.getEntidadeId());
			row.createCell(3).setCellValue(a.getTipo().getNome());
			row.createCell(4).setCellValue(a.getUsuario() != null ? a.getUsuario().getNome() : "");

			Cell dataCell = row.createCell(7);
			dataCell.setCellValue(a.getDataEvento());
			dataCell.setCellStyle(dataStyle);

			row.createCell(8).setCellValue(a.getIp());
			row.createCell(9).setCellValue(a.getUserAgent());

			row.createCell(10).setCellValue(a.getResumo());
		}

		// Ajustar colunas
		for(int i = 0; i < colunas.length; i++)
		{
			sheet.autoSizeColumn(i);
		}

		// Salvar
		workbook.write(out);
		workbook.close();
	}

	public static void exportarCSV(List<AuditoriaEvento> auditorias, OutputStream out) throws Exception
	{
		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8)))
		{
			// Cabeçalho
			writer.println("ID;Entidade;Entidade ID;Tipo;Usuário;Empresa;Loja;Data do Evento;IP;Navegador;Resumo");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

			// Conteúdo
			for(AuditoriaEvento a : auditorias)
			{
				writer.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n", a.getId(), 
				escape(EntidadeAuditavel.getNomePorSimpleName(a.getEntidade())),
				a.getEntidadeId(), a.getTipo().getNome(),
				escape(a.getUsuario() != null ? a.getUsuario().getNome() : ""), 
				a.getDataEvento() != null ? a.getDataEvento().format(formatter) : "",
				escape(a.getIp()),escape(a.getUserAgent()),escape(a.getResumo()));
			}
		}
	}

	private static String escape(String valor)
	{
		if(valor == null)
			return "";
		String v = valor.replace("\"", "\"\"");
		if(v.contains(";") || v.contains("\n") || v.contains("\""))
		{
			v = "\"" + v + "\"";
		}
		return v;
	}

}
