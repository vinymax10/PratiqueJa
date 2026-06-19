package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;

public class DivisibilidadeNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Divisibilidade2",
		".nivel1package.Divisibilidade3",
		".nivel1package.Divisibilidade5",
		".nivel1package.Divisibilidade10",
		".nivel1package.EhDivisor2",
		".nivel1package.Mc2",
		".nivel1package.Nao2",
		".nivel1package.Resto2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
