package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.SomaDoisTermos",
		".nivel2package.SubtracaoOposto",
		".nivel2package.TresTermos",
		".nivel2package.MissingTermo",
		".nivel2package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
