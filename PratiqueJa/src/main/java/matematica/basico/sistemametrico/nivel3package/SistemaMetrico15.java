package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: m² para cm² (×10000)
public class SistemaMetrico15 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(8), "\\text{m}^2", 10000, "\\text{cm}^2");
	}
}
