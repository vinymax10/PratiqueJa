package matematica.basico.sistemametrico.nivel2package;

import matematica.basico.sistemametrico.MetricoBase;

// Área: cm² para dm² (÷100)
public class SistemaMetrico17 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExatoLatex(2 + rand.nextInt(38), "\\text{dm}^2", 100, "\\text{cm}^2");
	}
}
