package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Comparar1",
		".nivel1package.Contextualizada",
		".nivel1package.ElementoNeutro",
		".nivel1package.MissingTermo1",
		".nivel1package.Oposto",
		".nivel1package.Parenteses1",
		".nivel1package.Problema1",
		".nivel1package.SinaisDiferentes",
		".nivel1package.SinaisIguais",
		".nivel1package.Soma2",
		".nivel1package.SomaDoisTermos",
		".nivel1package.SubtracaoOposto",
		".nivel1package.TresTermos1",
		".nivel1package.ValorAbsoluto"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
