package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.SomaDoisTermos",
		".nivel3package.TresTermos",
		".nivel3package.QuatroTermos",
		".nivel3package.Contextualizada",
		".nivel3package.QuatroTermos2",
		".nivel3package.CincoTermos1",
		".nivel3package.Parenteses",
		".nivel3package.Comparar",
		".nivel3package.Problema",
		".nivel3package.CincoTermos2",
		".nivel3package.QuatroTermos3",
		".nivel3package.TresTermos2",
		".nivel3package.Parenteses2",
		".nivel3package.MissingTermo",
		".nivel3package.CincoTermos3",
		".nivel3package.Problema2",
		".nivel3package.QuatroTermos4",
		".nivel3package.Comparar2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
