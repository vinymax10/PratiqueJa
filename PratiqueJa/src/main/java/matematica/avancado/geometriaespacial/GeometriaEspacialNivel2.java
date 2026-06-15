package matematica.avancado.geometriaespacial;

import matematica.GeradorExercicio;

public class GeometriaEspacialNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(7);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
