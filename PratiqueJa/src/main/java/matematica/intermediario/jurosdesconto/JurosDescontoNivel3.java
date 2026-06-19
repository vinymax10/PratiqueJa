package matematica.intermediario.jurosdesconto;

import matematica.GeradorExercicio;

public class JurosDescontoNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.JurosDesconto1",
		".nivel3package.JurosDesconto2",
		".nivel3package.JurosDesconto3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
