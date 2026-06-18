package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume→capacidade: m³ para L (×1000)
public class SistemaMetrico11 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(18), "\\text{m}^3", 1000, "\\text{L}");
	}
}
