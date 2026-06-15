package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.SomaDoisTermos",
		".nivel1package.ValorAbsoluto",
		".nivel1package.Oposto",
		".nivel1package.ElementoNeutro",
		".nivel1package.SinaisIguais",
		".nivel1package.SinaisDiferentes",
		".nivel1package.SubtracaoOposto",
		".nivel1package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
