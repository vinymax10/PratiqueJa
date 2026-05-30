package matematica.intermediario.jurosdesconto;

import java.lang.reflect.InvocationTargetException;
import modelo.matematica.Conta;
import jakarta.persistence.Entity;

@Entity
public class JurosDescontoNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDescontoNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.JurosDesconto" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public JurosDescontoNivel3()
	{
	}

	public static void main(String[] args)
	{
		new JurosDescontoNivel3(1);
	}
}
