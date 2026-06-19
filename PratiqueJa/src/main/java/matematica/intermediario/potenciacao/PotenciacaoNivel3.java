package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Potenciacao1",
		".nivel3package.Potenciacao2",
		".nivel3package.Potenciacao3",
		".nivel3package.Potenciacao4",
		".nivel3package.Potenciacao5",
		".nivel3package.Potenciacao6",
		".nivel3package.Potenciacao7",
		".nivel3package.Potenciacao8",
		".nivel3package.Potenciacao9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
