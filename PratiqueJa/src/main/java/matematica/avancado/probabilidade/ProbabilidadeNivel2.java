package matematica.avancado.probabilidade;

import matematica.GeradorExercicio;

public class ProbabilidadeNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Probabilidade1",  // probabilidade condicional P(B|A)
		".nivel2package.Probabilidade2",  // união/interseção P(A∪B), P(A-B)
		".nivel2package.Exercicio3",      // ao menos um com moeda: 1-(1/2)^n
		".nivel2package.Exercicio4",      // ao menos um com dado:  1-(5/6)^n
		".nivel2package.Exercicio5",      // regra da soma geral: baralho P(A∪B)
		".nivel2package.Exercicio6", ".nivel2package.Exercicio7", ".nivel2package.Exercicio8",
		".nivel2package.Exercicio9", ".nivel2package.Exercicio10", ".nivel2package.Exercicio11",
		".nivel2package.Exercicio12", ".nivel2package.Exercicio13", ".nivel2package.Exercicio14",
		".nivel2package.Exercicio15", ".nivel2package.Exercicio16", ".nivel2package.Exercicio17",
		".nivel2package.Exercicio18",
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
