package matematica.basico.expressaonumerica.nivel1package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int num;
		boolean temDiv = false;

		int size = 3 + rand.nextInt(3);
		Racional[] coeficientes = new Racional[size];
		String nomes[] = new String[size];
		for(int i = 0; i < size; i++)
		{
			num = 1 + rand.nextInt(20);
			coeficientes[i] = new Racional(num);
			nomes[i] = "" + (char) (65 + i);
		}

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

		String texto = "";
		String exp = nomes[0];
		texto += "" + coeficientes[0];

		for(int i = 0; i < size - 1; i++)
		{
			exp += operadores[i] + nomes[i + 1];
			texto += " " + Algebra.converter(operadores[i]) + " " + coeficientes[i + 1];
		}

		texto += "=";

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

		texto = texto.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
	}
}
