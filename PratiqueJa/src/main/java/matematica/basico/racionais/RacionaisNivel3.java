package matematica.basico.racionais;



import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class RacionaisNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RacionaisNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(6);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.Racionais" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public RacionaisNivel3()
	{
	}

	public static void main(String[] args)
	{
		new RacionaisNivel3(1);
	}

}
