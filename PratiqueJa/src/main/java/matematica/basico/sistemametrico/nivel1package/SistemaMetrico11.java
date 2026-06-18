package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Comprimento: m para cm (×100)
public class SistemaMetrico11 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(38), "m", 100, "cm");
	}
}
