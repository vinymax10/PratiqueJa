package matematica.intermediario.anguloinscritocircunferencia;

import matematica.GeradorExercicio;

public class AnguloInscritoCircunferenciaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(12);
		delegar(instanciar(".nivel3package.Image" + tipo));
	}
}
