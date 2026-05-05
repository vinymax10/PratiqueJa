package Matematica.Basico.MmcMdc;

import java.lang.reflect.InvocationTargetException;
import Modelo.Matematica.Conta;
import javax.persistence.Entity;

@Entity
public class MmcMdcNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MmcMdcNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.MmcMdc" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public MmcMdcNivel3()
	{
	}

	public static void main(String[] args)
	{
		new MmcMdcNivel3(1);
	}
}
