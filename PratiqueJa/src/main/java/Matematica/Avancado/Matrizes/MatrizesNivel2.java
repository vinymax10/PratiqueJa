package Matematica.Avancado.Matrizes;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class MatrizesNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MatrizesNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(5);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Matrizes" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public MatrizesNivel2()
	{
	}

	public static void main(String[] args)
	{
		new MatrizesNivel2(1);
	}
}
