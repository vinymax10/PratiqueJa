package matematica.intermediario.equacoes;

import matematica.GeradorExercicio;

public class EquacoesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Equacao" + tipo));
	}
}
