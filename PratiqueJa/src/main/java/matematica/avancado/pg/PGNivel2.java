package matematica.avancado.pg;

import java.lang.reflect.InvocationTargetException;

import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class PGNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public PGNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Expressao" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public PGNivel2()
	{

	}
	
	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto) || respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

	public static void main(String[] args)
	{
//		new Image5(1,true,"anguloInscritoCircunferencia.PNG");
	}

}
