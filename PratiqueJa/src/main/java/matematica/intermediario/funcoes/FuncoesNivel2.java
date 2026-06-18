package matematica.intermediario.funcoes;

import matematica.GeradorExercicio;

public class FuncoesNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
