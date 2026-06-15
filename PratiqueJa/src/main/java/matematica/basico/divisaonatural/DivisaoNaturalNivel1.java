package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.TermosDivisao",
		".nivel1package.ElementoNeutro",
		".nivel1package.ProvaReal",
		".nivel1package.Contextualizada",
		".nivel1package.DivisaoPorPotencia10",
		".nivel1package.ExataVsInexata",
		".nivel1package.Tabuada",
		".nivel1package.NaoComutativa",
		".nivel1package.QuantasVezes",
		".nivel1package.MissingFator"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
