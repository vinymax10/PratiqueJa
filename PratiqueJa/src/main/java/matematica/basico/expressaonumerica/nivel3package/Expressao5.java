package matematica.basico.expressaonumerica.nivel3package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 5;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = Algebra.sinalMenosDiv();
		String ope3 = Algebra.sinalPlusMinus();
		String ope4 = "/";

		String exp;

		exp = "(A" + ope1 + "B)" + ope2 + "((C" + ope3 + "D)" + ope4 + "E)";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String texto = "(" + coeficientes[0] + ope1 + coeficientes[1] + ")" + ope2 + "\\dfrac{" + coeficientes[2] + ope3 + coeficientes[3] + "}{" + coeficientes[4]
		+ "}";
		texto = texto.replace("*", "\\times");

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
