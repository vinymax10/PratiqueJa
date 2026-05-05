package Matematica.Avancado.Matrizes;

import java.lang.reflect.InvocationTargetException;
import Modelo.Matematica.Conta;
import jakarta.persistence.Entity;

@Entity
public class MatrizesNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MatrizesNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Matrizes" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public MatrizesNivel3()
	{
	}

	public static void main(String[] args)
	{
		new MatrizesNivel3(1);
	}
}
