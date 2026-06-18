package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: m³ para cm³ (×1.000.000)
public class SistemaMetrico7 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(6), "\\text{m}^3", 1000000, "\\text{cm}^3");
	}
}
