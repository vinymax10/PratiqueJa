package matematica.avancado.geometriaespacial;

import matematica.GeradorExercicio;

public class GeometriaEspacialNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
