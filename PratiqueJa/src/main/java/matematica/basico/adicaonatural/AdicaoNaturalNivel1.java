package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Comutativa",
		".nivel1package.ElementoNeutro",
		".nivel1package.TresParcelas",
		".nivel1package.Contextualizada",
		".nivel1package.Verificacao",
		".nivel1package.Associativa",
		".nivel1package.ParcelaMissing",
		".nivel1package.Multiplos10"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
