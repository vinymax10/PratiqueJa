package matematica.intermediario.funcoes;

import matematica.GeradorExercicio;

public class FuncoesNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Exercicio1",
		".nivel3package.Exercicio2",
		".nivel3package.Exercicio3",
		".nivel3package.Exercicio4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
