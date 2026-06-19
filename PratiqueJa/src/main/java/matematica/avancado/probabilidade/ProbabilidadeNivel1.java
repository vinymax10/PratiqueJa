package matematica.avancado.probabilidade;

import matematica.GeradorExercicio;

public class ProbabilidadeNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Exercicio3",
		".nivel1package.Exercicio4",
		".nivel1package.Exercicio5",
		".nivel1package.Exercicio6",
		".nivel1package.Exercicio7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
