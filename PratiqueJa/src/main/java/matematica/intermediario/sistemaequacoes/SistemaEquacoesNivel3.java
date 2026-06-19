package matematica.intermediario.sistemaequacoes;

import matematica.GeradorExercicio;

public class SistemaEquacoesNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Sistema1",
		".nivel3package.Sistema2",
		".nivel3package.Sistema3",
		".nivel3package.Sistema4",
		".nivel3package.Sistema5",
		".nivel3package.Sistema6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
