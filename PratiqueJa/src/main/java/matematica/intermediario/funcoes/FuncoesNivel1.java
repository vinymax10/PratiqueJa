package matematica.intermediario.funcoes;

import matematica.GeradorExercicio;

public class FuncoesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Exercicio" + tipo));
	}
}
