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
		".nivel1package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
