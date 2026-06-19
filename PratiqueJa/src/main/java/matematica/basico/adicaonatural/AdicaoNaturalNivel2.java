package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Coluna2",
		".nivel2package.ColunaSoma",
		".nivel2package.Contextualizada",
		".nivel2package.ParcelaMissing",
		".nivel2package.Problema",
		".nivel2package.ProblemaDoisPassos",
		".nivel2package.QuatroParcelas1",
		".nivel2package.SomaMental",
		".nivel2package.TresParcelas",
		".nivel2package.Verificacao"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
