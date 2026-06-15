package bean.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import util.StringAux;
import jakarta.el.MethodExpression;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Data;
import pdfAntigo.relatorio.TabelaPDF;

@Data
@Named
@SessionScoped
public class ExporterPdfBean implements Serializable
{
	
	public StreamedContent exportarPDF(String id, String nome)
	{
		List<String[]> tabela=extrairDadosDataTable(id);

		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			TabelaPDF.exportar(tabela, nome, out);
			String nomeFile=StringAux.normalizarNomeArquivo(nome);
			return DefaultStreamedContent.builder().name(nomeFile+".pdf").contentType("application/pdf")
			.stream(() -> new ByteArrayInputStream(out.toByteArray())).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<String[]> extrairDadosDataTable(String tableClientId)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		DataTable table = (DataTable) context.getViewRoot().findComponent(tableClientId);

		List<String[]> dadosExportados = new ArrayList<>();

		// 1. Capturar Cabeçalhos (Apenas colunas exportáveis e visíveis)
		List<UIColumn> colunas = table.getColumns().stream()
        .filter(UIColumn::isRendered)
        .filter(UIColumn::isExportable)
        .collect(Collectors.toList());

		String[] headers = colunas.stream()
        .map(column -> {
            // Verifica se existe valor específico para exportação no cabeçalho
            String exportHeader = (String) column.getExportHeaderValue();
            if (exportHeader != null && !exportHeader.isEmpty()) {
                return exportHeader;
            }
            // Se não houver, cai para o headerText padrão
            return column.getHeaderText();
        })
        .toArray(String[]::new);
		dadosExportados.add(headers);

		// 2. Iterar sobre os dados da tabela
		int rowCount = table.getRowCount();
//		int first = table.getFirst(); // Se quiser respeitar a paginação atual

		for(int i = 0; i < rowCount; i++)
		{
			table.setRowIndex(i);
			if(!table.isRowAvailable())
				continue;

			String[] linha = new String[colunas.size()];
			for(int j = 0; j < colunas.size(); j++)
			{
				UIColumn column = colunas.get(j);

				// Extrai o valor processado da célula (respeitando o exportValue se existir)
				Object value = column.getExportValue();
				if(value == null)
				{
					// Se não houver exportValue, tenta pegar o conteúdo dos filhos
					value = extrairValorColuna(context, column);
				}

				linha[j] = (value != null) ? value.toString() : "";
			}
			dadosExportados.add(linha);
		}

		table.setRowIndex(-1); // Resetar o índice da tabela após a iteração
		return dadosExportados;
	}

	private String extrairValorColuna(FacesContext context, UIColumn column) {
	    // 1. Tenta obter o exportValue (valor explícito)
	    Object value = column.getExportValue();
	    
	    // 2. Se for nulo, verifica se existe uma exportFunction definida
	    if (value == null) {
	        MethodExpression exportFunction = column.getExportFunction();
	        if (exportFunction != null) {
	            // Invoca o método definido no Bean passando o contexto do EL
	            value = exportFunction.invoke(context.getELContext(), null);
	        }
	    }

	    // 3. Se ainda for nulo, tenta extrair o texto dos componentes filhos
	    if (value == null) {
	        StringBuilder builder = new StringBuilder();
	        for (UIComponent kid : ((UIComponent) column).getChildren()) {
	            if (kid.isRendered()) {
	                String kidValue = org.primefaces.util.ComponentUtils.getValueToRender(context, kid);
	                if (kidValue != null) builder.append(kidValue);
	            }
	        }
	        value = builder.toString().trim();
	    }

	    return (value != null) ? value.toString() : "";
	}

}
