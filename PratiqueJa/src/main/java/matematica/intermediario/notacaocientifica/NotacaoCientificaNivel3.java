package matematica.intermediario.notacaocientifica;

import matematica.GeradorExercicio;

public class NotacaoCientificaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel3package.NotacaoCientifica" + tipo));
	}
}
