package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel1package.Potenciacao"));
	}
}
