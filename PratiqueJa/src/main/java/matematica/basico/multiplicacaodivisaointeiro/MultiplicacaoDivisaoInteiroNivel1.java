package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Contextualizada",
		".nivel1package.Distributiva1",
		".nivel1package.Div1",
		".nivel1package.ElementoNeutro",
		".nivel1package.IdentificarSinal",
		".nivel1package.MissingFator",
		".nivel1package.Mult1",
		".nivel1package.MultDiv",
		".nivel1package.PadraoNegativo",
		".nivel1package.PotenciaNegativo",
		".nivel1package.Problema1",
		".nivel1package.ProvaReal"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
