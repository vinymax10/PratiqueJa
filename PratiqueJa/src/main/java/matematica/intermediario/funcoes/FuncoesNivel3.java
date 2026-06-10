package matematica.intermediario.funcoes;

import matematica.GeradorExercicio;

public class FuncoesNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
