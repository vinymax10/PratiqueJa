package matematica.intermediario.equacoes;

import matematica.GeradorExercicio;

public class EquacoesNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Equacao1",
		".nivel3package.Equacao2",
		".nivel3package.Equacao3",
		".nivel3package.Equacao4",
		".nivel3package.Equacao5",
		".nivel3package.Equacao6",
		".nivel3package.Equacao7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
