package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.MultDiv",
		".nivel1package.IdentificarSinal",
		".nivel1package.ElementoNeutro",
		".nivel1package.ProvaReal",
		".nivel1package.PotenciaNegativo",
		".nivel1package.PadraoNegativo",
		".nivel1package.MissingFator",
		".nivel1package.Contextualizada",
		".nivel1package.Mult1",
		".nivel1package.Div1",
		".nivel1package.IdentificarSinal2",
		".nivel1package.PotenciaNegativo2",
		".nivel1package.MissingFator2",
		".nivel1package.ProvaReal2",
		".nivel1package.Problema1",
		".nivel1package.Mult2",
		".nivel1package.Div2",
		".nivel1package.Distributiva1"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
