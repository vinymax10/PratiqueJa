package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Comprimento: cm para mm (×10)
public class SistemaMetrico13 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(58), "cm", 10, "mm");
	}
}
