package Matematica.Intermediario.SemelhancaTriangulos;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class SemelhancaTriangulosNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SemelhancaTriangulosNivel1(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel1Package.Exercicio" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SemelhancaTriangulosNivel1()
	{
	}

	public static void main(String[] args)
	{
//		new Image9(1,true,"semelhancaTriangulos.PNG");
	}

}
