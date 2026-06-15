package matematica.basico.adicaosubtracaointeiro;

import matematica.GeradorExercicio;

public class AdicaoSubtracaoInteiroNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.SomaDoisTermos",
		".nivel3package.TresTermos",
		".nivel3package.QuatroTermos",
		".nivel3package.Contextualizada"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
