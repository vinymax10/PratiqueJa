package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Calculo",
		".nivel3package.Contextualizada",
		".nivel3package.Distributiva",
		".nivel3package.Estimativa",
		".nivel3package.Expressao",
		".nivel3package.FatorFaltante",
		".nivel3package.Problema",
		".nivel3package.ProblemaMultiPassos",
		".nivel3package.TresFatores"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
