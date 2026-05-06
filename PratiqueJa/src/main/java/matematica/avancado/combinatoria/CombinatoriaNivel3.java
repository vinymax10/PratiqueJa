package matematica.avancado.combinatoria;

import java.lang.reflect.InvocationTargetException;
import modelo.matematica.Conta;
import jakarta.persistence.Entity;

@Entity
public class CombinatoriaNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public CombinatoriaNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Combinatoria" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public CombinatoriaNivel3()
	{
	}

	public static void main(String[] args)
	{
		new CombinatoriaNivel3(1);
	}
}
