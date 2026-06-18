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
		".nivel2package.DivisaoPor10",
		".nivel2package.ColunaDiv2",
		".nivel2package.ColunaDiv3",
		".nivel2package.ComResto2",
		".nivel2package.ProvaReal2",
		".nivel2package.MissingDividendo2",
		".nivel2package.Por10b",
		".nivel2package.Problema",
		".nivel2package.Problema2",
		".nivel2package.QuantasVezes2",
		".nivel2package.ColunaDiv4",
		".nivel2package.ComResto3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
