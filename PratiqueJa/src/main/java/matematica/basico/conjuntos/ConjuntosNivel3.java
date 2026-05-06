package matematica.basico.conjuntos;

import java.lang.reflect.InvocationTargetException;
import modelo.matematica.Conta;
import jakarta.persistence.Entity;

@Entity
public class ConjuntosNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ConjuntosNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Conjuntos" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ConjuntosNivel3()
	{
	}

	public static void main(String[] args)
	{
		new ConjuntosNivel3(1);
	}
}
