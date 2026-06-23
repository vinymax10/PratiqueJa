package matematica.basico.expressaonumerica.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.basico.expressaonumerica.AgrupadorExercicio;
import util.Algebra;

// Expressão plana: A op B op C [op D] — hierarquia sem agrupadores
public class Expressao1 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		int size = 3 + rand.nextInt(2); // 3 ou 4 termos
		int[] vals = new int[size];
		String[] ops = new String[size - 1];

		// No máximo um * ou /
		boolean temMulDiv = false;
		for(int i = 0; i < ops.length; i++)
		{
			if(!temMulDiv && rand.nextInt(3) == 0)
			{
				ops[i] = rand.nextBoolean() ? "*" : "/";
				temMulDiv = true;
			}
			else
				ops[i] = opPM();
		}

		// Valores 2–9
		for(int i = 0; i < size; i++)
			vals[i] = 2 + rand.nextInt(8);

		// Garante divisibilidade exata
		for(int i = 0; i < ops.length; i++)
			if(ops[i].equals("/"))
				vals[i] = vals[i + 1] * (2 + rand.nextInt(4));

		// Monta exibição LaTeX do enunciado
		StringBuilder enun = new StringBuilder("" + vals[0]);
		for(int i = 0; i < ops.length; i++)
			enun.append(" ").append(Algebra.converter(ops[i])).append(" ").append(vals[i + 1]);

		// Calcula passo intermediário resolvendo * e / (esquerda para direita)
		List<Integer> vList = new ArrayList<>();
		for(int v : vals) vList.add(v);
		List<String> oList = new ArrayList<>();
		for(String o : ops) oList.add(o);

		String passoMulDiv = null;
		if(temMulDiv)
		{
			int j = 0;
			while(j < oList.size())
			{
				String o = oList.get(j);
				if(o.equals("*") || o.equals("/"))
				{
					int r = o.equals("*")
						? vList.get(j) * vList.get(j + 1)
						: vList.get(j) / vList.get(j + 1);
					vList.set(j, r);
					vList.remove(j + 1);
					oList.remove(j);
				}
				else
					j++;
			}

			StringBuilder sb = new StringBuilder("" + vList.get(0));
			for(int k = 0; k < oList.size(); k++)
				sb.append(" ").append(oList.get(k)).append(" ").append(vList.get(k + 1));
			passoMulDiv = sb.toString();
		}

		// Resultado final: resolve + e - da esquerda para direita
		int result = vList.get(0);
		for(int i = 0; i < oList.size(); i++)
			result = oList.get(i).equals("+") ? result + vList.get(i + 1) : result - vList.get(i + 1);

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + enun + " = \\,?\\)");
		gerarAlternativas("" + result);

		StringBuilder res = new StringBuilder("\\(\\begin{aligned}& ").append(enun);
		if(passoMulDiv != null)
			res.append(" = \\\\& ").append(passoMulDiv);
		res.append(" = ").append(result).append("\\end{aligned}\\)");
		addResolucao(res.toString());
	}
}
