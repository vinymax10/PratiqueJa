package matematica.avancado.funcaomodular;

import matematica.GeradorExercicio;

public class FuncaoModularNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
