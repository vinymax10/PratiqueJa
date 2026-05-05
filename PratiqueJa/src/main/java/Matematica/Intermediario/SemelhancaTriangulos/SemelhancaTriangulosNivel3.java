package Matematica.Intermediario.SemelhancaTriangulos;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class SemelhancaTriangulosNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SemelhancaTriangulosNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(6);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Exercicio" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SemelhancaTriangulosNivel3()
	{
	}

	public static void main(String[] args)
	{
//		new Image7(1,true,"semelhancaTriangulos.PNG");
	}
}
