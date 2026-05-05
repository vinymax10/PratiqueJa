package Matematica.Basico.RegraTres;

import java.lang.reflect.InvocationTargetException;
import Modelo.Matematica.Conta;
import jakarta.persistence.Entity;

@Entity
public class RegraTresNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RegraTresNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.RegraTres" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RegraTresNivel3()
	{
	}

	public static void main(String[] args)
	{
		new RegraTresNivel3(1);
	}
}
