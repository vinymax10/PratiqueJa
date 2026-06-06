package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel1package.Radiciacao"));
	}
}
