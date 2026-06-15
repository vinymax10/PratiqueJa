package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Multiplicacao",
		".nivel2package.Divisao",
		".nivel2package.TresFatores",
		".nivel2package.Distributiva",
		".nivel2package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
