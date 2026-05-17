package matematica.basico.areaperimetro;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class AreaPerimetroNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public AreaPerimetroNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(20);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public AreaPerimetroNivel3()
	{

	}

}
