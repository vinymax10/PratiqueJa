package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equivalência algébrica usando identidade fundamental
		// choice 0: 1 - cos²θ = sen²θ
		// choice 1: (sen²θ + cos²θ) / cos²θ = sec²θ = tg²θ + 1
		// choice 2: 1 - sen²θ = cos²θ
		int choice = rand.nextInt(3);

		String expressao, correta, res;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			expressao = "\\(1 - \\cos^2\\theta\\)";
			correta   = "\\(\\operatorname{sen}^2\\theta\\)";
			distratores.add("\\(\\operatorname{tg}^2\\theta\\)");
			distratores.add("\\(\\cos^2\\theta\\)");
			distratores.add("\\(1 + \\cos^2\\theta\\)");
			res = "Pela identidade fundamental \\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\):"
					+ "\\(\\\\\\)"
					+ "\\(\\operatorname{sen}^2\\theta = 1 - \\cos^2\\theta\\)"
					+ "\\(\\\\\\)"
					+ "Portanto \\(1 - \\cos^2\\theta = \\mathbf{\\operatorname{sen}^2\\theta}\\)";
		} else if (choice == 1) {
			expressao = "\\(\\dfrac{\\operatorname{sen}^2\\theta + \\cos^2\\theta}{\\cos^2\\theta}\\)";
			correta   = "\\(\\operatorname{tg}^2\\theta + 1\\)";
			distratores.add("\\(\\operatorname{tg}^2\\theta\\)");
			distratores.add("\\(\\operatorname{sen}^2\\theta + 1\\)");
			distratores.add("\\(1\\)");
			res = "O numerador \\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\), logo:"
					+ "\\(\\\\\\)"
					+ "\\(\\dfrac{1}{\\cos^2\\theta} = \\sec^2\\theta\\)"
					+ "\\(\\\\\\)"
					+ "Pela identidade \\(\\operatorname{tg}^2\\theta + 1 = \\sec^2\\theta\\):"
					+ "\\(\\\\\\)"
					+ "\\(\\dfrac{\\operatorname{sen}^2\\theta + \\cos^2\\theta}{\\cos^2\\theta} = \\mathbf{\\operatorname{tg}^2\\theta + 1}\\)";
		} else {
			expressao = "\\(1 - \\operatorname{sen}^2\\theta\\)";
			correta   = "\\(\\cos^2\\theta\\)";
			distratores.add("\\(\\operatorname{sen}^2\\theta\\)");
			distratores.add("\\(\\operatorname{tg}^2\\theta\\)");
			distratores.add("\\(1 + \\operatorname{sen}^2\\theta\\)");
			res = "Pela identidade fundamental \\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\):"
					+ "\\(\\\\\\)"
					+ "\\(\\cos^2\\theta = 1 - \\operatorname{sen}^2\\theta\\)"
					+ "\\(\\\\\\)"
					+ "Portanto \\(1 - \\operatorname{sen}^2\\theta = \\mathbf{\\cos^2\\theta}\\)";
		}

		addParagrafo("Qual das expressões abaixo é equivalente a " + expressao + "?");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
