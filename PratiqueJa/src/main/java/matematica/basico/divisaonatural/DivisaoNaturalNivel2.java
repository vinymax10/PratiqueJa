package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.ColunaDiv",
		".nivel2package.ComResto",
		".nivel2package.Contextualizada",
		".nivel2package.DivisaoPor10",
		".nivel2package.MissingDividendo",
		".nivel2package.Problema",
		".nivel2package.ProvaReal",
		".nivel2package.QuantasVezes"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
