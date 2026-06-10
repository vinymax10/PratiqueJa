package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {"Radiciacao", "Radiciacao2", "Radiciacao3", "Radiciacao4"};
		delegar(instanciar(".nivel1package." + tipos[rand.nextInt(tipos.length)]));
	}
}
