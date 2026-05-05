package Matematica.Avancado.JurosDesconto;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class JurosDescontoNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDescontoNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.JurosDesconto" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public JurosDescontoNivel2()
	{
	}

	public static void main(String[] args)
	{
		new JurosDescontoNivel2(1);
	}
}
