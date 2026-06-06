package matematica.basico.expressaonumerica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 4;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = Algebra.sinalMenosDiv();
		String ope3 = Algebra.sinal();

		String exp, expLatex;

		exp = "((A" + ope1 + "B)" + ope2 + "C)" + ope3 + "D";

		if(ope3.equals("/"))
			expLatex = "(A" + ope1 + "B)" + ope2 + "C" + ope3 + "D";
		else
			expLatex = "[(A" + ope1 + "B)" + ope2 + "C]" + ope3 + "D";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String texto = Algebra.gerarTextLatexEN(expLatex, coeficientes);

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

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
	}
}
