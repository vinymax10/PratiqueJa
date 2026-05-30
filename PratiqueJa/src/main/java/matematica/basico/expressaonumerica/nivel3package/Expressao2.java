package matematica.basico.expressaonumerica.nivel3package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int indice)
	{
		super(indice);

		int size = 4;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = "/";
		String ope3 = Algebra.sinalMenosDiv();

		String exp;

		exp = "((A" + ope1 + "B)" + ope2 + "C)" + ope3 + "D";

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

		textLatex = "\\dfrac{" + coeficientes[0] + ope1 + coeficientes[1] + "}{" + coeficientes[2] + "}" + ope3 + coeficientes[3];
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
			new Expressao2(1);
	}
}
