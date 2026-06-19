package matematica.avancado.geometriaespacial;

import matematica.GeradorExercicio;

public class GeometriaEspacialNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Exercicio1",
		".nivel2package.Exercicio2",
		".nivel2package.Exercicio3",
		".nivel2package.Exercicio4",
		".nivel2package.Exercicio5",
		".nivel2package.Exercicio6",
		".nivel2package.Exercicio7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
