package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: min para s (×60)
public class SistemaMetrico7 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(28), "min", 60, "s");
	}
}
