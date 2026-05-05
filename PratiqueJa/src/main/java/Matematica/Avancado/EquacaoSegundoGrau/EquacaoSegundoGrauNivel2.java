package Matematica.Avancado.EquacaoSegundoGrau;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class EquacaoSegundoGrauNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public EquacaoSegundoGrauNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(6);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Expressao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public EquacaoSegundoGrauNivel2()
	{
	}

}
