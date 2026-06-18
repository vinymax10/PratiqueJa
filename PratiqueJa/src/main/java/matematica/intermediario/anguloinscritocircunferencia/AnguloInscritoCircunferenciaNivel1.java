package matematica.intermediario.anguloinscritocircunferencia;

import matematica.GeradorExercicio;

public class AnguloInscritoCircunferenciaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
