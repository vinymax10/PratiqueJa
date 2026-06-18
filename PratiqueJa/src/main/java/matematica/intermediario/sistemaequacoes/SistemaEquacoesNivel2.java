package matematica.intermediario.sistemaequacoes;

import matematica.GeradorExercicio;

public class SistemaEquacoesNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.Sistema" + tipo));
	}
}
