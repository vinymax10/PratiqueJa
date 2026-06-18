package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: m² para dm² (×100)
public class SistemaMetrico13 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicarLatex(2 + rand.nextInt(38), "\\text{m}^2", 100, "\\text{dm}^2");
	}
}
