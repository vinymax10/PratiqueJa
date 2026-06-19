package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

public class SubtracaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Coluna1",
		".nivel1package.Contextualizada",
		".nivel1package.Diferenca",
		".nivel1package.ElementoNeutro",
		".nivel1package.MissingMinuendo",
		".nivel1package.MissingSubtraendo",
		".nivel1package.NaoAssociativa",
		".nivel1package.NaoComutativa",
		".nivel1package.Problema1",
		".nivel1package.ProvaReal",
		".nivel1package.Tabuada1",
		".nivel1package.TermosSubtracao",
		".nivel1package.TresEtapas1"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
