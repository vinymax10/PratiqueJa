package matematica.intermediario.jurosdesconto;

import matematica.GeradorExercicio;

public class JurosDescontoNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.JurosDesconto1",
		".nivel2package.JurosDesconto2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
