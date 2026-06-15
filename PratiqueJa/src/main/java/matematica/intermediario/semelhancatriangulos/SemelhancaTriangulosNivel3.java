package matematica.intermediario.semelhancatriangulos;

import matematica.GeradorExercicio;

public class SemelhancaTriangulosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(7);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
