package matematica.intermediario.jurosdesconto;

import matematica.GeradorExercicio;

public class JurosDescontoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel3package.JurosDesconto" + tipo));
	}
}
