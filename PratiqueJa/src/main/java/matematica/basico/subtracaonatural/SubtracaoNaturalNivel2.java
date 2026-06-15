package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.ColunaSoma",
		".nivel2package.Contextualizada",
		".nivel2package.ProvaReal",
		".nivel2package.Diferenca",
		".nivel2package.TresEtapas"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
