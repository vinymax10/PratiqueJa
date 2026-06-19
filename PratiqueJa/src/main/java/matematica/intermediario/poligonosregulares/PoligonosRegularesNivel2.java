package matematica.intermediario.poligonosregulares;

import matematica.GeradorExercicio;

public class PoligonosRegularesNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Exercicio1",
		".nivel2package.Exercicio2",
		".nivel2package.Exercicio3",
		".nivel2package.Exercicio4",
		".nivel2package.Exercicio5",
		".nivel2package.Exercicio6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
