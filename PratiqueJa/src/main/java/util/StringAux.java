package util;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringAux
{
	
	public static String toConvertDFrac(String fracao)
	{
		if(fracao.contains("/"))
		{
			String list[]=fracao.split("/");
			if(list.length==2)
				return "\\dfrac{" + list[0] + "}{" + list[1] + "}";
		}
		return fracao;
	}
	
	public static String toLowerCaseFirst(String str)
	{
		char c[] = str.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		String newStr = new String(c);
		return newStr;
	}

	public static String getDataStr(LocalDate data)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(data);
	}
	
	public static String numeroStr(Long numero)
	{
		if(numero != null)
		{
			String texto = numero.toString();
			String ano = texto.substring(0, 4);
			String sequencial = texto.substring(4);
			return ano + "/" + sequencial;
		}
		else
			return "Sem número";
	}

	public static String extensao(String nome)
	{
		String termos[] = nome.split("\\.");
		if(termos.length > 0)
			return termos[termos.length - 1];
		else
			return "";
	}

	public static String normalizarNomeArquivo(String nome)
	{
		if(nome == null || nome.isBlank())
		{
			return nome;
		}

		String normalizado = Normalizer.normalize(nome, Normalizer.Form.NFD);
		normalizado = normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		normalizado = normalizado.replace("ç", "c").replace("Ç", "C");

		normalizado = normalizado.toLowerCase().trim().replaceAll("\\s+", "-") // espaços → hífen
		.replaceAll("[^a-z0-9\\-]", ""); // remove caracteres inválidos

		return normalizado;
	}
}
