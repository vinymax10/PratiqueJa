package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: dia para min (×1440)
public class SistemaMetrico13 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(14), "dia", 1440, "min");
	}
}
