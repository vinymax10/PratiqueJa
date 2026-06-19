package matematica.intermediario.circulo;

import matematica.GeradorExercicio;

public class CirculoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Exercicio1",
		".nivel1package.Exercicio2",
		".nivel1package.Exercicio3",
		".nivel1package.Exercicio4",
		".nivel1package.Exercicio5",
		".nivel1package.Exercicio6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
