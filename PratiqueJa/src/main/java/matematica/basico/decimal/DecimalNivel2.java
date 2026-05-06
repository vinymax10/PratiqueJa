package matematica.basico.decimal;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class DecimalNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DecimalNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(4);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Decimal" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public DecimalNivel2()
	{

	}
}
