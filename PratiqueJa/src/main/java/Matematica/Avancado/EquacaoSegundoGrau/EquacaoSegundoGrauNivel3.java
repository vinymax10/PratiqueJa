package Matematica.Avancado.EquacaoSegundoGrau;

import java.lang.reflect.InvocationTargetException;



import Modelo.Matematica.Conta;

import jakarta.persistence.Entity;

@Entity
public class EquacaoSegundoGrauNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public EquacaoSegundoGrauNivel3(int index)
	{
		super(index);

		try
		{
			int tipo = 1 + rand.nextInt(11);
			clone(
			(Conta) Class.forName(this.getClass().getPackage().getName() + ".Nivel3Package.Image" + tipo).getConstructor(Integer.TYPE).newInstance(index));
		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public EquacaoSegundoGrauNivel3()
	{

	}

	public boolean isCorreta()
	{
		if(respostaAluno.trim().equals(resultadoCorreto))
			return true;
		else
		{
			String termo1Aluno = "", termo2Aluno = "";
			String respostaAlunoNew = respostaAluno;
			if(respostaAlunoNew.charAt(0) != '+' && respostaAlunoNew.charAt(0) != '-')
				respostaAlunoNew = "+" + respostaAlunoNew;

			int index = 0;
			do
				termo1Aluno += respostaAlunoNew.charAt(index++);
			while(index < respostaAlunoNew.length() && respostaAlunoNew.charAt(index) != '+' && respostaAlunoNew.charAt(index) != '-');

			while(index < respostaAlunoNew.length())
				termo2Aluno += respostaAlunoNew.charAt(index++);

//			--------------------------------------------
			String termo1Correto = "", termo2Correto = "";
			String resultadoCorretoNew = resultadoCorreto;
			if(resultadoCorretoNew.charAt(0) != '+' && resultadoCorretoNew.charAt(0) != '-')
				resultadoCorretoNew = "+" + resultadoCorretoNew;

			index = 0;
			do
				termo1Correto += resultadoCorretoNew.charAt(index++);
			while(index < resultadoCorretoNew.length() && resultadoCorretoNew.charAt(index) != '+' && resultadoCorretoNew.charAt(index) != '-');

			while(index < resultadoCorretoNew.length())
				termo2Correto += resultadoCorretoNew.charAt(index++);

			if(termo1Aluno.equals(termo1Correto) && termo2Aluno.equals(termo2Correto) || termo1Aluno.equals(termo2Correto) && termo2Aluno.equals(termo1Correto))
				return true;

		}
		return false;
	}
}
