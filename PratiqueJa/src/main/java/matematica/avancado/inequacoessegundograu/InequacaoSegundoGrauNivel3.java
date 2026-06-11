package matematica.avancado.inequacoessegundograu;

import matematica.GeradorExercicio;

public class InequacaoSegundoGrauNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel3package.Image" + tipo));
	}
}
