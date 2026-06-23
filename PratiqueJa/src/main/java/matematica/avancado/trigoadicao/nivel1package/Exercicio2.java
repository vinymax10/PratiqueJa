package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(a+b) ou cos(a-b) com ângulos notáveis → resultado com radicais
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int choice = rand.nextInt(2);

		String enunciado, correta;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			enunciado = "Calcule \\(\\cos(75°)\\) usando a fórmula de adição"
					+ " \\(\\cos(45°+30°)\\).";
			correta = "\\(\\dfrac{\\sqrt{6}-\\sqrt{2}}{4}\\)";
			distratores.add("\\(\\dfrac{\\sqrt{6}+\\sqrt{2}}{4}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{6}-\\sqrt{2}}{2}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{3}-1}{4}\\)");
		} else {
			enunciado = "Calcule \\(\\cos(15°)\\) usando a fórmula de subtração"
					+ " \\(\\cos(45°-30°)\\).";
			correta = "\\(\\dfrac{\\sqrt{6}+\\sqrt{2}}{4}\\)";
			distratores.add("\\(\\dfrac{\\sqrt{6}-\\sqrt{2}}{4}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{6}+\\sqrt{2}}{2}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{3}+1}{4}\\)");
		}

		addParagrafo(enunciado);
		embaralharEAdicionarAlternativas(correta, distratores);

		if (choice == 0) {
			addResolucao("Aplicando \\(\\cos(a+b) = \\cos a\\cos b - \\operatorname{sen}\\,a\\,\\operatorname{sen}\\,b\\):");
			addResolucao("\\(\\cos(45°+30°) =\\)");
			addResolucao("\\(\\dfrac{\\sqrt{2}}{2}\\cdot\\dfrac{\\sqrt{3}}{2} - \\dfrac{\\sqrt{2}}{2}\\cdot\\dfrac{1}{2} =\\)");
			addResolucao("\\(\\dfrac{\\sqrt{6}}{4} - \\dfrac{\\sqrt{2}}{4} = \\mathbf{\\dfrac{\\sqrt{6}-\\sqrt{2}}{4}}\\)");
		} else {
			addResolucao("Aplicando \\(\\cos(a-b) = \\cos a\\cos b + \\operatorname{sen}\\,a\\,\\operatorname{sen}\\,b\\):");
			addResolucao("\\(\\cos(45°-30°) =\\)");
			addResolucao("\\(\\dfrac{\\sqrt{2}}{2}\\cdot\\dfrac{\\sqrt{3}}{2} + \\dfrac{\\sqrt{2}}{2}\\cdot\\dfrac{1}{2} =\\)");
			addResolucao("\\(\\dfrac{\\sqrt{6}}{4} + \\dfrac{\\sqrt{2}}{4} = \\mathbf{\\dfrac{\\sqrt{6}+\\sqrt{2}}{4}}\\)");
		}
	}
}
