package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Imagem (conjunto de valores) das funções trigonométricas
		int choice = rand.nextInt(3);

		String funcao, correta, res;
		List<String> distratores = new ArrayList<>();

		if (choice == 0) {
			funcao  = "\\(f(x) = \\operatorname{sen}(x)\\)";
			correta = "\\([-1,\\; 1]\\)";
			distratores.add("\\([0,\\; 1]\\)");
			distratores.add("\\(\\mathbb{R}\\)");
			distratores.add("\\([-\\pi,\\; \\pi]\\)");
			res = "No ciclo trigonométrico, o seno é a projeção vertical do ponto sobre a circunferência de raio 1."
					+ "\\(\\\\\\)"
					+ "O valor mínimo é \\(-1\\) (em \\(270°\\)) e o máximo é \\(1\\) (em \\(90°\\))."
					+ "\\(\\\\\\)"
					+ "Imagem: \\(\\mathbf{[-1,\\; 1]}\\)";
		} else if (choice == 1) {
			funcao  = "\\(f(x) = \\cos(x)\\)";
			correta = "\\([-1,\\; 1]\\)";
			distratores.add("\\([0,\\; 1]\\)");
			distratores.add("\\(\\mathbb{R}\\)");
			distratores.add("\\((0,\\; 1]\\)");
			res = "No ciclo trigonométrico, o cosseno é a projeção horizontal do ponto sobre a circunferência de raio 1."
					+ "\\(\\\\\\)"
					+ "O valor mínimo é \\(-1\\) (em \\(180°\\)) e o máximo é \\(1\\) (em \\(0°\\))."
					+ "\\(\\\\\\)"
					+ "Imagem: \\(\\mathbf{[-1,\\; 1]}\\)";
		} else {
			funcao  = "\\(f(x) = \\operatorname{tg}(x)\\)";
			correta = "\\(\\mathbb{R}\\)";
			distratores.add("\\([-1,\\; 1]\\)");
			distratores.add("\\([0,\\; +\\infty)\\)");
			distratores.add("\\((-1,\\; 1)\\)");
			res = "A tangente cresce sem limite à medida que \\(x\\) se aproxima de \\(\\dfrac{\\pi}{2}\\),"
					+ " e decresce sem limite ao se aproximar de \\(-\\dfrac{\\pi}{2}\\)."
					+ "\\(\\\\\\)"
					+ "A tangente assume qualquer valor real: imagem \\(= \\mathbf{\\mathbb{R}}\\)";
		}

		addParagrafo("Qual é a imagem (conjunto dos valores) da função " + funcao + "?");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
