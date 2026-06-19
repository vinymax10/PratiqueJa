package matematica.intermediario.sistemaequacoes;

import matematica.GeradorExercicio;

public class SistemaEquacoesNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Sistema3",
		".nivel2package.Sistema4",
		".nivel2package.Sistema5",
		".nivel2package.Sistema6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
