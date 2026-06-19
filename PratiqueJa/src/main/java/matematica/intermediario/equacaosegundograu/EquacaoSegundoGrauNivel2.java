package matematica.intermediario.equacaosegundograu;

import matematica.GeradorExercicio;

public class EquacaoSegundoGrauNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Expressao1",
		".nivel2package.Expressao2",
		".nivel2package.Expressao3",
		".nivel2package.Expressao4",
		".nivel2package.Expressao5",
		".nivel2package.Expressao6",
		".nivel2package.Expressao7",
		".nivel2package.Expressao8"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
