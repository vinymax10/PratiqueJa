package matematica.basico.racionais;

import matematica.GeradorExercicio;

public class RacionaisNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Racionais1",
		".nivel3package.Racionais2",
		".nivel3package.Racionais3",
		".nivel3package.Racionais4",
		".nivel3package.Racionais5",
		".nivel3package.Racionais6",
		".nivel3package.Racionais7",
		".nivel3package.Racionais8",
		".nivel3package.Racionais9",
		".nivel3package.Racionais11",
		".nivel3package.Racionais12",
		".nivel3package.Racionais16",
		".nivel3package.Racionais17"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
