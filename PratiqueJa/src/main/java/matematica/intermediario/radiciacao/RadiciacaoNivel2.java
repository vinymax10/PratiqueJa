package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(12);
		delegar(instanciar(".nivel2package.Radiciacao" + tipo));
	}
}
