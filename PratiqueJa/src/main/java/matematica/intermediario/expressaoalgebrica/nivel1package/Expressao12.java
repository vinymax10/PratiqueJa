package matematica.intermediario.expressaoalgebrica.nivel1package;

import matematica.GeradorExercicio;

public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(4);   // 1..4
		int b = -5 + rand.nextInt(11); // -5..5
		int c = -9 + rand.nextInt(19); // -9..9
		while(b == 0 && c == 0)
		{
			b = -5 + rand.nextInt(11);
			c = -9 + rand.nextInt(19);
		}
		int v = 2 + rand.nextInt(5); // 2..6

		int v2      = v * v;
		int av2     = a * v2;
		int bv      = b * v;
		int resultado = av2 + bv + c;

		// Enunciado: ax² ± bx ± c
		String expr = (a == 1 ? "" : a + "") + "x^2" + termoLinear(b) + termoCte(c);

		addParagrafo("Calcule o valor da expressão para \\(x = " + v + "\\):");
		addParagrafo("\\(" + expr + "\\)");
		gerarAlternativasInteiras(resultado);

		// Resolução
		StringBuilder s1 = new StringBuilder(a + " \\cdot " + v + "^{2}");
		if(b > 0)  s1.append(" + ").append(b).append(" \\cdot ").append(v);
		if(b < 0)  s1.append(" - ").append(Math.abs(b)).append(" \\cdot ").append(v);
		if(c > 0)  s1.append(" + ").append(c);
		if(c < 0)  s1.append(" - ").append(Math.abs(c));

		StringBuilder s2 = new StringBuilder("" + av2);
		if(bv > 0) s2.append(" + ").append(bv);
		if(bv < 0) s2.append(" - ").append(Math.abs(bv));
		if(c > 0)  s2.append(" + ").append(c);
		if(c < 0)  s2.append(" - ").append(Math.abs(c));

		StringBuilder s1b = new StringBuilder(a + " \\cdot " + v2);
		if(b > 0)  s1b.append(" + ").append(b).append(" \\cdot ").append(v);
		if(b < 0)  s1b.append(" - ").append(Math.abs(b)).append(" \\cdot ").append(v);
		if(c > 0)  s1b.append(" + ").append(c);
		if(c < 0)  s1b.append(" - ").append(Math.abs(c));

		String res = "Substituindo \\(x = " + v + "\\): \\(\\\\\\)";
		res += "\\(" + s1 + " = \\\\ ";
		res += "" + s1b + " = \\\\ ";
		res += "" + s2 + " = " + resultado + "\\)";
		setResolucao(res);
	}

	private String termoLinear(int coef)
	{
		if(coef == 0)  return "";
		if(coef == 1)  return " + x";
		if(coef == -1) return " - x";
		if(coef > 0)   return " + " + coef + "x";
		return " - " + Math.abs(coef) + "x";
	}

	private String termoCte(int cte)
	{
		if(cte == 0) return "";
		if(cte > 0)  return " + " + cte;
		return " - " + Math.abs(cte);
	}
}
