package matematica.intermediario.circulo;

import matematica.GeradorExercicio;

public class CirculoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
