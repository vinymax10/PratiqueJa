package matematica.avancado.inequacoessegundograu.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

// Special cases: Δ = 0 (double root, answer x=r or ℝ) or Δ < 0 (no real roots, answer ∅ or ℝ)
public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean casoRaizDupla = rand.nextBoolean();

		if (casoRaizDupla)
			construirDelta0();
		else
			construirDeltaNegativo();
	}

	private void construirDelta0()
	{
		int r = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(3);
		int b = -2 * a * r;
		int c = a * r * r;

		// Ask for a(x-r)^2 ≤ 0  →  only x = r satisfies it (a > 0)
		// OR a(x-r)^2 ≥ 0  →  all x satisfy it (a > 0)
		boolean pedirMenorIgual = rand.nextBoolean();
		String sinal = pedirMenorIgual ? "\\leq" : "\\geq";

		String eq = Auxiliar.getNumber(a, "x^2", true)
				+ Auxiliar.getNumber(b, "x", false)
				+ Auxiliar.getNumber(c, "", false);
		String inequacao = eq + sinal + "0";

		String correta, d1, d2, d3;
		if (pedirMenorIgual)
		{
			correta = "\\(x=" + r + "\\)";
			d1 = "\\(x \\in \\mathbb{R}\\)";
			d2 = "\\(S=\\emptyset\\)";
			d3 = "\\(x \\in (-\\infty,\\," + r + "]\\)";
		}
		else
		{
			correta = "\\(x \\in \\mathbb{R}\\)";
			d1 = "\\(x=" + r + "\\)";
			d2 = "\\(S=\\emptyset\\)";
			d3 = "\\(x \\in [" + r + ",\\,+\\infty)\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		String res = "Calcular \\(\\Delta\\): \\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		String bStr = b < 0 ? "(" + b + ")" : "" + b;
		res += "\\(\\Delta=" + bStr + "^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=\\mathbf{0}\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=0\\): raiz dupla em \\(x_v=\\dfrac{-b}{2a}=" + r + "\\)" + "\\(\\\\\\)";
		res += "A parábola toca o eixo \\(x\\) apenas em \\(x=" + r + "\\): \\(f(" + r + ")=0\\) e \\(f(x)>0\\) para todo \\(x\\neq " + r + "\\)" + "\\(\\\\\\)";
		if (pedirMenorIgual)
		{
			res += "\\(f(x)\\leq 0\\) somente quando \\(f(x)=0\\), ou seja, \\(x=" + r + "\\)" + "\\(\\\\\\)";
			res += "\\(\\mathbf{x=" + r + "}\\)";
		}
		else
		{
			res += "\\(f(x)\\geq 0\\) para todo \\(x \\in \\mathbb{R}\\)" + "\\(\\\\\\)";
			res += "\\(\\mathbf{x \\in \\mathbb{R}}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + inequacao + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}

	private void construirDeltaNegativo()
	{
		int a = 1 + rand.nextInt(3);
		int c = 5 + rand.nextInt(6);
		int b = rand.nextInt(3) - 1;
		// Garantir Δ < 0: b^2 - 4ac < 0  →  b ∈ {-1,0,1}, 4ac ≥ 20
		int delta = b * b - 4 * a * c;

		boolean pedirNegativo = rand.nextBoolean();
		String sinal = pedirNegativo ? "<" : ">";

		String eq = Auxiliar.getNumber(a, "x^2", true)
				+ Auxiliar.getNumber(b, "x", false)
				+ Auxiliar.getNumber(c, "", false);
		String inequacao = eq + sinal + "0";

		String correta, d1, d2, d3;
		if (pedirNegativo)
		{
			// a>0, Δ<0, f<0  →  ∅
			correta = "\\(S=\\emptyset\\)";
			d1 = "\\(x \\in \\mathbb{R}\\)";
			d2 = "\\(x=0\\)";
			d3 = "\\(x \\in (0,\\,+\\infty)\\)";
		}
		else
		{
			// a>0, Δ<0, f>0  →  ℝ
			correta = "\\(x \\in \\mathbb{R}\\)";
			d1 = "\\(S=\\emptyset\\)";
			d2 = "\\(x=0\\)";
			d3 = "\\(x \\in (0,\\,+\\infty)\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		String bStr = b < 0 ? "(" + b + ")" : "" + b;
		String res = "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=" + bStr + "^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=\\mathbf{" + delta + "}<0\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta<0\\): a parábola não cruza o eixo \\(x\\). Como \\(a=" + a + ">0\\), a parábola está inteiramente acima do eixo. \\(\\\\\\)";
		if (pedirNegativo)
		{
			res += "\\(f(x)<0\\) não possui solução: \\(\\mathbf{S=\\emptyset}\\)";
		}
		else
		{
			res += "\\(f(x)>0\\) para todo \\(x\\): \\(\\mathbf{x \\in \\mathbb{R}}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + inequacao + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
