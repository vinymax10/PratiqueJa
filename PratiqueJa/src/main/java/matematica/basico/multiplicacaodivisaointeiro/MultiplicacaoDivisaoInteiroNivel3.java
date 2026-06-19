package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.CincoFatores",
		".nivel3package.Contextualizada",
		".nivel3package.Distributiva",
		".nivel3package.Div",
		".nivel3package.Divisao",
		".nivel3package.IdentificarSinal",
		".nivel3package.MissingFator",
		".nivel3package.Mult",
		".nivel3package.Multiplicacao",
		".nivel3package.ProvaReal",
		".nivel3package.TresFatores"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
