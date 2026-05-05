package Matematica;

public class Correcao
{
	public static boolean isCorreta(String resultadoCorreto, String respostaAluno)
	{
		if(clean(respostaAluno).equals(clean(resultadoCorreto)))
			return true;

		if(isPorcentagem(resultadoCorreto))
			return isCorretaPorcentagem(resultadoCorreto, respostaAluno);

		if(isFracao(resultadoCorreto) && isFracao(respostaAluno))
			return isCorretaFracao(resultadoCorreto, respostaAluno);

		return false;
	}

	private static String clean(String texto)
	{
		return texto.trim().replace("°", "");
	}
	
	private static boolean isCorretaPorcentagem(String resultadoCorreto, String respostaAluno)
	{
		if(isFracao(respostaAluno) && respostaAluno.replaceAll("/", "").matches("-?\\d+"))
		{
			String resultadoCorretoStr = resultadoCorreto.replaceAll("\\\\%", "");
			long resultadoCorretoNumerador = Long.valueOf(resultadoCorretoStr);
			Racional resultadoCorretoRacional = new Racional(resultadoCorretoNumerador, 100);
			Racional respostaAlunoRacional = Racional.toConvert(respostaAluno);
			return respostaAlunoRacional.proporcional(resultadoCorretoRacional);
		}
		
		if(!isFracao(respostaAluno) &&respostaAluno.equals(resultadoCorreto.replace("\\", "")))
		{
			return true;
		}
		
		return false;
	}

	private static boolean isPorcentagem(String resultadoCorreto)
	{
		return resultadoCorreto.contains("%");
	}

	private static boolean isCorretaFracao(String resultadoCorreto, String respostaAluno)
	{
		if(respostaAluno.replaceAll("/", "").matches("-?\\d+"))
		{
			Racional resultadoCorretoRacional = Racional.toConvert(resultadoCorreto);
			Racional respostaAlunoRacional = Racional.toConvert(respostaAluno);
			return respostaAlunoRacional.proporcional(resultadoCorretoRacional);
		}
		return false;
	}

	private static boolean isFracao(String resultado)
	{
		return resultado.contains("/")&&(contarOcorrencias(resultado,"/")==1);
	}

	public static int contarOcorrencias(String texto, String sub)
	{
		int contador = 0;
		int indice = texto.indexOf(sub); // Encontra a primeira ocorrência

		while(indice != -1)
		{ // Enquanto a substring for encontrada
			contador++;
			indice = texto.indexOf(sub, indice + 1); // Procura a próxima ocorrência
		}

		return contador;
	}
	
	public static void main(String[] args)
	{
	}
}
