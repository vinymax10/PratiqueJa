package matematica.basico.divisibilidade;



import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class DivisibilidadeNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisibilidadeNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(4);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.Divisibilidade" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public DivisibilidadeNivel3()
	{
	}
}
