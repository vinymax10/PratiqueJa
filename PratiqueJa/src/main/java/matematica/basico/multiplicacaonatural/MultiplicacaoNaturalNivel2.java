package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Calculo",
		".nivel2package.Contextualizada",
		".nivel2package.Estimativa",
		".nivel2package.FatorFaltante",
		".nivel2package.Verificacao",
		".nivel2package.DobradoMetade",
		".nivel2package.Distribuicao",
		".nivel2package.ComparaSemCalcular",
		".nivel2package.Calculo2",
		".nivel2package.Calculo3",
		".nivel2package.Por10b",
		".nivel2package.TresFatores1",
		".nivel2package.FatorFaltante2",
		".nivel2package.Problema",
		".nivel2package.Problema2",
		".nivel2package.Distribuicao2",
		".nivel2package.Expressao1",
		".nivel2package.Calculo4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
