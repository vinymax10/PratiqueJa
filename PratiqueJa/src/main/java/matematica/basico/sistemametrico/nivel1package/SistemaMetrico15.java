package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Massa: g para kg (÷1000)
public class SistemaMetrico15 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(18), "kg", 1000, "g");
	}
}
