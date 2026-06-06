package matematica.intermediario.semelhancatriangulos;

import matematica.GeradorExercicio;

public class SemelhancaTriangulosNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(3);
		delegar(instanciar(".nivel1package.Exercicio" + tipo));
	}
}
