package matematica.avancado.inequacoessegundograu;

import matematica.GeradorExercicio;

public class InequacaoSegundoGrauNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Image1",
		".nivel3package.Image2",
		".nivel3package.Image3",
		".nivel3package.Image4",
		".nivel3package.Image5",
		".nivel3package.Image6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
