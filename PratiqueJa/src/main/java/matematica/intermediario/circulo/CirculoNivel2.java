package matematica.intermediario.circulo;

import matematica.GeradorExercicio;

public class CirculoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
