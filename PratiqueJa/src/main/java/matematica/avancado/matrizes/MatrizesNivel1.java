package matematica.avancado.matrizes;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class MatrizesNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MatrizesNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Matrizes" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public MatrizesNivel1()
	{
	}

	public static void main(String[] args)
	{
		new MatrizesNivel1(1);
	}
}
