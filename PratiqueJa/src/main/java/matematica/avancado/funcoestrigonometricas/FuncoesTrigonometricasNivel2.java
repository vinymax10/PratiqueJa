package matematica.avancado.funcoestrigonometricas;

import matematica.GeradorExercicio;

public class FuncoesTrigonometricasNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(10);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
