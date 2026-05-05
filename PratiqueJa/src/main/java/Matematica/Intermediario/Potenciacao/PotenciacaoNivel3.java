package Matematica.Intermediario.Potenciacao;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class PotenciacaoNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public PotenciacaoNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(7);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Potenciacao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public PotenciacaoNivel3()
	{
	}
}
