package matematica.basico.expressoesalgebricas.nivel3package;

import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int indice)
	{
		super(indice);

		int size = 3 + rand.nextInt(3);
		Racional[] coeficientes = new Racional[size];

		for(int i = 0; i < size; i++)
		{
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
			if(rand.nextBoolean())
				coeficientes[i].numerador *= -1;
		}

//		String variavel="x";
		String variavel = "" + (char) (97 + rand.nextInt(26));

		int numX = 1 + rand.nextInt(3);
		int posX[] = new int[numX];
		for(int i = 0; i < posX.length; i++)
			posX[i] = (i * 2) + rand.nextInt(2);

		textLatex = "";
		if(posicaoX(0, posX))
		{
			if(coeficientes[0].numerador != 1 && coeficientes[0].numerador != -1)
				textLatex += "" + coeficientes[0];
			else if(coeficientes[0].numerador == -1)
				textLatex += "-";

			textLatex += variavel;
		}
		else
			textLatex += "" + coeficientes[0];

		for(int i = 1; i < size; i++)
		{
			if(coeficientes[i].numerador >= 0)
				textLatex += "+";

			if(posicaoX(i, posX))
			{
				if(coeficientes[i].numerador != 1 && coeficientes[i].numerador != -1)
					textLatex += "" + coeficientes[i];
				else if(coeficientes[i].numerador == -1)
					textLatex += "-";

				textLatex += variavel;
			}
			else
				textLatex += "" + coeficientes[i];
		}

		Racional x = new Racional(0);
		Racional naoX = new Racional(0);

		for(int i = 0; i < size; i++)
		{
			if(posicaoX(i, posX))
				x = x.add(coeficientes[i]);
			else
				naoX = naoX.add(coeficientes[i]);
		}

		resultadoCorreto = "";
		if(x.numerador != 0)
		{
			if(x.numerador == 1)
				resultadoCorreto += variavel;
			else if(x.numerador == -1)
				resultadoCorreto += "-" + variavel;
			else
				resultadoCorreto += x + variavel;
		}

		if(naoX.numerador > 0 && x.numerador != 0)
			resultadoCorreto += "+";

		if(naoX.numerador != 0 || x.numerador == 0)
			resultadoCorreto += naoX;

	}

	private boolean posicaoX(int index, int posX[])
	{
		for(int i = 0; i < posX.length; i++)
		{
			if(posX[i] == index)
				return true;
		}
		return false;
	}

	public boolean isCorreta()
	{
		resultadoCorreto = "26p+16";
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

	public static void main(String[] args)
	{
		for(int i = 0; i < 1000; i++)
			new Expressao1(1);

//		exp.setRespostaAluno("1");
	}
}
