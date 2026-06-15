package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.MultipliPor10",
		".nivel1package.FatorFaltante",
		".nivel1package.Comutativa",
		".nivel1package.Contextualizada",
		".nivel1package.Distributiva",
		".nivel1package.TruqueMental",
		".nivel1package.Sequencia",
		".nivel1package.ElementoEspecial",
		".nivel1package.Associativa"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
