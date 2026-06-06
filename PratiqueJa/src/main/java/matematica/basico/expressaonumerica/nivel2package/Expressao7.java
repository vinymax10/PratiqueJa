package matematica.basico.expressaonumerica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 4;
		ExpressaoExt expressao;
		Racional resultado = null;

		Racional[] coeficientes = new Racional[size];
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String ope1 = Algebra.sinal();
		String ope2 = Algebra.sinalPlusMinus();
		String ope3 = Algebra.sinalMenosDiv();

		String exp, expLatex;
		String exp2 = "(A" + ope2 + "B)" + ope3 + "C";

		exp = "A" + ope1 + "((B" + ope2 + "C)" + ope3 + "D)";

		if(ope1.equals("/"))
		{
			expLatex = "A" + ope1 + "(B" + ope2 + "C)" + ope3 + "D";

			Racional[] coeficientes2 = new Racional[size - 1];

			do
			{
				for(int i = 0; i < coeficientes2.length; i++)
					coeficientes2[i] = new Racional(1 + rand.nextInt(20));

				try
				{
					expressao = new ExpressaoExt(exp2, coeficientes2);
					resultado = expressao.calcular();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			while(resultado.numerador == 0);

			for(int i = 0; i < coeficientes2.length; i++)
				coeficientes[i + 1] = coeficientes2[i];
		}
		else
			expLatex = "A" + ope1 + "[(B" + ope2 + "C)" + ope3 + "D]";

		String texto = Algebra.gerarTextLatexEN(expLatex, coeficientes);

		try
		{
			expressao = new ExpressaoExt(exp, coeficientes);
			resultado = expressao.calcular();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
	}
}
