package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.ColunaSoma",
		".nivel2package.TresParcelas",
		".nivel2package.Contextualizada",
		".nivel2package.Verificacao",
		".nivel2package.ParcelaMissing",
		".nivel2package.ProblemaDoisPassos",
		".nivel2package.Coluna2",
		".nivel2package.Coluna3",
		".nivel2package.TresParcelas2",
		".nivel2package.QuatroParcelas1",
		".nivel2package.ParcelaMissing2",
		".nivel2package.Problema",
		".nivel2package.Problema2",
		".nivel2package.Verificacao2",
		".nivel2package.Coluna4",
		".nivel2package.TresParcelas3",
		".nivel2package.SomaMental",
		".nivel2package.QuatroParcelas2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
