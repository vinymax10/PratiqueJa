package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Volume: cm³ para dm³ (÷1000)
public class SistemaMetrico9 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(18), "\\text{dm}^3", 1000, "\\text{cm}^3");
	}
}
