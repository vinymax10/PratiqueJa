package Matematica.Basico.ExpressoesNumericas.Nivel2Package;

import Auxiliar.Algebra;
import Matematica.ExpressaoExt;
import Matematica.Racional;
import Modelo.Matematica.Conta;

public class Expressao8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao8(int indice)
	{
		super(indice);

		int size = 5;
		ExpressaoExt expressao;
		Racional resultado = null;

//		gerando os coeficientes;
		Racional[] coeficientes = new Racional[size];
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = Algebra.sinal();
		String ope3 = Algebra.sinalMenosDiv();
		String ope4 = Algebra.sinalPlusMinus();

		String exp = "(A" + ope1 + "B)" + ope2 + "(C" + ope3 + "(D" + ope4 + "E))";

		String exp2 = "A" + ope3 + "(B" + ope4 + "C)";

		String expLatex;
		if(ope2.equals("/"))
		{
			expLatex = "A" + ope1 + "B" + ope2 + "C" + ope3 + "(D" + ope4 + "E)";

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
				coeficientes[i + 2] = coeficientes2[i];
		}
		else
			expLatex = "(A" + ope1 + "B)" + ope2 + "[C" + ope3 + "(D" + ope4 + "E)]";

//		-------------------------		

		textLatex = Algebra.gerarTextLatexEN(expLatex, coeficientes);

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
			new Expressao8(1);
	}
}
