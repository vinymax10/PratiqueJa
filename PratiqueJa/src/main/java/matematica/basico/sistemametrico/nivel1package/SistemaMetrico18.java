package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Comprimento: mm para cm (÷10)
public class SistemaMetrico18 extends MetricoBase
{
	@Override
	protected void construir()
	{
		dividirExato(2 + rand.nextInt(48), "cm", 10, "mm");
	}
}
