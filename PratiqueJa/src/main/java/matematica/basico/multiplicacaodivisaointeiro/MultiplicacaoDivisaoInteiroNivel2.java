package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Multiplicacao",
		".nivel2package.Divisao",
		".nivel2package.TresFatores",
		".nivel2package.Distributiva",
		".nivel2package.Contextualizada",
		".nivel2package.Mult2",
		".nivel2package.Div2",
		".nivel2package.TresFatores2",
		".nivel2package.QuatroFatores",
		".nivel2package.PotenciaNegativo",
		".nivel2package.IdentificarSinal",
		".nivel2package.Distributiva2",
		".nivel2package.MissingFator",
		".nivel2package.ProvaReal",
		".nivel2package.Problema",
		".nivel2package.Problema2",
		".nivel2package.Mult3",
		".nivel2package.Div3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
