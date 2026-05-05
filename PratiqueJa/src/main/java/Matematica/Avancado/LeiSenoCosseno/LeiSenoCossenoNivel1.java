package Matematica.Avancado.LeiSenoCosseno;

import java.lang.reflect.InvocationTargetException;

import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class LeiSenoCossenoNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public LeiSenoCossenoNivel1(int index)
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

	public LeiSenoCossenoNivel1()
	{
	}

}
