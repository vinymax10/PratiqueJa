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
		".nivel1package.Multiplos10",
		".nivel1package.Coluna1",
		".nivel1package.Tabuada1",
		".nivel1package.Tabuada2",
		".nivel1package.TresParcelas2",
		".nivel1package.ParcelaMissing2",
		".nivel1package.Verificacao2",
		".nivel1package.Problema1",
		".nivel1package.Problema2",
		".nivel1package.Coluna2",
		".nivel1package.TresParcelas3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
