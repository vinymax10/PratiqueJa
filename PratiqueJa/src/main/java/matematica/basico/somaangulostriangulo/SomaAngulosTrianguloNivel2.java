package matematica.basico.somaangulostriangulo;

import matematica.GeradorExercicio;

public class SomaAngulosTrianguloNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(41);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
