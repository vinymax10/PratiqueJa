package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

public class ConjuntosNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Exercicio1",
		".nivel1package.Exercicio2",
		".nivel1package.Exercicio3",
		".nivel1package.Exercicio4",
		".nivel1package.Exercicio5",
		".nivel1package.Exercicio6",
		".nivel1package.Exercicio7",
		".nivel1package.Exercicio8",
		".nivel1package.Exercicio9",
		".nivel1package.Exercicio10",
		".nivel1package.Exercicio11",
		".nivel1package.Exercicio12",
		".nivel1package.Exercicio13",
		".nivel1package.Exercicio14",
		".nivel1package.Exercicio15",
		".nivel1package.Exercicio16"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
