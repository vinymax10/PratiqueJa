package matematica.avancado.funcaoquadratica;

import matematica.GeradorExercicio;

public class FuncaoQuadraticaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel1package.Expressao1"));
	}
}
