package matematica.intermediario.sistemaequacoes;

import matematica.expressao.MyExpression;

public class ResolucaoSubtituicao
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

	public static String substituicaoX1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		resolucaoLatex+="Isolando \\(x\\) na equação (1):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(x ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Substituindo \\(x\\) na equação (2):\\(\\\\\\)";

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=resolvendoX(sistema,1);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}

	public static String substituicaoY1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		resolucaoLatex+="Isolando \\(y\\) na equação (1):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(y ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Substituindo \\(y\\) na equação (2):\\(\\\\\\)";

		expressao = new MyExpression(outra.coeficienteX+"x +"+outra.coeficienteY+"*("+parteIsolada+")="+outra.valor);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=resolvendoY(sistema,1);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}

	public static String substituicaoX2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;

		resolucaoLatex+="Isolando \\(x\\) na equação (2):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(x ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Substituindo \\(x\\) na equação (1):\\(\\\\\\)";

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=resolvendoX(sistema,2);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}

	public static String substituicaoY2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex="";
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;

		resolucaoLatex+="Isolando \\(y\\) na equação (2):\\(\\\\\\)";
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		resolucaoLatex+="\\(y ="+parteIsolada+"\\)\\(\\\\\\)";

		resolucaoLatex+="Substituindo \\(y\\) na equação (1):\\(\\\\\\)";

		expressao = new MyExpression(outra.coeficienteX+"x+"+outra.coeficienteY+"*("+parteIsolada+") ="+outra.valor);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolucaoLatex+=resolvendoY(sistema,2);
			resolucaoLatex+=resolvendoZ(sistema);
		}

		return resolucaoLatex;
	}

	public static String resolvendoX(SistemaEquacoes sistema, int numEq)
	{
		String resolucaoLatex="";
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		resolucaoLatex+="Substituindo \\(y\\) na equação ("+numEq+"):\\(\\\\\\)";

		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("x ="+equacao.valor+"+"+sistema.y);
		else
			expressao = new MyExpression("x ="+equacao.valor+"+"+(-equacao.coeficienteY)+"*"+sistema.y);

		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		return resolucaoLatex;
	}

	public static String resolvendoY(SistemaEquacoes sistema, int numEq)
	{
		String resolucaoLatex="";
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		resolucaoLatex+="Substituindo \\(x\\) na equação ("+numEq+"):\\(\\\\\\)";

		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("y ="+equacao.valor+"+"+sistema.x);
		else
			expressao = new MyExpression("y ="+equacao.valor+"+"+(-equacao.coeficienteX)+"*"+sistema.x);

		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		return resolucaoLatex;
	}

	public static String resolvendoZ(SistemaEquacoes sistema)
	{
		String resolucaoLatex="";
		EquacaoSE tres=sistema.tres;
		MyExpression expressao;
		String strExpressao="z=";
		if(tres.coeficienteX!=1)
			strExpressao+=tres.coeficienteX+"*";

		strExpressao+=sistema.x+"+";
		if(tres.coeficienteY!=1)
			strExpressao+=tres.coeficienteY+"*";

		strExpressao+=sistema.y;

		expressao = new MyExpression(strExpressao);
		resolucaoLatex+=wrapLatex(expressao.resolverLatex());

		return resolucaoLatex;
	}

	public static String substituicaoY(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteX==1)
			return substituicaoX1(sistema);

		if(sistema.dois.coeficienteX==1)
			return substituicaoX2(sistema);
		return "";
	}

	public static String substituicaoX(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteY==1)
			return substituicaoY1(sistema);

		if(sistema.dois.coeficienteY==1)
			return substituicaoY2(sistema);
		return "";
	}

	// Finds both x and y. Requires construirY1 (one equation with coefY == 1).
	public static String substituicaoXY(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		String resolucaoLatex;
		EquacaoSE equacao, outra;
		String parteIsolada;

		if(sistema.um.coeficienteY == 1)
		{
			equacao = sistema.um;
			outra = sistema.dois;
			resolucaoLatex = "Isolando \\(y\\) na equação (1):\\(\\\\\\)";
			expressao = new MyExpression(equacao.valor + "+" + (-equacao.coeficienteX) + "x");
			parteIsolada = expressao.imprimir();
			resolucaoLatex += "\\(y =" + parteIsolada + "\\)\\(\\\\\\)";
			resolucaoLatex += "Substituindo \\(y\\) na equação (2):\\(\\\\\\)";
			expressao = new MyExpression(outra.coeficienteX + "x +" + outra.coeficienteY + "*(" + parteIsolada + ")=" + outra.valor);
		}
		else
		{
			equacao = sistema.dois;
			outra = sistema.um;
			resolucaoLatex = "Isolando \\(y\\) na equação (2):\\(\\\\\\)";
			expressao = new MyExpression(equacao.valor + "+" + (-equacao.coeficienteX) + "x");
			parteIsolada = expressao.imprimir();
			resolucaoLatex += "\\(y =" + parteIsolada + "\\)\\(\\\\\\)";
			resolucaoLatex += "Substituindo \\(y\\) na equação (1):\\(\\\\\\)";
			expressao = new MyExpression(outra.coeficienteX + "x +" + outra.coeficienteY + "*(" + parteIsolada + ")=" + outra.valor);
		}
		resolucaoLatex += wrapLatex(expressao.resolverLatex());

		resolucaoLatex += "Encontrando \\(y\\):\\(\\\\\\)";
		if(equacao.coeficienteX == 1)
			expressao = new MyExpression(sistema.x + "+y=" + equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX + "*" + sistema.x + "+y=" + equacao.valor);
		resolucaoLatex += wrapLatex(expressao.resolverLatex());

		return resolucaoLatex;
	}
}
