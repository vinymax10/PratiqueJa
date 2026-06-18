package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: min para h (÷60)
public class SistemaMetrico11 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(12), "h", 60, "min");
	}
}
