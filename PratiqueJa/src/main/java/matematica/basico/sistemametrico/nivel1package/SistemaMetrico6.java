package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Capacidade: kL para L (×1000)
public class SistemaMetrico6 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(18), "kL", 1000, "L");
	}
}
