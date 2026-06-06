package matematica.basico.expressaonumerica.nivel3package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 6;
		ExpressaoExt expressao;
		Racional resultado = null;

		Racional[] coeficientes = new Racional[size];
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String ope1 = Algebra.sinalMenosDiv();
		String ope2 = Algebra.sinalPlusMinus();
		String ope3 = "/";
		String ope4 = Algebra.sinalPlusMinus();
		String ope5 = Algebra.sinalMenosDiv();

		String exp = "(A" + ope1 + "(B" + ope2 + "C))" + ope3 + "((D" + ope4 + "E)" + ope5 + "F)";

		String exp2 = "((A" + ope4 + "B)" + ope5 + "C)";

		Racional[] coeficientes2 = new Racional[3];
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
			coeficientes[i + 3] = coeficientes2[i];

		String texto = "\\dfrac{" + coeficientes[0] + ope1 + "(" + coeficientes[1] + ope2 + coeficientes[2] + ")}{(" + coeficientes[3] + ope4 + coeficientes[4]
		+ ")" + ope5 + coeficientes[5] + "}";

		texto = texto.replace("*", "\\times");

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
