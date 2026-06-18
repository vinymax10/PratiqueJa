package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.SomaDoisTermos",
		".nivel2package.SubtracaoOposto",
		".nivel2package.TresTermos",
		".nivel2package.MissingTermo",
		".nivel2package.Contextualizada",
		".nivel2package.Parenteses",
		".nivel2package.QuatroTermos",
		".nivel2package.Comparar",
		".nivel2package.TresTermos2",
		".nivel2package.Problema",
		".nivel2package.Soma2",
		".nivel2package.MissingTermo2",
		".nivel2package.Parenteses2",
		".nivel2package.TresTermos3",
		".nivel2package.QuatroTermos2",
		".nivel2package.Problema2",
		".nivel2package.Comparar2",
		".nivel2package.Oposto"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
