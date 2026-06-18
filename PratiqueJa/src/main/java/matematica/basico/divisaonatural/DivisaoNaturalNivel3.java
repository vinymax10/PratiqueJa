package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.ColunaDiv",
		".nivel3package.ComResto",
		".nivel3package.Contextualizada",
		".nivel3package.ProvaReal",
		".nivel3package.QuantasVezes",
		".nivel3package.MissingDividendo",
		".nivel3package.TresEtapas",
		".nivel3package.ColunaDiv2",
		".nivel3package.ColunaDiv3",
		".nivel3package.ComResto2",
		".nivel3package.TresEtapas2",
		".nivel3package.ProvaReal2",
		".nivel3package.MissingDividendo2",
		".nivel3package.Problema",
		".nivel3package.Problema2",
		".nivel3package.Por10c",
		".nivel3package.ComResto3",
		".nivel3package.QuantasVezes2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
