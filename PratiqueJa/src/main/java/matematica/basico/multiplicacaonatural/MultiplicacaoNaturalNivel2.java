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
		".nivel2package.ComparaSemCalcular"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
