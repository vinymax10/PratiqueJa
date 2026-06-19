package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Coluna2",
		".nivel3package.ColunaSoma",
		".nivel3package.Contextualizada",
		".nivel3package.Diferenca",
		".nivel3package.EmprestimoZeros1",
		".nivel3package.MissingMinuendo",
		".nivel3package.MissingSubtraendo",
		".nivel3package.Problema",
		".nivel3package.ProvaReal",
		".nivel3package.QuatroEtapas1",
		".nivel3package.TresEtapas"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
