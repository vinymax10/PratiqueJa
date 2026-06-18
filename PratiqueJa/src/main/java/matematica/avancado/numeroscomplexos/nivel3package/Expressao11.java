package matematica.avancado.numeroscomplexos.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);
		int k    = 1 + rand.nextInt(3); // 1, 2 ou 3

		String zStr, rStr, thetaStr, rExpl, angleExpl;
		List<String> dist = new ArrayList<>();

		switch (tipo)
		{
			case 0: // z = k + ki, r = k√2, θ = π/4
				zStr     = k == 1 ? "1 + i" : k + " + " + k + "i";
				rStr     = k == 1 ? "\\sqrt{2}" : k + "\\sqrt{2}";
				thetaStr = "\\dfrac{\\pi}{4}";
				rExpl    = k == 1
					? "r = \\sqrt{1 + 1} = \\sqrt{2}"
					: "r = \\sqrt{" + (k*k) + " + " + (k*k) + "} = \\sqrt{" + (2*k*k) + "} = " + rStr;
				angleExpl = "\\(a > 0, b > 0\\) (1.° quadrante); "
					+ "\\(\\tan\\theta = \\dfrac{b}{a} = 1 \\Rightarrow \\theta = \\dfrac{\\pi}{4}\\)";
				dist.add("\\(" + rStr + "\\left(\\cos\\dfrac{\\pi}{3}+i\\sin\\dfrac{\\pi}{3}\\right)\\)");
				dist.add("\\(" + rStr + "\\left(\\cos\\dfrac{\\pi}{2}+i\\sin\\dfrac{\\pi}{2}\\right)\\)");
				dist.add("\\(" + k + "\\left(\\cos\\dfrac{\\pi}{4}+i\\sin\\dfrac{\\pi}{4}\\right)\\)");
				break;

			case 1: // z = -k + ki, r = k√2, θ = 3π/4
				zStr     = k == 1 ? "-1 + i" : "-" + k + " + " + k + "i";
				rStr     = k == 1 ? "\\sqrt{2}" : k + "\\sqrt{2}";
				thetaStr = "\\dfrac{3\\pi}{4}";
				rExpl    = k == 1
					? "r = \\sqrt{1 + 1} = \\sqrt{2}"
					: "r = \\sqrt{" + (k*k) + " + " + (k*k) + "} = \\sqrt{" + (2*k*k) + "} = " + rStr;
				angleExpl = "\\(a < 0, b > 0\\) (2.° quadrante); "
					+ "\\(\\tan\\theta = \\dfrac{b}{a} = -1 \\Rightarrow \\theta = \\dfrac{3\\pi}{4}\\)";
				dist.add("\\(" + rStr + "\\left(\\cos\\dfrac{\\pi}{4}+i\\sin\\dfrac{\\pi}{4}\\right)\\)");
				dist.add("\\(" + rStr + "\\left(\\cos\\dfrac{\\pi}{2}+i\\sin\\dfrac{\\pi}{2}\\right)\\)");
				dist.add("\\(" + k + "\\left(\\cos\\dfrac{3\\pi}{4}+i\\sin\\dfrac{3\\pi}{4}\\right)\\)");
				break;

			case 2: // z = ki, r = k, θ = π/2
				zStr     = k == 1 ? "i" : k + "i";
				rStr     = "" + k;
				thetaStr = "\\dfrac{\\pi}{2}";
				rExpl    = "r = \\sqrt{0 + " + (k*k) + "} = \\sqrt{" + (k*k) + "} = " + k;
				angleExpl = "\\(a = 0, b > 0\\) (eixo imaginário positivo) "
					+ "\\(\\Rightarrow \\theta = \\dfrac{\\pi}{2}\\)";
				String wrongSqrt = k == 1 ? "\\sqrt{2}" : k + "\\sqrt{2}";
				dist.add("\\(" + k + "\\left(\\cos\\dfrac{\\pi}{4}+i\\sin\\dfrac{\\pi}{4}\\right)\\)");
				dist.add("\\(" + k + "\\left(\\cos\\pi+i\\sin\\pi\\right)\\)");
				dist.add("\\(" + wrongSqrt + "\\left(\\cos\\dfrac{\\pi}{2}+i\\sin\\dfrac{\\pi}{2}\\right)\\)");
				break;

			default: // z = -k, r = k, θ = π
				zStr     = "-" + k;
				rStr     = "" + k;
				thetaStr = "\\pi";
				rExpl    = "r = \\sqrt{" + (k*k) + " + 0} = \\sqrt{" + (k*k) + "} = " + k;
				angleExpl = "\\(a < 0, b = 0\\) (eixo real negativo) \\(\\Rightarrow \\theta = \\pi\\)";
				dist.add("\\(" + k + "\\left(\\cos\\dfrac{\\pi}{2}+i\\sin\\dfrac{\\pi}{2}\\right)\\)");
				dist.add("\\(" + k + "\\left(\\cos\\dfrac{3\\pi}{2}+i\\sin\\dfrac{3\\pi}{2}\\right)\\)");
				dist.add("\\(" + k + "\\left(\\cos 0+i\\sin 0\\right)\\)");
				break;
		}

		String correct = "\\(" + rStr + "\\left(\\cos " + thetaStr + "+i\\sin " + thetaStr + "\\right)\\)";

		addParagrafo("Expresse \\(z\\) na forma trigonométrica \\(r(\\cos\\theta+i\\sin\\theta)\\).");
		addParagrafo("\\(z = " + zStr + "\\)");
		embaralharEAdicionarAlternativas(correct, dist);

		String res = "Calculamos o módulo: \\(\\\\\\)";
		res += "\\(" + rExpl + "\\). \\(\\\\\\)";
		res += "Determinamos o argumento \\(\\theta\\): \\(\\\\\\)";
		res += angleExpl + ". \\(\\\\\\)";
		res += "\\(z = \\mathbf{" + rStr + "\\left(\\cos " + thetaStr + "+i\\sin " + thetaStr + "\\right)}\\)";
		setResolucao(res);
	}
}
