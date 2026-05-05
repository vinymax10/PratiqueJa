package Matematica.Basico.Conjuntos;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class ConjuntosNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public ConjuntosNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(12);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Exercicio" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ConjuntosNivel1()
	{
	}

	public static void main(String[] args)
	{
		new ConjuntosNivel1(1);
	}
}
