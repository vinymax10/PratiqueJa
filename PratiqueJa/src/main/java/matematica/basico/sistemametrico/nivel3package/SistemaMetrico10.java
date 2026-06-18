package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: cm³ para m³ (÷1.000.000)
public class SistemaMetrico10 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(6), "\\text{m}^3", 1000000, "\\text{cm}^3");
	}
}
