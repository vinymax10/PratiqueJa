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
		".nivel1package.Exercicio6", ".nivel1package.Exercicio7", ".nivel1package.Exercicio8",
		".nivel1package.Exercicio9", ".nivel1package.Exercicio10", ".nivel1package.Exercicio11",
		".nivel1package.Exercicio12", ".nivel1package.Exercicio13", ".nivel1package.Exercicio14",
		".nivel1package.Exercicio15", ".nivel1package.Exercicio16", ".nivel1package.Exercicio17",
		".nivel1package.Exercicio18",
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
