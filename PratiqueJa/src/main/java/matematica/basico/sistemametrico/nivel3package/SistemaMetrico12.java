package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Capacidade→volume: L para cm³ (×1000)
public class SistemaMetrico12 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(18), "\\text{L}", 1000, "\\text{cm}^3");
	}
}
