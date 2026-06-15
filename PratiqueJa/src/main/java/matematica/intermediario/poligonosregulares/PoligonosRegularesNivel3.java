package matematica.intermediario.poligonosregulares;

import matematica.GeradorExercicio;

public class PoligonosRegularesNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
