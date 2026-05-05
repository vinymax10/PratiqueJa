package Matematica.Avancado.PA;

import java.lang.reflect.InvocationTargetException;

import Modelo.Matematica.Conta;

import javax.persistence.Entity;

@Entity
public class PANivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public PANivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(10);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Expressao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public PANivel3()
	{

	}
	
	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto) || respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

	public static void main(String[] args)
	{
//		new Image8(1,true,"anguloInscritoCircunferencia.PNG");
	}
}
