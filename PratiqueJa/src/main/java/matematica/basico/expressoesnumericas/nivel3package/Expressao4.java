package matematica.basico.expressoesnumericas.nivel3package;

import auxiliar.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;


public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int indice)
	{
		super(indice);

		int size = 6;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = "/";
		String ope3 = Algebra.sinalMenosDiv();
		String ope4 = Algebra.sinalPlusMinus();
		String ope5 = "/";

		String exp;

		exp = "((A" + ope1 + "B)" + ope2 + "C)" + ope3 + "((D" + ope4 + "E)" + ope5 + "F)";

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

		textLatex = "\\dfrac{" + coeficientes[0] + ope1 + coeficientes[1] + "}{" + coeficientes[2] + "}" + ope3 + "\\dfrac{" + coeficientes[3] + ope4
		+ coeficientes[4] + "}{" + coeficientes[5] + "}";
		textLatex = textLatex.replace("*", "\\times");

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

		resultadoCorreto = "" + resultado.toString();
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 1000; i++)
			new Expressao4(1);
	}
}
