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
		".nivel1package.Associativa",
		".nivel1package.Tabuada1",
		".nivel1package.Tabuada2",
		".nivel1package.Por10a",
		".nivel1package.FatorFaltante2",
		".nivel1package.Problema1",
		".nivel1package.Distributiva2",
		".nivel1package.Calculo1",
		".nivel1package.TresFatores1",
		".nivel1package.Problema2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
