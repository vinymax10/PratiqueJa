package matematica.basico.expressaonumerica.nivel2package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao6(int indice)
	{
		super(indice);

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

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

		textLatex = Algebra.gerarTextLatexEN(expLatex, coeficientes);

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
