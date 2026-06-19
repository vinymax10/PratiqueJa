package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Coluna2",
		".nivel2package.ColunaSoma",
		".nivel2package.Contextualizada",
		".nivel2package.Diferenca",
		".nivel2package.MissingMinuendo",
		".nivel2package.MissingSubtraendo",
		".nivel2package.Problema",
		".nivel2package.ProvaReal",
		".nivel2package.TresEtapas"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
