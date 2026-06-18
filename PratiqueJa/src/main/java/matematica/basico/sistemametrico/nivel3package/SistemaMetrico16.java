package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: cm² para m² (÷10000)
public class SistemaMetrico16 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(8), "\\text{m}^2", 10000, "\\text{cm}^2");
	}
}
