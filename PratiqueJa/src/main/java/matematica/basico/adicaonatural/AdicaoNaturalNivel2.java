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
		".nivel2package.ProblemaDoisPassos"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
