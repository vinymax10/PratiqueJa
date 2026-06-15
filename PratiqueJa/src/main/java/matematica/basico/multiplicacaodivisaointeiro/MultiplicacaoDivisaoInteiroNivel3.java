package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Multiplicacao",
		".nivel3package.Divisao",
		".nivel3package.TresFatores",
		".nivel3package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
