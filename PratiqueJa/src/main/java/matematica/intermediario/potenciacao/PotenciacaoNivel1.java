package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String[] tipos = {"Potenciacao", "Potenciacao2", "Potenciacao3"};
		delegar(instanciar(".nivel1package." + tipos[rand.nextInt(tipos.length)]));
	}
}
