package matematica.avancado.probabilidade;

import matematica.GeradorExercicio;

public class ProbabilidadeNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Probabilidade1",  // retiradas sem reposição (dependentes)
		".nivel3package.Exercicio2",      // combinatória: 2 bolas simultâneas
		".nivel3package.Exercicio3",      // combinatória: 3 bolas simultâneas
		".nivel3package.Exercicio4",      // Teorema de Bayes: teste diagnóstico
		".nivel3package.Exercicio5",      // distribuição binomial: P(X=k)
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
