package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.ColunaSoma",
		".nivel3package.TresParcelas",
		".nivel3package.Contextualizada",
		".nivel3package.Verificacao",
		".nivel3package.ParcelaMissing",
		".nivel3package.ProblemaDoisPassos",
		".nivel3package.Coluna2",
		".nivel3package.Coluna3",
		".nivel3package.QuatroParcelas1",
		".nivel3package.QuatroParcelas2",
		".nivel3package.TresParcelas2",
		".nivel3package.Problema",
		".nivel3package.Problema2",
		".nivel3package.ParcelaMissing2",
		".nivel3package.Verificacao2",
		".nivel3package.Coluna4",
		".nivel3package.TresParcelas3",
		".nivel3package.QuatroParcelas3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
