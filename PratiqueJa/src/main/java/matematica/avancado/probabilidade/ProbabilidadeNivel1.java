package matematica.avancado.probabilidade;

import matematica.GeradorExercicio;

public class ProbabilidadeNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Probabilidade1",  // probabilidade clássica P(A)=n(A)/n(Ω)
		".nivel1package.Probabilidade2",  // inverso: dado P(A), encontrar n(A) ou n(Ω)
		".nivel1package.Exercicio3",      // complementar: P(A^c) = 1 - P(A)
		".nivel1package.Exercicio4",      // eventos exclusivos: P(A∪B) = P(A)+P(B)
		".nivel1package.Exercicio5",      // produto independente: P(A∩B) = P(A)·P(B)
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
