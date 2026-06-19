package matematica.avancado.probabilidade;

import matematica.GeradorExercicio;

public class ProbabilidadeNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Exercicio2",
		".nivel3package.Exercicio3",
		".nivel3package.Exercicio4",
		".nivel3package.Exercicio5",
		".nivel3package.Exercicio6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
