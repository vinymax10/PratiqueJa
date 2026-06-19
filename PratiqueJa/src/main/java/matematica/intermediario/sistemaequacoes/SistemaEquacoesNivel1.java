package matematica.intermediario.sistemaequacoes;

import matematica.GeradorExercicio;

public class SistemaEquacoesNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Sistema1",
		".nivel1package.Sistema2",
		".nivel1package.Sistema3",
		".nivel1package.Sistema4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
