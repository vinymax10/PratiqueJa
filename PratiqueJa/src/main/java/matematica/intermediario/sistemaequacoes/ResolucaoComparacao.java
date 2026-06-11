package matematica.intermediario.sistemaequacoes;

import matematica.expressao.MyExpression;

public class ResolucaoComparacao
{
	private static String wrapLatex(String mathSteps)
	{
		StringBuilder result = new StringBuilder();
		int start = 0;
		for (int i = 0; i < mathSteps.length() - 1; i++)
		{
			if (mathSteps.charAt(i) == '\\' && mathSteps.charAt(i + 1) == '\\')
			{
				String part = mathSteps.substring(start, i).trim();
				if (!part.isEmpty())
					result.append("\\(").append(part).append("\\)").append("\\(\\\\\\)");
				i++;
				start = i + 1;
			}
		}
		String last = mathSteps.substring(start).trim();
		if (!last.isEmpty())
			result.append("\\(").append(last).append("\\)").append("\\(\\\\\\)");
		return result.toString();
	}

	public static String comparacaoY(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		resolucaoLatex+="Isolando \\(x\\) na equação (1):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(x ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Isolando \\(x\\) na equação (2):\\(\\\\\\)";
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteY)+"y");
		String parteIsolada2=expressao.imprimir();
		resolucaoLatex+="\\(x ="+parteIsolada2+"\\)\\(\\\\\\)";

		resolucaoLatex+="Igualando as duas expressões:\\(\\\\\\)";

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=ResolucaoAdicao.resolvendoX(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}

	public static String comparacaoX(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		resolucaoLatex+="Isolando \\(y\\) na equação (1):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(y ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Isolando \\(y\\) na equação (2):\\(\\\\\\)";
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteX)+"x");
		String parteIsolada2=expressao.imprimir();
		resolucaoLatex+="\\(y ="+parteIsolada2+"\\)\\(\\\\\\)";

		resolucaoLatex+="Igualando as duas expressões:\\(\\\\\\)";

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=ResolucaoAdicao.resolvendoY(sistema,2);
			resolucaoLatex+=ResolucaoSubtituicao.resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}
}
