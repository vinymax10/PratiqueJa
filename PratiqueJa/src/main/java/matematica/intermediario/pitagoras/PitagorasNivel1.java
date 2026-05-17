package matematica.intermediario.pitagoras;

import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class PitagorasNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public PitagorasNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel1package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public PitagorasNivel1()
	{
	}

}
