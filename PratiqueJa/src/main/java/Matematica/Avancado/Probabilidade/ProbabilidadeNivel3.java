package Matematica.Avancado.Probabilidade;

import java.lang.reflect.InvocationTargetException;
import Modelo.Matematica.Conta;
import javax.persistence.Entity;

@Entity
public class ProbabilidadeNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ProbabilidadeNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Probabilidade" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ProbabilidadeNivel3()
	{
	}

	public static void main(String[] args)
	{
		new ProbabilidadeNivel3(1);
	}
}
