package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: s para h (÷3600)
public class SistemaMetrico12 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(8), "h", 3600, "s");
	}
}
