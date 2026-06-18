package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Capacidade: L para mL (×1000)
public class SistemaMetrico5 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(18), "L", 1000, "mL");
	}
}
