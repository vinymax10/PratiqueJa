package matematica.avancado.probabilidade;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class ProbabilidadeNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ProbabilidadeNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel2package.Probabilidade" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ProbabilidadeNivel2()
	{
	}

	public static void main(String[] args)
	{
		new ProbabilidadeNivel2(1);
	}
}
