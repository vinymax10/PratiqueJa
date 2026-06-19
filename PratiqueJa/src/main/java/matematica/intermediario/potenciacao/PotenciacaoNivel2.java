package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Potenciacao1",
		".nivel2package.Potenciacao2",
		".nivel2package.Potenciacao3",
		".nivel2package.Potenciacao4",
		".nivel2package.Potenciacao5",
		".nivel2package.Potenciacao6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
