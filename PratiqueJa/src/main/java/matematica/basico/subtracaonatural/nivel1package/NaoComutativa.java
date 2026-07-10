package matematica.basico.subtracaonatural.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class NaoComutativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 15 + rand.nextInt(25);
		int b = 3 + rand.nextInt(10);
		int c = a - b;

		addParagrafo("Sabendo que \\(" + a + " - " + b + " = " + c + "\\), o que é possível afirmar sobre \\(" + b + " - " + a + "\\)?");

		List<String> distratores = new ArrayList<>();
		distratores.add("É igual a \\(" + c + "\\), pois a ordem não importa");
		distratores.add("É igual a \\(" + (a + b) + "\\)");
		distratores.add("É igual a \\(0\\)");

		embaralharEAdicionarAlternativas(
			"Não é definida nos naturais, pois \\(" + b + " < " + a + "\\)",
			distratores);

		addResolucao("A subtração não é comutativa: a ordem dos termos importa.");
		addResolucao("Como \\(" + b + " < " + a + "\\), \\(\\textbf{não é possível calcular}\\) \\(" + b + " - " + a + "\\) nos números naturais.");
		addResolucao("Diferentemente da adição, \\(" + a + " - " + b + " \\neq " + b + " - " + a + "\\).");
	}
}
