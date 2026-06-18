package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Capacidade: mL para L (÷1000)
public class SistemaMetrico16 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(18), "L", 1000, "mL");
	}
}
