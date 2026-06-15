package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.ColunaDiv",
		".nivel2package.ComResto",
		".nivel2package.Contextualizada",
		".nivel2package.ProvaReal",
		".nivel2package.QuantasVezes",
		".nivel2package.MissingDividendo",
		".nivel2package.DivisaoPor10"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
