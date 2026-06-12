package matematica.intermediario.jurosdesconto;

import matematica.GeradorExercicio;

public class JurosDescontoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel1package.JurosDesconto" + tipo));
	}
}
