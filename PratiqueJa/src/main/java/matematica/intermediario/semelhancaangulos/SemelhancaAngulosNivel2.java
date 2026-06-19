package matematica.intermediario.semelhancaangulos;

import matematica.GeradorExercicio;

public class SemelhancaAngulosNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Exercicio1",
		".nivel2package.Exercicio2",
		".nivel2package.Exercicio3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
