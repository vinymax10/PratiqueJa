package matematica.avancado.matrizes;

import java.lang.reflect.InvocationTargetException;
import modelo.matematica.Conta;
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
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.Matrizes" + tipo).getConstructor(Integer.TYPE).newInstance(index));
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
