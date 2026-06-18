package matematica.basico.sistemametrico.nivel1package;

import matematica.basico.sistemametrico.MetricoBase;

// Capacidade: L para cL (×100)
public class SistemaMetrico10 extends MetricoBase
{
	@Override
	protected void construir()
	{
		multiplicar(2 + rand.nextInt(38), "L", 100, "cL");
	}
}
