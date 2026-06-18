package matematica.intermediario.funcaoafim;

import matematica.GeradorExercicio;

public class FuncaoAfimNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
