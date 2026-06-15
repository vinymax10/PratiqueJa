package matematica.basico.somaangulostriangulo;

import matematica.GeradorExercicio;

public class SomaAngulosTrianguloNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(26);
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
