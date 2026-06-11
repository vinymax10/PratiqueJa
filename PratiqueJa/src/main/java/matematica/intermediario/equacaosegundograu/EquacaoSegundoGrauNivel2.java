package matematica.intermediario.equacaosegundograu;

import matematica.GeradorExercicio;

public class EquacaoSegundoGrauNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(8);
		delegar(instanciar(".nivel2package.Expressao" + tipo));
	}
}
