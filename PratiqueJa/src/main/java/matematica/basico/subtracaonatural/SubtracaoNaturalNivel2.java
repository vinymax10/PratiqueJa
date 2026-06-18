package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.ColunaSoma",
		".nivel2package.Contextualizada",
		".nivel2package.ProvaReal",
		".nivel2package.Diferenca",
		".nivel2package.TresEtapas",
		".nivel2package.Coluna2",
		".nivel2package.Coluna3",
		".nivel2package.Diferenca2",
		".nivel2package.ProvaReal2",
		".nivel2package.TresEtapas2",
		".nivel2package.MissingMinuendo",
		".nivel2package.MissingSubtraendo",
		".nivel2package.Problema",
		".nivel2package.Problema2",
		".nivel2package.Coluna4",
		".nivel2package.TresEtapas3",
		".nivel2package.Diferenca3",
		".nivel2package.Coluna5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
