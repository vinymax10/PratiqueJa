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
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
