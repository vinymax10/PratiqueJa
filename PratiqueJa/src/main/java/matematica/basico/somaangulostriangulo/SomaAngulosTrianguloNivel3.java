package matematica.basico.somaangulostriangulo;

import matematica.GeradorExercicio;

public class SomaAngulosTrianguloNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel3package.Image" + tipo));
	}
}
