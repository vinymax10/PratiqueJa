package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Zeros das funções trigonométricas
		// choice 0: sen = 0 em 0°, 180°, 360°
		// choice 1: cos = 0 em 90°, 270°
		// choice 2: tg = 0 em 0°, 180°
		int choice = rand.nextInt(3);

		String funcao, correta, res;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			funcao  = "\\(f(x) = \\operatorname{sen}(x)\\)";
			correta = "\\(x = 180°\\)";
			distratores.add("\\(x = 90°\\)");
			distratores.add("\\(x = 45°\\)");
			distratores.add("\\(x = 270°\\)");
			res = "O seno vale zero quando o ponto está sobre o eixo horizontal do ciclo:"
					+ "\\(\\\\\\)"
					+ "\\(\\operatorname{sen}(0°) = 0\\), \\(\\operatorname{sen}(180°) = 0\\), \\(\\operatorname{sen}(360°) = 0\\)"
					+ "\\(\\\\\\)"
					+ "Dos ângulos apresentados, \\(\\mathbf{180°}\\) é um zero de \\(f\\)."
					+ "\\(\\\\\\)"
					+ "(\\(\\operatorname{sen}(90°)=1\\), \\(\\operatorname{sen}(45°)=\\dfrac{\\sqrt{2}}{2}\\), \\(\\operatorname{sen}(270°)=-1\\))";
		} else if (choice == 1) {
			funcao  = "\\(f(x) = \\cos(x)\\)";
			correta = "\\(x = 90°\\)";
			distratores.add("\\(x = 0°\\)");
			distratores.add("\\(x = 60°\\)");
			distratores.add("\\(x = 180°\\)");
			res = "O cosseno vale zero quando o ponto está sobre o eixo vertical do ciclo:"
					+ "\\(\\\\\\)"
					+ "\\(\\cos(90°) = 0\\), \\(\\cos(270°) = 0\\)"
					+ "\\(\\\\\\)"
					+ "Dos ângulos apresentados, \\(\\mathbf{90°}\\) é um zero de \\(f\\)."
					+ "\\(\\\\\\)"
					+ "(\\(\\cos(0°)=1\\), \\(\\cos(60°)=\\dfrac{1}{2}\\), \\(\\cos(180°)=-1\\))";
		} else {
			funcao  = "\\(f(x) = \\operatorname{tg}(x)\\)";
			correta = "\\(x = 180°\\)";
			distratores.add("\\(x = 90°\\)");
			distratores.add("\\(x = 60°\\)");
			distratores.add("\\(x = 270°\\)");
			res = "A tangente vale zero quando \\(\\operatorname{sen}(x) = 0\\) (e \\(\\cos(x)\\neq 0\\)):"
					+ "\\(\\\\\\)"
					+ "\\(\\operatorname{tg}(0°) = 0\\), \\(\\operatorname{tg}(180°) = 0\\)"
					+ "\\(\\\\\\)"
					+ "Dos ângulos apresentados, \\(\\mathbf{180°}\\) é um zero de \\(f\\)."
					+ "\\(\\\\\\)"
					+ "(Em \\(90°\\) e \\(270°\\) a tangente não está definida.)";
		}

		addParagrafo("Em qual dos ângulos " + listarOpcoes(correta, distratores) + " a função " + funcao + " vale zero?");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
