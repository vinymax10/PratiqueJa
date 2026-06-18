package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Comprimento: m para km (÷1000)
public class SistemaMetrico14 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(18), "km", 1000, "m");
	}
}
