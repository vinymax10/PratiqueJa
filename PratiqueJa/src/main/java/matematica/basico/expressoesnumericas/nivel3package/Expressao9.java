package matematica.basico.expressoesnumericas.nivel3package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao9(int indice)
	{
		super(indice);

		int size = 5;
		ExpressaoExt expressao;
		Racional resultado = null;

//		gerando os coeficientes;
		Racional[] coeficientes = new Racional[size];
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		String ope1 = Algebra.sinal();
		String ope2 = Algebra.sinalMenosDiv();
		String ope3 = Algebra.sinalPlusMinus();
		String ope4 = Algebra.sinalMenosDiv();

		String exp, expLatex;

		exp = "A" + ope1 + "(B" + ope2 + "((C" + ope3 + "D)" + ope4 + "E))";

		String exp2 = "(A" + ope2 + "((B" + ope3 + "C)" + ope4 + "D))";

		if(ope1.equals("/"))
		{
			expLatex = "A" + ope1 + "B" + ope2 + "[(C" + ope3 + "D)" + ope4 + "E]";

			Racional[] coeficientes2 = new Racional[4];
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
			expLatex = "A" + ope1 + "\\{B" + ope2 + "[(C" + ope3 + "D)" + ope4 + "E]\\}";

//		-------------------------		

//		evitando coeficientes iguais

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
			new Expressao9(1);
	}
}
