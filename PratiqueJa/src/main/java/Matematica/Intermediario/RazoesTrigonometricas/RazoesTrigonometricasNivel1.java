package Matematica.Intermediario.RazoesTrigonometricas;

import java.lang.reflect.InvocationTargetException;

import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class RazoesTrigonometricasNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RazoesTrigonometricasNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(6);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RazoesTrigonometricasNivel1()
	{
	}

}
