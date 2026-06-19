package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Contextualizada",
		".nivel2package.Distributiva",
		".nivel2package.Div2",
		".nivel2package.Divisao",
		".nivel2package.IdentificarSinal",
		".nivel2package.MissingFator",
		".nivel2package.Mult2",
		".nivel2package.Multiplicacao",
		".nivel2package.PotenciaNegativo",
		".nivel2package.ProvaReal",
		".nivel2package.QuatroFatores",
		".nivel2package.TresFatores"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
