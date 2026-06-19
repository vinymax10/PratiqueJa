package matematica.basico.racionais;

import matematica.GeradorExercicio;

public class RacionaisNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Racionais1",
		".nivel2package.Racionais2",
		".nivel2package.Racionais3",
		".nivel2package.Racionais4",
		".nivel2package.Racionais5",
		".nivel2package.Racionais6",
		".nivel2package.Racionais7",
		".nivel2package.Racionais8",
		".nivel2package.Racionais9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
