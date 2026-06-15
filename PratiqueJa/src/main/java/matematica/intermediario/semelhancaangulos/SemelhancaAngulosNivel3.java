package matematica.intermediario.semelhancaangulos;

import matematica.GeradorExercicio;

public class SemelhancaAngulosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] tipos = {1, 2, 6};
		int tipo = tipos[rand.nextInt(tipos.length)];
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
