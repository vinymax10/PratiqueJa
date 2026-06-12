package matematica.avancado.funcaoexponencial;

import matematica.GeradorExercicio;

public class FuncaoExponencialNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
