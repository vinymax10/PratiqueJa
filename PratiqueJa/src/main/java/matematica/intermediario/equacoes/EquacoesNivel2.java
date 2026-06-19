package matematica.intermediario.equacoes;

import matematica.GeradorExercicio;

public class EquacoesNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Equacao1",
		".nivel2package.Equacao2",
		".nivel2package.Equacao3",
		".nivel2package.Equacao4",
		".nivel2package.Equacao5",
		".nivel2package.Equacao6",
		".nivel2package.Equacao7",
		".nivel2package.Equacao10"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
