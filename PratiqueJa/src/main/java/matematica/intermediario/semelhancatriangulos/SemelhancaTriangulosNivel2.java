package matematica.intermediario.semelhancatriangulos;

import matematica.GeradorExercicio;

public class SemelhancaTriangulosNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
