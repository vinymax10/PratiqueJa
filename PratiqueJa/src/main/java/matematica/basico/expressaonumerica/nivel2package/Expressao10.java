package matematica.basico.expressaonumerica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 6;
		Racional[] coeficientes = new Racional[size];

		String exp = "(( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinalMenosDiv() + " ( C " + Algebra.sinalPlusMinus() + " D )) / ( E "
		+ Algebra.sinalPlusMinus() + " F )";

		String expLatex = "( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinalMenosDiv() + " ( C " + Algebra.sinalPlusMinus() + " D ) / ( E "
		+ Algebra.sinalPlusMinus() + " F )";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[4].equals(coeficientes[5]))
			coeficientes[5] = new Racional(1 + rand.nextInt(20));

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
