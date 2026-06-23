package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

// cos(2a) = cos²(a) - sen²(a) com ângulo notável
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// choice 0: cos(2·30°) = 1/2; choice 1: cos(2·45°) = 0; choice 2: cos(2·60°) = -1/2
		int choice = rand.nextInt(3);

		String angulo, passo1, passo2, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo    = "30°";
			passo1    = "\\left(\\dfrac{\\sqrt{3}}{2}\\right)^2 - \\left(\\dfrac{1}{2}\\right)^2";
			passo2    = "\\dfrac{3}{4} - \\dfrac{1}{4}";
			resultado = "\\dfrac{1}{2}";
			distratores.add("\\(-\\dfrac{1}{2}\\)");
			distratores.add("\\(0\\)");
			distratores.add("\\(\\dfrac{\\sqrt{3}}{2}\\)");
		} else if (choice == 1) {
			angulo    = "45°";
			passo1    = "\\left(\\dfrac{\\sqrt{2}}{2}\\right)^2 - \\left(\\dfrac{\\sqrt{2}}{2}\\right)^2";
			passo2    = "\\dfrac{1}{2} - \\dfrac{1}{2}";
			resultado = "0";
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(-\\dfrac{1}{2}\\)");
			distratores.add("\\(1\\)");
		} else {
			angulo    = "60°";
			passo1    = "\\left(\\dfrac{1}{2}\\right)^2 - \\left(\\dfrac{\\sqrt{3}}{2}\\right)^2";
			passo2    = "\\dfrac{1}{4} - \\dfrac{3}{4}";
			resultado = "-\\dfrac{1}{2}";
			distratores.add("\\(\\dfrac{1}{2}\\)");
			distratores.add("\\(0\\)");
			distratores.add("\\(-\\dfrac{\\sqrt{3}}{2}\\)");
		}

		addParagrafo("Use a fórmula \\(\\cos(2a) = \\cos^2 a - \\operatorname{sen}^2 a\\) para calcular"
				+ " \\(\\cos(2 \\cdot " + angulo + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		addResolucao("Pela fórmula \\(\\cos(2a) = \\cos^2 a - \\operatorname{sen}^2 a\\):");
		addResolucao("\\(\\cos(2 \\cdot " + angulo + ") = " + passo1 + " =\\)");
		addResolucao("\\(" + passo2 + " = \\mathbf{" + resultado + "}\\)");
	}
}
