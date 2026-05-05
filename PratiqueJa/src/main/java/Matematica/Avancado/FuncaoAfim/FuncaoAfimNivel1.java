package Matematica.Avancado.FuncaoAfim;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class FuncaoAfimNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public FuncaoAfimNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Expressao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public FuncaoAfimNivel1()
	{

	}

}
