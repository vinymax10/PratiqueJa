package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int choice = rand.nextInt(2);

		List<String> distratores = new ArrayList<>();
		String funcao, correta, res;

		if (choice == 0) {
			funcao  = "\\(f(x) = \\operatorname{sen}(x)\\)";
			correta = "\\(2\\pi\\)";
			distratores.add("\\(\\pi\\)");
			distratores.add("\\(\\dfrac{\\pi}{2}\\)");
			distratores.add("\\(4\\pi\\)");
			res = "A função seno completa um ciclo a cada volta completa no ciclo trigonométrico."
					+ "\\(\\\\\\)"
					+ "Período: \\(T = \\mathbf{2\\pi}\\)";
		} else {
			funcao  = "\\(f(x) = \\operatorname{tg}(x)\\)";
			correta = "\\(\\pi\\)";
			distratores.add("\\(2\\pi\\)");
			distratores.add("\\(\\dfrac{\\pi}{2}\\)");
			distratores.add("\\(4\\pi\\)");
			res = "A função tangente tem período metade do de seno/cosseno, pois"
					+ " \\(\\operatorname{tg}(x+\\pi) = \\operatorname{tg}(x)\\)."
					+ "\\(\\\\\\)"
					+ "Período: \\(T = \\mathbf{\\pi}\\)";
		}

		addParagrafo("Qual é o período da função " + funcao + "?");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
