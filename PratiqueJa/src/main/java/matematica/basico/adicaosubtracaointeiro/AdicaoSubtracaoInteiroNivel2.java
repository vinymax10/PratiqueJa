package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Comparar",
		".nivel2package.Contextualizada",
		".nivel2package.MissingTermo",
		".nivel2package.Oposto",
		".nivel2package.Parenteses",
		".nivel2package.QuatroTermos",
		".nivel2package.Soma2",
		".nivel2package.SomaDoisTermos",
		".nivel2package.SubtracaoOposto",
		".nivel2package.TresTermos"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
