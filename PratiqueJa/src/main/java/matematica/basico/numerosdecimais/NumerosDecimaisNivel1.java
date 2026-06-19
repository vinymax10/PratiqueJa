package matematica.basico.numerosdecimais;

import matematica.GeradorExercicio;

public class NumerosDecimaisNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Decimal1",
		".nivel1package.Decimal2",
		".nivel1package.Decimal3",
		".nivel1package.Decimal4",
		".nivel1package.Decimal5",
		".nivel1package.Decimal6",
		".nivel1package.Decimal7",
		".nivel1package.Decimal8",
		".nivel1package.Decimal9",
		".nivel1package.Decimal10",
		".nivel1package.Decimal11",
		".nivel1package.Decimal12"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
