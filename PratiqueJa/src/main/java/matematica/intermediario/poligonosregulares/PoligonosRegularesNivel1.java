package matematica.intermediario.poligonosregulares;

import matematica.GeradorExercicio;

public class PoligonosRegularesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel1package.Exercicio" + tipo));
	}
}
