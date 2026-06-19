package matematica.intermediario.funcoes;

import matematica.GeradorExercicio;

public class FuncoesNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Exercicio1",
		".nivel2package.Exercicio2",
		".nivel2package.Exercicio3",
		".nivel2package.Exercicio4",
		".nivel2package.Exercicio5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
