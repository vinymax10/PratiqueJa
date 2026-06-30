package matematica.intermediario.sistemaequacoes;

import java.util.ArrayList;
import java.util.List;

import matematica.expressao.MyExpression;

public class ResolucaoAdicao
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

	public static String[] adicaoY(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteX==1)
			return adicaoX1(sistema);

		if(sistema.dois.coeficienteX==1)
			return adicaoX2(sistema);
		return new String[0];
	}

	public static String[] adicaoX2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE outra=sistema.um;
		EquacaoSE terceira=sistema.dois.multiplica((-outra.coeficienteX));

		if(outra.coeficienteX!=-1)
		{
			passos.add("Multiplicando a equação (2) por \\("+(-outra.coeficienteX)+"\\):");
			passos.add("\\("+terceira.latex()+" \\quad (3)\\)");
			passos.add("Somando as equações (1) e (3):");
		}
		else
		{
			passos.add("Somando as equações (1) e (2):");
		}

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		passos.add("\\("+expressao.imprimir()+"\\)");

		expressao = new MyExpression(outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoX(passos, sistema,2);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] adicaoX1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE outra=sistema.dois;
		EquacaoSE terceira=sistema.um.multiplica((-outra.coeficienteX));

		if(outra.coeficienteX!=-1)
		{
			passos.add("Multiplicando a equação (1) por \\("+(-outra.coeficienteX)+"\\):");
			passos.add("\\("+terceira.latex()+" \\quad (3)\\)");
			passos.add("Somando as equações (2) e (3):");
		}
		else
		{
			passos.add("Somando as equações (1) e (2):");
		}

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		passos.add("\\("+expressao.imprimir()+"\\)");

		expressao = new MyExpression(outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoX(passos, sistema,1);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] adicaoX(SistemaEquacoes sistema)
	{
		if(sistema.um.coeficienteY==1)
			return adicaoY1(sistema);

		if(sistema.dois.coeficienteY==1)
			return adicaoY2(sistema);
		return new String[0];
	}

	public static String[] adicaoY2(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE outra=sistema.um;
		EquacaoSE terceira=sistema.dois.multiplica((-outra.coeficienteY));

		if(outra.coeficienteY!=-1)
		{
			passos.add("Multiplicando a equação (2) por \\("+(-outra.coeficienteY)+"\\):");
			passos.add("\\("+terceira.latex()+" \\quad (3)\\)");
			passos.add("Somando as equações (1) e (3):");
		}
		else
		{
			passos.add("Somando as equações (1) e (2):");
		}

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		passos.add("\\("+expressao.imprimir()+"\\)");

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x="+outra.valor+" +"+terceira.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoY(passos, sistema,2);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	public static String[] adicaoY1(SistemaEquacoes sistema)
	{
		MyExpression expressao;
		List<String> passos = new ArrayList<>();
		EquacaoSE outra=sistema.dois;
		EquacaoSE terceira=sistema.um.multiplica((-outra.coeficienteY));

		if(outra.coeficienteY!=-1)
		{
			passos.add("Multiplicando a equação (1) por \\("+(-outra.coeficienteY)+"\\):");
			passos.add("\\("+terceira.latex()+" \\quad (3)\\)");
			passos.add("Somando as equações (2) e (3):");
		}
		else
		{
			passos.add("Somando as equações (1) e (2):");
		}

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x +"+outra.coeficienteY+"y +"+terceira.coeficienteY+"y="+outra.valor+" +"+terceira.valor);
		passos.add("\\("+expressao.imprimir()+"\\)");

		expressao = new MyExpression(outra.coeficienteX+"x +"+terceira.coeficienteX+"x="+outra.valor+" +"+terceira.valor);
		wrapLatex(passos, expressao.resolverLatex());

		if(sistema.tres!=null)
		{
			resolvendoY(passos, sistema,1);
			ResolucaoSubtituicao.resolvendoZ(passos, sistema);
		}

		boldUltimo(passos);
		return passos.toArray(new String[0]);
	}

	// Finds both x and y. Requires construirY1 (one equation with coefY == 1).
	public static String[] adicaoXY(SistemaEquacoes sistema)
	{
		List<String> passos = new ArrayList<>();
		for(String passo : adicaoX(sistema))
			passos.add(passo);

		passos.add("Encontrando \\(y\\):");
		EquacaoSE equacao = (sistema.um.coeficienteY == 1) ? sistema.um : sistema.dois;
		MyExpression expressao;
		if(equacao.coeficienteX == 1)
			expressao = new MyExpression(sistema.x + "+y=" + equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX + "*" + sistema.x + "+y=" + equacao.valor);
		wrapLatex(passos, expressao.resolverLatex());

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

		if(equacao.coeficienteY==1)
			expressao = new MyExpression(equacao.coeficienteX+"x +"+sistema.y+"="+equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX+"x +"+equacao.coeficienteY+"*"+sistema.y+"="+equacao.valor);

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

		if(equacao.coeficienteX==1)
			expressao = new MyExpression(sistema.x+"+"+equacao.coeficienteY+"y="+equacao.valor);
		else
			expressao = new MyExpression(equacao.coeficienteX+"*"+sistema.x+"+"+equacao.coeficienteY+"y="+equacao.valor);

		wrapLatex(passos, expressao.resolverLatex());
	}
}
