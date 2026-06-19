package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

public class AdicaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Coluna2",
		".nivel3package.ColunaSoma",
		".nivel3package.Contextualizada",
		".nivel3package.ParcelaMissing",
		".nivel3package.Problema",
		".nivel3package.ProblemaDoisPassos",
		".nivel3package.QuatroParcelas1",
		".nivel3package.TresParcelas",
		".nivel3package.Verificacao"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
