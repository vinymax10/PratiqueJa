package matematica.avancado.funcoestrigonometricas;

import matematica.GeradorExercicio;

public class FuncoesTrigonometricasNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Exercicio" + tipo));
	}
}
