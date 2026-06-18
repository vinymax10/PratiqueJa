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
		".nivel3package.ProblemaMultiPassos",
		".nivel3package.Calculo2",
		".nivel3package.Calculo3",
		".nivel3package.TresFatores2",
		".nivel3package.TresFatores3",
		".nivel3package.Expressao2",
		".nivel3package.Expressao3",
		".nivel3package.Por10c",
		".nivel3package.FatorFaltante2",
		".nivel3package.Problema",
		".nivel3package.Problema2",
		".nivel3package.Distributiva"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
