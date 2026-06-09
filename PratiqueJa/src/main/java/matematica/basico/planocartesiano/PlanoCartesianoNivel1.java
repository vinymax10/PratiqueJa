package matematica.basico.planocartesiano;

import matematica.GeradorExercicio;

public class PlanoCartesianoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
