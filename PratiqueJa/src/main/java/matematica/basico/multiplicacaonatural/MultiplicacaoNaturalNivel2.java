package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Calculo",
		".nivel2package.ComparaSemCalcular",
		".nivel2package.Contextualizada",
		".nivel2package.Distribuicao",
		".nivel2package.Distribuicao2",
		".nivel2package.DobradoMetade",
		".nivel2package.Estimativa",
		".nivel2package.Expressao1",
		".nivel2package.FatorFaltante",
		".nivel2package.Problema",
		".nivel2package.TresFatores1",
		".nivel2package.Verificacao"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
