package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel2package.Potenciacao" + tipo));
	}
}
