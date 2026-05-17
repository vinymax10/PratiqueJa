package matematica.basico.regratres;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class RegraTresNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RegraTresNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel1package.RegraTres" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RegraTresNivel1()
	{
	}

	public static void main(String[] args)
	{
		new RegraTresNivel1(1);
	}
}
