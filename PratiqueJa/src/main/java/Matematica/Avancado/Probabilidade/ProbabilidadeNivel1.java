package Matematica.Avancado.Probabilidade;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class ProbabilidadeNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ProbabilidadeNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(2);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Probabilidade" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ProbabilidadeNivel1()
	{
	}

	public static void main(String[] args)
	{
		new ProbabilidadeNivel1(1);
	}
}
