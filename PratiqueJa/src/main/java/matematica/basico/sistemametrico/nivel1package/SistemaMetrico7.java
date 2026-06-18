package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Massa: g para mg (×1000)
public class SistemaMetrico7 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(18), "g", 1000, "mg");
	}
}
