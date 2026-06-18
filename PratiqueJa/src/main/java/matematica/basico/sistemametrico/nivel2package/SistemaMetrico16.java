package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: dm² para m² (÷100)
public class SistemaMetrico16 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(38), "\\text{m}^2", 100, "\\text{dm}^2");
	}
}
