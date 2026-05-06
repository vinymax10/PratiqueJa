package matematica.basico.racionais;



import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class RacionaisNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RacionaisNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Racionais" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public RacionaisNivel1()
	{
	}

	public static void main(String[] args)
	{
		new RacionaisNivel1(1);

	}

}
