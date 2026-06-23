package matematica.basico.expressaonumerica;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public abstract class AgrupadorExercicio extends GeradorExercicio
{
	protected int computar(int a, int b, String op)
	{
		switch(op)
		{
			case "+": return a + b;
			case "-": return a - b;
			case "/": return a / b;
			default:  return a * b;
		}
	}

	protected String opTex(String op)
	{
		return op.equals("*") ? "\\times" : op;
	}

	protected String opDisplay(String op)
	{
		switch(op)
		{
			case "*": return "\\times";
			case "/": return "\\div";
			default:  return op;
		}
	}

	protected String opPM()
	{
		return rand.nextBoolean() ? "+" : "-";
	}

	protected String opPMM()
	{
		switch(rand.nextInt(3))
		{
			case 0:  return "+";
			case 1:  return "-";
			default: return "*";
		}
	}

	// ===== Geradores parametrizados de expressão (Fase 2) =====

	// Expressão plana A op B op C [op D], no máximo um × ou ÷ (hierarquia sem agrupadores).
	protected void planaSimples(int minT, int maxT, boolean permiteMulDiv, int maxVal)
	{
		int size = minT + rand.nextInt(maxT - minT + 1);
		int[] vals = new int[size];
		String[] ops = new String[size - 1];

		boolean temMulDiv = false;
		for(int i = 0; i < ops.length; i++)
		{
			if(permiteMulDiv && !temMulDiv && rand.nextInt(3) == 0)
			{
				ops[i] = rand.nextBoolean() ? "*" : "/";
				temMulDiv = true;
			}
			else
				ops[i] = opPM();
		}
		for(int i = 0; i < size; i++)
			vals[i] = 2 + rand.nextInt(maxVal - 1);
		for(int i = 0; i < ops.length; i++)
			if(ops[i].equals("/"))
				vals[i] = vals[i + 1] * (2 + rand.nextInt(4));

		StringBuilder enun = new StringBuilder("" + vals[0]);
		for(int i = 0; i < ops.length; i++)
			enun.append(" ").append(opDisplay(ops[i])).append(" ").append(vals[i + 1]);

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
					int r = computar(vList.get(j), vList.get(j + 1), o);
					vList.set(j, r);
					vList.remove(j + 1);
					oList.remove(j);
				}
				else
					j++;
			}
			StringBuilder sb = new StringBuilder("" + vList.get(0));
			for(int k = 0; k < oList.size(); k++)
				sb.append(" ").append(opDisplay(oList.get(k))).append(" ").append(vList.get(k + 1));
			passoMulDiv = sb.toString();
		}

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

	// Expressão com um par de parênteses: (a ± b) op c  — resolve o agrupador primeiro.
	protected void comParenteses(int maxVal)
	{
		int a = 2 + rand.nextInt(maxVal - 1);
		int b = 2 + rand.nextInt(maxVal - 1);
		String op1 = opPM();
		if(op1.equals("-") && a < b) { int t = a; a = b; b = t; }
		int inner = computar(a, b, op1);

		int c = 2 + rand.nextInt(9);
		String op2 = rand.nextBoolean() ? "*" : "+"; // evita resultado negativo
		int result = op2.equals("*") ? inner * c : inner + c;

		String enun = "(" + a + " " + op1 + " " + b + ") " + opDisplay(op2) + " " + c;
		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + enun + " = \\,?\\)");
		gerarAlternativas("" + result);
		addResolucao(
			"\\(\\begin{aligned}& " + enun + " = \\\\& " +
			inner + " " + opDisplay(op2) + " " + c + " = " + result + "\\end{aligned}\\)"
		);
	}

	// Expressão com potência: a^2 ± b  — resolve a potência primeiro.
	protected void comPotencia()
	{
		int a = 3 + rand.nextInt(6); // 3..8 → a^2 = 9..64
		int sq = a * a;
		int b = 2 + rand.nextInt(8);
		String op = opPM();
		if(op.equals("-") && sq < b) op = "+";
		int c = 2 + rand.nextInt(8);
		// a^2 op b op2 c, com op2 = +
		int parcial = computar(sq, b, op);
		int result = parcial + c;

		String enun = a + "^2 " + op + " " + b + " + " + c;
		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + enun + " = \\,?\\)");
		gerarAlternativas("" + result);
		addResolucao(
			"\\(\\begin{aligned}& " + enun + " = \\\\& " +
			sq + " " + op + " " + b + " + " + c + " = \\\\& " +
			parcial + " + " + c + " = " + result + "\\end{aligned}\\)"
		);
	}

	// Expressão com colchetes: [(a + b) × c] ± d  — resolve do interno para o externo.
	protected void comColchetes()
	{
		int a = 2 + rand.nextInt(8), b = 2 + rand.nextInt(8), c = 2 + rand.nextInt(5);
		int s1 = a + b;
		int s2 = s1 * c;
		int d = 2 + rand.nextInt(9);
		String op = (s2 >= d && rand.nextBoolean()) ? "-" : "+";
		int result = op.equals("-") ? s2 - d : s2 + d;

		String enun = "[(" + a + " + " + b + ") \\times " + c + "] " + op + " " + d;
		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + enun + " = \\,?\\)");
		gerarAlternativas("" + result);
		addResolucao(
			"\\(\\begin{aligned}& " + enun + " = \\\\& " +
			"[" + s1 + " \\times " + c + "] " + op + " " + d + " = \\\\& " +
			s2 + " " + op + " " + d + " = " + result + "\\end{aligned}\\)"
		);
	}
}
