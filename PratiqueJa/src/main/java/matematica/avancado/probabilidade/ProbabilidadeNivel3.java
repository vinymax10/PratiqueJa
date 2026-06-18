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
		".nivel3package.Exercicio6", ".nivel3package.Exercicio7", ".nivel3package.Exercicio8",
		".nivel3package.Exercicio9", ".nivel3package.Exercicio10", ".nivel3package.Exercicio11",
		".nivel3package.Exercicio12", ".nivel3package.Exercicio13", ".nivel3package.Exercicio14",
		".nivel3package.Exercicio15", ".nivel3package.Exercicio16", ".nivel3package.Exercicio17",
		".nivel3package.Exercicio18",
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
