package Matematica.Basico.ExpressoesNumericas.Nivel3Package;

import Auxiliar.Algebra;
import Matematica.ExpressaoExt;
import Matematica.Racional;
import Modelo.Matematica.Conta;

public class Expressao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao6(int indice)
	{
		super(indice);

		int size = 4;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalMenosDiv();
		String ope2 = Algebra.sinalPlusMinus();
		String ope3 = "/";

		String exp;

		exp = "A" + ope1 + "((B" + ope2 + "C)" + ope3 + "D)";

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

		textLatex = "" + coeficientes[0] + ope1 + "\\dfrac{" + coeficientes[1] + ope2 + coeficientes[2] + "}{" + coeficientes[3] + "}";
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
			new Expressao6(1);
	}
}
