package matematica.intermediario.semelhancaangulos;

import matematica.GeradorExercicio;

public class SemelhancaAngulosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 6 + rand.nextInt(1);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
