package Matematica.Basico.AreaPerimetro;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class AreaPerimetroNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public AreaPerimetroNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(38);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public AreaPerimetroNivel2()
	{

	}
}
