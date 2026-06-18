package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo: h para s (×3600)
public class SistemaMetrico14 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(8), "h", 3600, "s");
	}
}
