package matematica.intermediario.sistemaequacoes;

import java.util.ArrayList;
import java.util.List;

import matematica.expressao.MyExpression;

public class ResolucaoComparacao
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

	public static String[] comparacaoY(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		passos.add("Isolando \\(x\\) na equação (1):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteY)+"y");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(x ="+parteIsolada+"\\)");

		passos.add("Isolando \\(x\\) na equação (2):");
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteY)+"y");
		String parteIsolada2=expressao.imprimir();
		passos.add("\\(x ="+parteIsolada2+"\\)");

		passos.add("Igualando as duas expressões:");

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			ResolucaoAdicao.resolvendoX(passos, sistema,2);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		return passos.toArray(new String[0]);
	}

	public static String[] comparacaoX(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE equacao=sistema.um;
		EquacaoSE outra=sistema.dois;

		passos.add("Isolando \\(y\\) na equação (1):");
		expressao = new MyExpression(equacao.valor+"+"+(-equacao.coeficienteX)+"x");
		String parteIsolada=expressao.imprimir();
		passos.add("\\(y ="+parteIsolada+"\\)");

		passos.add("Isolando \\(y\\) na equação (2):");
		expressao = new MyExpression(outra.valor+"+"+(-outra.coeficienteX)+"x");
		String parteIsolada2=expressao.imprimir();
		passos.add("\\(y ="+parteIsolada2+"\\)");

		passos.add("Igualando as duas expressões:");

		expressao = new MyExpression(parteIsolada+"="+parteIsolada2);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			ResolucaoAdicao.resolvendoY(passos, sistema,2);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		return passos.toArray(new String[0]);
	}
}
