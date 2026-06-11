package matematica.intermediario.equacaosegundograu;

import matematica.GeradorExercicio;

public class EquacaoSegundoGrauNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
