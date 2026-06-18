package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(2a) com ângulo notável → resultado notável
public class Exercicio12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// choice 0: sen(2·30°) = √3/2; choice 1: sen(2·45°) = 1; choice 2: sen(2·60°) = √3/2
		int choice = rand.nextInt(3);

		String angulo, passo1, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo    = "30°";
			passo1    = "2 \\cdot \\dfrac{1}{2} \\cdot \\dfrac{\\sqrt{3}}{2}";
			resultado = "\\dfrac{\\sqrt{3}}{2}";
			distratores.add("\\(1\\)");
			distratores.add("\\(\\dfrac{\\sqrt{2}}{2}\\)");
			distratores.add("\\(\\dfrac{1}{2}\\)");
		} else if (choice == 1) {
			angulo    = "45°";
			passo1    = "2 \\cdot \\dfrac{\\sqrt{2}}{2} \\cdot \\dfrac{\\sqrt{2}}{2} = 2 \\cdot \\dfrac{1}{2}";
			resultado = "1";
			distratores.add("\\(\\dfrac{\\sqrt{2}}{2}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{3}}{2}\\)");
			distratores.add("\\(0\\)");
		} else {
			angulo    = "60°";
			passo1    = "2 \\cdot \\dfrac{\\sqrt{3}}{2} \\cdot \\dfrac{1}{2}";
			resultado = "\\dfrac{\\sqrt{3}}{2}";
			distratores.add("\\(-\\dfrac{1}{2}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{2}}{2}\\)");
			distratores.add("\\(1\\)");
		}

		addParagrafo("Use a fórmula de duplicação para calcular \\(\\operatorname{sen}(2 \\cdot " + angulo + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		setResolucao("Pela fórmula \\(\\operatorname{sen}(2a) = 2\\operatorname{sen}\\,a\\cos a\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}(2 \\cdot " + angulo + ") = " + passo1 + " = \\mathbf{" + resultado + "}\\)");
	}
}
