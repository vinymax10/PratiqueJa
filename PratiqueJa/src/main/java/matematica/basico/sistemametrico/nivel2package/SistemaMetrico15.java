package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: dm² para cm² (×100)
public class SistemaMetrico15 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(38), "\\text{dm}^2", 100, "\\text{cm}^2");
	}
}
