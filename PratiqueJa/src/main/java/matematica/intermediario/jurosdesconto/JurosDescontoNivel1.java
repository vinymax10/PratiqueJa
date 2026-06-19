package matematica.intermediario.jurosdesconto;

import matematica.GeradorExercicio;

public class JurosDescontoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.JurosDesconto1",
		".nivel1package.JurosDesconto2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
