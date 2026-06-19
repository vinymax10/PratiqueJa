package matematica.intermediario.equacoes;

import matematica.GeradorExercicio;

public class EquacoesNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Equacao1",
		".nivel1package.Equacao2",
		".nivel1package.Equacao3",
		".nivel1package.Equacao4",
		".nivel1package.Equacao6",
		".nivel1package.Equacao7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
