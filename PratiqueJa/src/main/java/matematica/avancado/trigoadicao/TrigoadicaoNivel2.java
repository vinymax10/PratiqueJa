package matematica.avancado.trigoadicao;

import matematica.GeradorExercicio;

public class TrigoadicaoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(9);
		delegar(instanciar(".nivel2package.Exercicio" + tipo));
	}
}
