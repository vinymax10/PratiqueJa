package matematica.intermediario.semelhancatriangulos;

import matematica.GeradorExercicio;

public class SemelhancaTriangulosNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Exercicio1",
		".nivel1package.Exercicio2",
		".nivel1package.Exercicio3",
		".nivel1package.Exercicio4",
		".nivel1package.Exercicio5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
