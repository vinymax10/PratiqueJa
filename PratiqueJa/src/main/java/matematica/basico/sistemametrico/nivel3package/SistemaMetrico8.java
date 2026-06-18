package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: dm³ para m³ (÷1000)
public class SistemaMetrico8 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(18), "\\text{m}^3", 1000, "\\text{dm}^3");
	}
}
