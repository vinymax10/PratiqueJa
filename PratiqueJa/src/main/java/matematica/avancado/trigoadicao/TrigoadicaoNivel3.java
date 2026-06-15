package matematica.avancado.trigoadicao;

import matematica.GeradorExercicio;

public class TrigoadicaoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(9);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
