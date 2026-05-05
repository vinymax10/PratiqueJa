package Matematica.Intermediario.Radiciacao;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class RadiciacaoNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RadiciacaoNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(5);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Radiciacao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RadiciacaoNivel3()
	{
	}
}
