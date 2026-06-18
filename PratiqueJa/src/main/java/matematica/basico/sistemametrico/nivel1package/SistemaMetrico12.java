package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Comprimento: m para dm (×10)
public class SistemaMetrico12 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(48), "m", 10, "dm");
	}
}
