package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Massa: kg para g (×1000)
public class SistemaMetrico9 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(18), "kg", 1000, "g");
	}
}
