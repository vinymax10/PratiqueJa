package matematica.intermediario.semelhancaangulos;

import java.lang.reflect.InvocationTargetException;



import modelo.matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class SemelhancaAngulosNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SemelhancaAngulosNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 6 + rand.nextInt(1);
			clone((Conta) Class.forName(this.getClass().getPackage().getName() + ".nivel3package.Exercicio" + tipo)
			.getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
		| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public SemelhancaAngulosNivel3()
	{
	}

	public boolean isCorreta()
	{
		return respostaAluno.trim().equals(resultadoCorreto)
		|| respostaAluno.trim().equals(resultadoCorreto.replaceAll("°", ""));
	}

}
