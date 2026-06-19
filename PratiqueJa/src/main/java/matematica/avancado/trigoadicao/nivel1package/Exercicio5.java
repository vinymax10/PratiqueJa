package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(2a) com ângulo notável usando a fórmula de duplicação da tangente
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// choice 0: tg(2·30°) = tg(60°) = √3
		// choice 1: tg(2·60°) = tg(120°) = -√3
		int choice = rand.nextInt(2);

		String angulo, passoTg, denCalculo, denValor, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo     = "30°";
			passoTg    = "\\dfrac{\\sqrt{3}}{3}";
			denCalculo = "1 - \\left(\\dfrac{\\sqrt{3}}{3}\\right)^2 = 1 - \\dfrac{1}{3}";
			denValor   = "\\dfrac{2}{3}";
			resultado  = "\\sqrt{3}";
			distratores.add("\\(-\\sqrt{3}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{3}}{3}\\)");
			distratores.add("\\(\\dfrac{2\\sqrt{3}}{3}\\)");
		} else {
			angulo     = "60°";
			passoTg    = "\\sqrt{3}";
			denCalculo = "1 - (\\sqrt{3})^2 = 1 - 3";
			denValor   = "-2";
			resultado  = "-\\sqrt{3}";
			distratores.add("\\(\\sqrt{3}\\)");
			distratores.add("\\(-\\dfrac{\\sqrt{3}}{3}\\)");
			distratores.add("\\(-\\dfrac{\\sqrt{3}}{2}\\)");
		}

		addParagrafo("Use a fórmula de duplicação \\(\\operatorname{tg}(2a) = \\dfrac{2\\operatorname{tg}\\,a}{1 - \\operatorname{tg}^2 a}\\)"
				+ " para calcular \\(\\operatorname{tg}(2 \\cdot " + angulo + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		setResolucao("Com \\(\\operatorname{tg}(" + angulo + ") = " + passoTg + "\\). \\(\\\\\\)"
				+ "Denominador: \\(" + denCalculo + " = " + denValor + "\\). \\(\\\\\\)"
				+ "\\(\\operatorname{tg}(2 \\cdot " + angulo + ") = \\dfrac{2 \\cdot " + passoTg + "}{" + denValor + "} = \\mathbf{" + resultado + "}\\)");
	}
}
