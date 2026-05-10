package matematica.basico.expressoesalgebricas.nivel1package;

import jakarta.persistence.Transient;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;


public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	@Transient
	int posX;

	@Transient
	Racional[] coeficientes;

	public Expressao1(int index)
	{
		super(index);
		int num;
		boolean temDiv = false;

//		definindo os nomes e coeficiente
		int size = 2 + rand.nextInt(3);
		coeficientes = new Racional[size];
		String nomes[] = new String[size];
		for(int i = 0; i < size; i++)
		{
			num = 1 + rand.nextInt(20);
			coeficientes[i] = new Racional(num);
			nomes[i] = "" + (char) (65 + i);
		}

//		definindo os nomes os operadores
		String operadoresList[] = { "+", "-", "*", "/" };
		String operadores[] = new String[size - 1];

		int pos;
		for(int i = 0; i < operadores.length; i++)
		{
			if(!temDiv)
				pos = rand.nextInt(operadoresList.length);
			else
				pos = rand.nextInt(operadoresList.length - 1);

			operadores[i] = operadoresList[pos];
			if(pos == 3)
			{
				temDiv = true;
				coeficientes[i].numerador = (1 + rand.nextInt(10)) * coeficientes[i + 1].numerador;
			}
		}

		posX = rand.nextInt(size);

		textLatex = "";
		String exp = nomes[0];
		textLatex += "" + getCoeficiente(0);

		for(int i = 0; i < size - 1; i++)
		{
			exp += operadores[i] + nomes[i + 1];
			textLatex += " " + Algebra.converter(operadores[i]) + " " + getCoeficiente(i + 1);
		}

		textLatex += "=";

		ExpressaoExt expressao;
		Racional resultado = null;
		try
		{
			expressao = new ExpressaoExt(exp, coeficientes);
			resultado = expressao.calcular();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		textLatex = "\\begin{align}&" + "\\text{Para}~ x = " + coeficientes[posX] + ",\\\\ &" + textLatex + "\\end{align}";

		resultadoCorreto = "" + resultado.toString();
	}

	private String getCoeficiente(int index)
	{
		if(index == posX)
			return "x";
		else
			return "" + coeficientes[index];
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
