package matematica.intermediario.anguloinscritocircunferencia;

import matematica.GeradorExercicio;

public class AnguloInscritoCircunferenciaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(8);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
