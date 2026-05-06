package matematica.basico.expressoesnumericas.nivel3package;

import auxiliar.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;


public class Expressao13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao13(int indice)
	{
		super(indice);

		int size = 6;
		ExpressaoExt expressao;
		Racional resultado = null;

//		gerando os coeficientes;
		Racional[] coeficientes = new Racional[size];
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String ope1 = Algebra.sinalMenosDiv();
		String ope2 = Algebra.sinalPlusMinus();
		String ope3 = "/";
		String ope4 = Algebra.sinalMenosDiv();
		String ope5 = Algebra.sinalPlusMinus();

		String exp = "(A" + ope1 + "(B" + ope2 + "C))" + ope3 + "(D" + ope4 + "(E" + ope5 + "F))";

//		-------------------------		
		String exp2 = "(A" + ope4 + "(B" + ope5 + "C))";

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
//		-------------------------		

		textLatex = "\\dfrac{" + coeficientes[0] + ope1 + "(" + coeficientes[1] + ope2 + coeficientes[2] + ")}{" + coeficientes[3] + ope4 + "("
		+ coeficientes[4] + ope5 + coeficientes[5] + ")}";

		textLatex = textLatex.replace("*", "\\times");

		try
		{
			expressao = new ExpressaoExt(exp, coeficientes);
			resultado = expressao.calcular();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		resultadoCorreto = "" + resultado.toString();
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 1000; i++)
			new Expressao13(1);
	}
}
