package matematica.intermediario.sistemaequacoes;

import java.util.ArrayList;
import java.util.List;

import matematica.expressao.MyExpression;

public class ResolucaoSubtituicao
{
	private static void wrapLatex(List<String> passos, String mathSteps)
	{
		int start = 0;
		for (int i = 0; i < mathSteps.length() - 1; i++)
		{
			if (mathSteps.charAt(i) == '\\' && mathSteps.charAt(i + 1) == '\\')
			{
				String part = mathSteps.substring(start, i).trim();
				if (!part.isEmpty())
					passos.add("\\(" + part + "\\)");
				i++;
				start = i + 1;
			}
		}
		String last = mathSteps.substring(start).trim();
		if (!last.isEmpty())
			passos.add("\\(" + last + "\\)");
	}

	private static void boldUltimo(List<String> passos)
	{
		if (passos.isEmpty()) return;
		String ultimo = passos.get(passos.size() - 1);
		if (ultimo.startsWith("\\(") && ultimo.endsWith("\\)"))
		{
			String conteudo = ultimo.substring(2, ultimo.length() - 2);
			int lastEq = conteudo.lastIndexOf('=');
			if (lastEq >= 0)
			{
				String antes = conteudo.substring(0, lastEq + 1);
				String valor = conteudo.substring(lastEq + 1).trim();
				passos.set(passos.size() - 1, "\\(" + antes + "\\mathbf{" + valor + "}\\)");
			}
		}
	}

	public static String[] substituicaoX1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		passos.add("Isolando \\(x\\) na equação (1):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(x ="+parteIsolada+"\\)");

		passos.add("Substituindo \\(x\\) na equação (2):");

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoX(passos, sistema,1);
			resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] substituicaoY1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		passos.add("Isolando \\(y\\) na equação (1):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(y ="+parteIsolada+"\\)");

		passos.add("Substituindo \\(y\\) na equação (2):");

		expressao = new MyExpression(outra.coeficienteX+"x +"+outra.coeficienteY+"*("+parteIsolada+")="+outra.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoY(passos, sistema,1);
			resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] substituicaoX2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;

		passos.add("Isolando \\(x\\) na equação (2):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(x ="+parteIsolada+"\\)");

		passos.add("Substituindo \\(x\\) na equação (1):");

		expressao = new MyExpression(outra.coeficienteX+"*("+parteIsolada+")+"+outra.coeficienteY+"y ="+outra.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoX(passos, sistema,2);
			resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] substituicaoY2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.dois;
		EquacaoSE outra=sistema.um;

		passos.add("Isolando \\(y\\) na equação (2):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(y ="+parteIsolada+"\\)");

		passos.add("Substituindo \\(y\\) na equação (1):");

		expressao = new MyExpression(outra.coeficienteX+"x+"+outra.coeficienteY+"*("+parteIsolada+") ="+outra.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoY(passos, sistema,2);
			resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static void resolvendoX(List<String> passos, SistemaEquacoes sistema, int numEq)
	{
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		passos.add("Substituindo \\(y\\) na equação ("+numEq+"):");

		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("x ="+equacao.valor+"+"+sistema.y);
		else
			expressao = new MyExpression("x ="+equacao.valor+"+"+(-equacao.coeficienteY)+"*"+sistema.y);

		wrapLatex(passos, expressao.resolverLatex());
	}

	public static void resolvendoY(List<String> passos, SistemaEquacoes sistema, int numEq)
	{
		MyExpression expressao;
		EquacaoSE equacao;
		if(numEq==1)
			equacao=sistema.um;
		else
			equacao=sistema.dois;

		passos.add("Substituindo \\(x\\) na equação ("+numEq+"):");

		if((-equacao.coeficienteY)==1)
			expressao = new MyExpression("y ="+equacao.valor+"+"+sistema.x);
		else
			expressao = new MyExpression("y ="+equacao.valor+"+"+(-equacao.coeficienteX)+"*"+sistema.x);

		wrapLatex(passos, expressao.resolverLatex());
	}

	public static void resolvendoZ(List<String> passos, SistemaEquacoes sistema)
	{
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
		wrapLatex(passos, expressao.resolverLatex());
	}

	public static String[] substituicaoY(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteX==1)
			return substituicaoX1(sistema);

		if(sistema.dois.coeficienteX==1)
			return substituicaoX2(sistema);
		return new String[0];
	}

	public static String[] substituicaoX(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteY==1)
			return substituicaoY1(sistema);

		if(sistema.dois.coeficienteY==1)
			return substituicaoY2(sistema);
		return new String[0];
	}

	// Finds both x and y. Requires construirY1 (one equation with coefY == 1).
	public static String[] substituicaoXY(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao, outra;
		String parteIsolada;

		if(sistema.um.coeficienteY == 1)
		{
			equacao = sistema.um;
			outra = sistema.dois;
			passos.add("Isolando \\(y\\) na equação (1):");
			expressao = new MyExpression(equacao.valor + "+" + (-equacao.coeficienteX) + "x");
			parteIsolada = expressao.imprimir();
			passos.add("\\(y =" + parteIsolada + "\\)");
			passos.add("Substituindo \\(y\\) na equação (2):");
			expressao = new MyExpression(outra.coeficienteX + "x +" + outra.coeficienteY + "*(" + parteIsolada + ")=" + outra.valor);
		}
		else
		{
			equacao = sistema.dois;
			outra = sistema.um;
			passos.add("Isolando \\(y\\) na equação (2):");
			expressao = new MyExpression(equacao.valor + "+" + (-equacao.coeficienteX) + "x");
			parteIsolada = expressao.imprimir();
			passos.add("\\(y =" + parteIsolada + "\\)");
			passos.add("Substituindo \\(y\\) na equação (1):");
			expressao = new MyExpression(outra.coeficienteX + "x +" + outra.coeficienteY + "*(" + parteIsolada + ")=" + outra.valor);
		}
		wrapLatex(passos, expressao.resolverLatex());

		passos.add("Encontrando \\(y\\):");
		if(equacao.coeficienteX == 1)
			expressao = new MyExpression(sistema.x + "+y=" + equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX + "*" + sistema.x + "+y=" + equacao.valor);
		wrapLatex(passos, expressao.resolverLatex());

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}
}
