package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.ColunaDiv",
		".nivel3package.ComResto",
		".nivel3package.Contextualizada",
		".nivel3package.MissingDividendo",
		".nivel3package.Problema",
		".nivel3package.ProvaReal",
		".nivel3package.QuantasVezes",
		".nivel3package.TresEtapas"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
