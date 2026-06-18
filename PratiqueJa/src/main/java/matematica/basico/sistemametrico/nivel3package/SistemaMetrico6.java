package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: dm³ para cm³ (×1000)
public class SistemaMetrico6 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(18), "\\text{dm}^3", 1000, "\\text{cm}^3");
	}
}
