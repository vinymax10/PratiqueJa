package bean.exporter;

import java.io.Serializable;
import java.util.Collection;

import auxiliar.ClasseAux;
import auxiliar.StringAux;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class ExporterBean implements Serializable
{
	public String exportCollection(Collection<?> collection, String nomeAtributo, 
	TipoSeparador separador)
	{
		if(collection == null || collection.isEmpty() || separador == null)
		{
			return "";
		}

		String delimiter = separador.getCaracter();
		StringBuilder sb = new StringBuilder();

		for(Object obj : collection)
		{
			Object valorObj = ClasseAux.getValorAtributo(obj, nomeAtributo);
			if(valorObj != null)
			{
				sb.append(valorObj).append(delimiter);
			}
		}

		// Remove o último separador
		if(sb.length() > 0 && delimiter.length() > 0)
		{
			sb.setLength(sb.length() - delimiter.length());
		}

		return sb.toString();
	}

	public String exportBoolean(boolean value)
	{
		return value ? "Sim" : "Não";
	}

	public String formatNomeFile(String nome)
	{
		return StringAux.normalizarNomeArquivo(nome);
	}
}
