package matematica.intermediario.anguloinscritocircunferencia;

import matematica.GeradorExercicio;

public class AnguloInscritoCircunferenciaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Image1",
		".nivel3package.Image2",
		".nivel3package.Image3",
		".nivel3package.Image4",
		".nivel3package.Image5",
		".nivel3package.Image6",
		".nivel3package.Image7",
		".nivel3package.Image8",
		".nivel3package.Image9",
		".nivel3package.Image10",
		".nivel3package.Image11",
		".nivel3package.Image12"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
