package matematica.intermediario.notacaocientifica;

import matematica.GeradorExercicio;

public class NotacaoCientificaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(8);
		delegar(instanciar(".nivel2package.NotacaoCientifica" + tipo));
	}
}
