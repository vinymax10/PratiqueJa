package Matematica.Intermediario.RazoesTrigonometricas;

import java.lang.reflect.InvocationTargetException;

import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class RazoesTrigonometricasNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RazoesTrigonometricasNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(12);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RazoesTrigonometricasNivel2()
	{
	}

}
