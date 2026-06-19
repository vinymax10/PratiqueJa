package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.CincoTermos1",
		".nivel3package.Comparar",
		".nivel3package.Contextualizada",
		".nivel3package.MissingTermo",
		".nivel3package.Parenteses",
		".nivel3package.QuatroTermos",
		".nivel3package.SomaDoisTermos",
		".nivel3package.TresTermos"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
