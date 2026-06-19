package matematica.intermediario.razoestrigonometricas;

import matematica.GeradorExercicio;

public class RazoesTrigonometricasNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Image1",
		".nivel2package.Image2",
		".nivel2package.Image3",
		".nivel2package.Image4",
		".nivel2package.Image5",
		".nivel2package.Image6",
		".nivel2package.Image7",
		".nivel2package.Image8",
		".nivel2package.Image9",
		".nivel2package.Image10",
		".nivel2package.Image11",
		".nivel2package.Image12"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
