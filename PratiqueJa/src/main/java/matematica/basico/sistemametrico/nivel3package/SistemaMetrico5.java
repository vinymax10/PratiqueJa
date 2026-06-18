package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: m³ para dm³ (×1000)
public class SistemaMetrico5 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(18), "\\text{m}^3", 1000, "\\text{dm}^3");
	}
}
