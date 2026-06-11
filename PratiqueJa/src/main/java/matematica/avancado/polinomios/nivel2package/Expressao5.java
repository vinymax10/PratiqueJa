package matematica.avancado.polinomios.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Remainder theorem: find the remainder of p(x) ÷ (x − r) using p(r)
public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(2);
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int c = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int d = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int r = 1 + rand.nextInt(3);

		int result = a * r * r * r + b * r * r + c * r + d;

		String poly = Polinomio.formatar(a, b, c, d);

		String step1 = showEval(a, b, c, d, r);
		String step2 = showPartial(a, b, c, d, r);

		String res = "Pelo Teorema do Resto, o resto da divisão por \\((x - " + r
				+ ")\\) é \\(p(" + r + ")\\):\\(\\\\\\)";
		res += "\\(p(" + r + ") = " + step1 + " = \\\\ \\)";
		res += "\\(" + step2 + " = \\mathbf{" + result + "}\\)";

		addParagrafo("Qual o resto da divisão de \\(p(x)\\) por \\((x - " + r + ")\\)?");
		addParagrafo("\\(p(x) = " + poly + "\\)");
		gerarAlternativas("" + result);
		setResolucao(res);
	}

	private String showEval(int a, int b, int c, int d, int r)
	{
		StringBuilder sb = new StringBuilder();
		appendTerm(sb, a, r, 3, true);
		appendTerm(sb, b, r, 2, false);
		appendTerm(sb, c, r, 1, false);
		if (d != 0)
		{
			String sign = (d > 0) ? " + " : " - ";
			sb.append(sb.length() == 0 ? "" : sign).append(Math.abs(d));
		}
		return sb.toString();
	}

	private void appendTerm(StringBuilder sb, int coef, int r, int exp, boolean first)
	{
		if (coef == 0) return;
		int abs = Math.abs(coef);
		boolean neg = coef < 0;
		String sign = first ? (neg ? "-" : "") : (neg ? " - " : " + ");
		sb.append(sign);
		if (exp == 1)
		{
			if (abs != 1) sb.append(abs).append(" \\cdot ");
			sb.append(r);
		}
		else
		{
			if (abs != 1) sb.append(abs).append(" \\cdot ");
			sb.append(r).append("^").append(exp);
		}
	}

	private String showPartial(int a, int b, int c, int d, int r)
	{
		int t1 = a * r * r * r;
		int t2 = b * r * r;
		int t3 = c * r;
		StringBuilder sb = new StringBuilder();
		if (t1 != 0) sb.append(t1);
		appendConst(sb, t2);
		appendConst(sb, t3);
		appendConst(sb, d);
		return sb.length() == 0 ? "0" : sb.toString();
	}

	private void appendConst(StringBuilder sb, int val)
	{
		if (val == 0) return;
		if (sb.length() == 0) { sb.append(val); return; }
		if (val > 0) sb.append(" + ").append(val);
		else sb.append(" - ").append(Math.abs(val));
	}
}
