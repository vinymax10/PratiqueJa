package matematica.intermediario.razoestrigonometricas;

import java.lang.reflect.InvocationTargetException;

import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class RazoesTrigonometricasNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public RazoesTrigonometricasNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(12);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public RazoesTrigonometricasNivel3()
	{
	}

}
