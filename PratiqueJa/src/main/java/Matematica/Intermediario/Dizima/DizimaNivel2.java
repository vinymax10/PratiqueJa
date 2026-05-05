package Matematica.Intermediario.Dizima;



import Modelo.Matematica.Conta;

import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import Matematica.Intermediario.Dizima.Nivel2Package.Dizima2;

@Entity
public class DizimaNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DizimaNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Dizima" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public DizimaNivel2()
	{
	}
}
