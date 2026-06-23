package matematica.intermediario.potenciacao.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Potenciacao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(18); // 2..19

		addParagrafo("Calcule:");
		addParagrafo("\\(" + a + "^{0}\\)");
		embaralharEAdicionarAlternativas("1", Arrays.asList("0", "" + a, "-1"));
		addResolucao("Todo número não nulo elevado a \\(0\\) é igual a \\(1\\).");
		addResolucao("\\(" + a + "^{0} = \\mathbf{1}\\)");
	}
}
