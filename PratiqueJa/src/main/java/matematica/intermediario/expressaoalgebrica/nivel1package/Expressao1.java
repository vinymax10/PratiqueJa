package matematica.intermediario.expressaoalgebrica.nivel1package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao1 extends GeradorExercicio
{
	private int posX;
	private Racional[] coeficientes;

	@Override
	protected void construir()
	{
		int num;
		boolean temDiv = false;

		int size = 2 + rand.nextInt(3);
		coeficientes = new Racional[size];
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

		posX = rand.nextInt(size);

		String texto = "";
		String exp = nomes[0];
		texto += "" + getCoeficiente(0);

		for(int i = 0; i < size - 1; i++)
		{
			exp += operadores[i] + nomes[i + 1];
			texto += " " + Algebra.converter(operadores[i]) + " " + getCoeficiente(i + 1);
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

		addParagrafo("Calcule o valor da expressão algébrica para \\(x = " + coeficientes[posX] + "\\):");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultado);

		String textoValores = "" + coeficientes[0];
		for(int i = 0; i < operadores.length; i++)
			textoValores += " " + Algebra.converter(operadores[i]) + " " + coeficientes[i + 1];

		boolean hasMulDiv = false;
		for(String op : operadores)
			if(op.equals("*") || op.equals("/")) { hasMulDiv = true; break; }

		addResolucao("Substituindo \\(x = " + coeficientes[posX] + "\\) na expressão:");
		if(hasMulDiv)
		{
			StringBuilder inter = new StringBuilder();
			long running = coeficientes[0].numerador;
			for(int i = 0; i < operadores.length; i++)
			{
				if(operadores[i].equals("*"))      running *= coeficientes[i + 1].numerador;
				else if(operadores[i].equals("/")) running /= coeficientes[i + 1].numerador;
				else
				{
					inter.append(running).append(" ").append(Algebra.converter(operadores[i])).append(" ");
					running = coeficientes[i + 1].numerador;
				}
			}
			inter.append(running);
			addResolucao("\\(" + textoValores + " =\\)");
			addResolucao("\\(" + inter + " = \\mathbf{" + resultado.toStringLatex() + "}\\)");
		}
		else
		{
			addResolucao("\\(" + textoValores + " = \\mathbf{" + resultado.toStringLatex() + "}\\)");
		}
	}

	private String getCoeficiente(int index)
	{
		if(index == posX)
			return "x";
		else
			return "" + coeficientes[index];
	}
}
