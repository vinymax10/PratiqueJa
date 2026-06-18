package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: s para min (÷60)
public class SistemaMetrico10 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(28), "min", 60, "s");
	}
}
