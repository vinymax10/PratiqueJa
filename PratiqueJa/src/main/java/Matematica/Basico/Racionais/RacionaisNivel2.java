package Matematica.Basico.Racionais;



import java.lang.reflect.InvocationTargetException;

import jakarta.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class RacionaisNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RacionaisNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Racionais" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public RacionaisNivel2()
	{
	}

	public static void main(String[] args)
	{
		new RacionaisNivel2(1);
	}

}
