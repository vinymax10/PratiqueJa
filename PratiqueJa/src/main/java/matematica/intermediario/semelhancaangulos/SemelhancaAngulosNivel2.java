package matematica.intermediario.semelhancaangulos;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SemelhancaAngulosNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SemelhancaAngulosNivel2(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(3);
			clone((Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel2Package.Exercicio" + tipo)
			.getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
		| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SemelhancaAngulosNivel2()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto)
		|| respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

	public static void main(String[] args)
	{
//		new Image4(1,true,"duasRetas.PNG");
	}
}
