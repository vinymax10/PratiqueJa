package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

import java.util.ArrayList;
import java.util.List;

public class NaoComutativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(8);
		int q = 2 + rand.nextInt(8);
		int a = b * q;

		addParagrafo("Sabendo que \\(" + a + " \\div " + b + " = " + q + "\\), qual é o resultado de \\(" + b + " \\div " + a + "\\) nos números naturais?");

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + q + "\\), pois a ordem não altera o resultado");
		distratores.add("\\(" + a + "\\), pois os valores se invertem");
		distratores.add("\\(1\\), pois qualquer número dividido por outro dá \\(1\\)");
		embaralharEAdicionarAlternativas("\\(0\\) com resto \\(" + b + "\\), pois a divisão não é comutativa", distratores);

		addResolucao("A divisão não é comutativa: a ordem dos termos importa.");
		addResolucao("Como \\(" + b + " < " + a + "\\), ao dividir \\(" + b + "\\) por \\(" + a + "\\) nos naturais obtemos:");
		addResolucao("\\(" + b + " \\div " + a + " = 0\\) com resto \\(" + b + "\\).");
		addResolucao("Diferentemente, \\(" + a + " \\div " + b + " = " + q + "\\).");
		addResolucao("Portanto \\(" + a + " \\div " + b + " \\neq " + b + " \\div " + a + "\\).");
	}
}
