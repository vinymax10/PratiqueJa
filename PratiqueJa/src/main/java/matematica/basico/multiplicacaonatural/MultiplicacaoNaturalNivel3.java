package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Calculo",
		".nivel3package.Contextualizada",
		".nivel3package.Estimativa",
		".nivel3package.TresFatores",
		".nivel3package.FatorFaltante",
		".nivel3package.Expressao",
		".nivel3package.ProblemaMultiPassos"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
