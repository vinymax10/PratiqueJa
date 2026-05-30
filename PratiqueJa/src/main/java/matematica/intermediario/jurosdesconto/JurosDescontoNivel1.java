package matematica.intermediario.jurosdesconto;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class JurosDescontoNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDescontoNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(1);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel1package.JurosDesconto" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public JurosDescontoNivel1()
	{
	}

	public static void main(String[] args)
	{
		new JurosDescontoNivel1(1);
	}
}
