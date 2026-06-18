package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);
		int a, b, c, delta, resultado;

		if(tipo == 0)
		{
			// Δ > 0: duas raízes distintas
			int r1 = 1 + rand.nextInt(5);
			int r2 = r1 + 1 + rand.nextInt(4);
			if(rand.nextBoolean()) r1 *= -1;
			a = 1 + rand.nextInt(5);
			b = -a * (r1 + r2);
			c = a * r1 * r2;
			resultado = 2;
		}
		else if(tipo == 1)
		{
			// Δ = 0: raiz dupla a(x-r)²=0
			int r = 1 + rand.nextInt(9);
			a = 1 + rand.nextInt(5);
			b = -2 * a * r;
			c = a * r * r;
			resultado = 1;
		}
		else
		{
			// Δ < 0: sem raízes reais (b² ≤ 1 < 4ac ≥ 20)
			a = 1 + rand.nextInt(3);
			c = 5 + rand.nextInt(5);
			b = rand.nextInt(3) - 1;
			resultado = 0;
		}

		delta = b * b - 4 * a * c;

		String eq = Auxiliar.getNumber(a, "x^2", true);
		eq += Auxiliar.getNumber(b, "x", false);
		eq += Auxiliar.getNumber(c, "", false);
		eq += "=0";

		String res = "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		if(b == 0)
			res += "\\(\\Delta=0^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ \\)";
		else
			res += "\\(\\Delta=" + b + "^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=\\mathbf{" + delta + "}\\)" + "\\(\\\\\\)";

		if(delta > 0)
			res += "Como \\(\\Delta=" + delta + " > 0\\), a equação possui \\(\\mathbf{2}\\) raízes reais distintas.";
		else if(delta == 0)
			res += "Como \\(\\Delta=0\\), a equação possui \\(\\mathbf{1}\\) raiz real repetida.";
		else
			res += "Como \\(\\Delta=" + delta + " < 0\\), a equação não possui raízes reais: \\(\\mathbf{0}\\) raízes.";

		addParagrafo("Quantas raízes reais a equação possui?");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas("" + resultado);
		setResolucao(res);
	}
}
