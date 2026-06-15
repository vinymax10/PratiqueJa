package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.ElementoNeutro",
		".nivel1package.TermosSubtracao",
		".nivel1package.ProvaReal",
		".nivel1package.Contextualizada",
		".nivel1package.NaoComutativa",
		".nivel1package.NaoAssociativa",
		".nivel1package.MissingMinuendo",
		".nivel1package.MissingSubtraendo",
		".nivel1package.Diferenca"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
