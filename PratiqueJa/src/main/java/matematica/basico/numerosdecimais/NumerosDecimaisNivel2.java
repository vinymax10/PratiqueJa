package matematica.basico.numerosdecimais;

import matematica.GeradorExercicio;

public class NumerosDecimaisNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Decimal1",
		".nivel2package.Decimal2",
		".nivel2package.Decimal3",
		".nivel2package.Decimal4",
		".nivel2package.Decimal5",
		".nivel2package.Decimal6",
		".nivel2package.Decimal7",
		".nivel2package.Decimal8",
		".nivel2package.Decimal9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
