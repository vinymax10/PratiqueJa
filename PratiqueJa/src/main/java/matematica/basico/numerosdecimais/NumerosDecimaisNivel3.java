package matematica.basico.numerosdecimais;

import matematica.GeradorExercicio;

public class NumerosDecimaisNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Decimal1",
		".nivel3package.Decimal2",
		".nivel3package.Decimal3",
		".nivel3package.Decimal4",
		".nivel3package.Decimal5",
		".nivel3package.Decimal6",
		".nivel3package.Decimal12",
		".nivel3package.Decimal17"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
