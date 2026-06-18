package matematica.avancado.trigoadicao.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(75°) = 2+√3  ou  tg(15°) = 2−√3  pela fórmula de adição/subtração da tangente
public class Exercicio16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int choice = rand.nextInt(2);

		String angulo, operacao, formula, passo1, passo2, passo3, resultado;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			angulo    = "75°";
			operacao  = "45°+30°";
			formula   = "\\operatorname{tg}(a+b) = \\dfrac{\\operatorname{tg}\\,a + \\operatorname{tg}\\,b}{1 - \\operatorname{tg}\\,a\\cdot\\operatorname{tg}\\,b}";
			passo1    = "\\dfrac{1 + \\dfrac{\\sqrt{3}}{3}}{1 - 1 \\cdot \\dfrac{\\sqrt{3}}{3}}";
			passo2    = "\\dfrac{\\dfrac{3+\\sqrt{3}}{3}}{\\dfrac{3-\\sqrt{3}}{3}} = \\dfrac{3+\\sqrt{3}}{3-\\sqrt{3}}";
			passo3    = "\\dfrac{(3+\\sqrt{3})^2}{(3-\\sqrt{3})(3+\\sqrt{3})} = \\dfrac{12+6\\sqrt{3}}{6}";
			resultado = "2+\\sqrt{3}";
			distratores.add("\\(2-\\sqrt{3}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{6}+\\sqrt{2}}{4}\\)");
			distratores.add("\\(\\sqrt{3}+1\\)");
		} else {
			angulo    = "15°";
			operacao  = "45°-30°";
			formula   = "\\operatorname{tg}(a-b) = \\dfrac{\\operatorname{tg}\\,a - \\operatorname{tg}\\,b}{1 + \\operatorname{tg}\\,a\\cdot\\operatorname{tg}\\,b}";
			passo1    = "\\dfrac{1 - \\dfrac{\\sqrt{3}}{3}}{1 + 1 \\cdot \\dfrac{\\sqrt{3}}{3}}";
			passo2    = "\\dfrac{\\dfrac{3-\\sqrt{3}}{3}}{\\dfrac{3+\\sqrt{3}}{3}} = \\dfrac{3-\\sqrt{3}}{3+\\sqrt{3}}";
			passo3    = "\\dfrac{(3-\\sqrt{3})^2}{(3+\\sqrt{3})(3-\\sqrt{3})} = \\dfrac{12-6\\sqrt{3}}{6}";
			resultado = "2-\\sqrt{3}";
			distratores.add("\\(2+\\sqrt{3}\\)");
			distratores.add("\\(\\dfrac{\\sqrt{6}-\\sqrt{2}}{4}\\)");
			distratores.add("\\(\\sqrt{3}-1\\)");
		}

		addParagrafo("Use a fórmula \\(" + formula + "\\) para calcular"
				+ " \\(\\operatorname{tg}(" + angulo + ") = \\operatorname{tg}(" + operacao + ")\\).");
		embaralharEAdicionarAlternativas("\\(" + resultado + "\\)", distratores);

		setResolucao("Aplicando a fórmula com \\(\\operatorname{tg}(45°)=1\\) e \\(\\operatorname{tg}(30°)=\\dfrac{\\sqrt{3}}{3}\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{tg}(" + angulo + ") = " + passo1 + " = \\\\"
				+ passo2 + " = \\\\"
				+ passo3 + " = \\mathbf{" + resultado + "}\\)");
	}
}
