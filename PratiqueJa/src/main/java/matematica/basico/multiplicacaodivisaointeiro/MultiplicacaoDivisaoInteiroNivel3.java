package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Multiplicacao",
		".nivel3package.Divisao",
		".nivel3package.TresFatores",
		".nivel3package.Contextualizada",
		".nivel3package.QuatroFatores",
		".nivel3package.CincoFatores",
		".nivel3package.TresFatores2",
		".nivel3package.PotenciaNegativo",
		".nivel3package.Distributiva",
		".nivel3package.Problema",
		".nivel3package.Problema2",
		".nivel3package.MissingFator",
		".nivel3package.ProvaReal",
		".nivel3package.QuatroFatores2",
		".nivel3package.CincoFatores2",
		".nivel3package.IdentificarSinal",
		".nivel3package.Mult",
		".nivel3package.Div"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
