package matematica.avancado.polinomios;

import matematica.GeradorExercicio;

public class PolinomiosNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Expressao1",
		".nivel2package.Expressao2",
		".nivel2package.Expressao3",
		".nivel2package.Expressao4",
		".nivel2package.Expressao5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
