package matematica.intermediario.sistemaequacoes;

import matematica.GeradorExercicio;

public class SistemaEquacoesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel1package.Sistema" + tipo));
	}
}
