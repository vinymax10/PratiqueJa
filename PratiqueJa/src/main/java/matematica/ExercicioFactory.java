package matematica;

import modelo.matematica.Exercicio;

/**
 * Ponto único de instanciação de exercícios a partir do nome da classe
 * (ExercicioPadrao.getClasse()). Substitui o
 * {@code Class.forName(classe).getConstructor(int).newInstance(i)} espalhado
 * pelos call sites.
 *
 * <p>Suporta os dois mundos durante a migração:
 * <ul>
 *   <li>Novo: classe é um {@link GeradorExercicio} (POJO) → instancia e chama gerar().</li>
 *   <li>Legado: classe ainda {@code extends Exercicio} com construtor (int).</li>
 * </ul>
 */
public final class ExercicioFactory
{
	private ExercicioFactory()
	{
	}

	public static Exercicio gerar(String classe, int indice)
	{
		try
		{
			Class<?> clazz = Class.forName(classe);

			if(GeradorExercicio.class.isAssignableFrom(clazz))
				return ((GeradorExercicio) clazz.getDeclaredConstructor().newInstance()).gerar();

			// Legado: gerador ainda é subclasse de Exercicio com construtor (int).
			return (Exercicio) clazz.getConstructor(Integer.TYPE).newInstance(indice);
		}
		catch(ReflectiveOperationException e)
		{
			throw new IllegalStateException("Não foi possível gerar o exercício: " + classe, e);
		}
	}
}
