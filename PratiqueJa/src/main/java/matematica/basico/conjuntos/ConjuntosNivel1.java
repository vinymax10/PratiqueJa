package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

/**
 * Conjuntos (nível 1) — dispatcher: sorteia um tipo e delega para a folha
 * correspondente em {@code nivel1package}.
 */
public class ConjuntosNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(12);
		delegar(instanciar(".nivel1package.Exercicio" + tipo));
	}
}
