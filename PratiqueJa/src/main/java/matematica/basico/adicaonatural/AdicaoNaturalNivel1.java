package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Associativa",
		".nivel1package.Coluna1",
		".nivel1package.Comutativa",
		".nivel1package.Contextualizada",
		".nivel1package.ElementoNeutro",
		".nivel1package.Multiplos10",
		".nivel1package.ParcelaMissing",
		".nivel1package.Problema1",
		".nivel1package.Tabuada1",
		".nivel1package.TresParcelas",
		".nivel1package.Verificacao"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
