package matematica.basico.expressaonumerica.nivel2package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int indice)
	{
		super(indice);

		int size = 4;
		Racional[] coeficientes = new Racional[size];

		String ope1 = Algebra.sinalPlusMinus();
		String ope2 = Algebra.sinal();
		String ope3 = Algebra.sinalPlusMinus();
		String exp, expLatex;

		exp = "(A" + ope1 + "B)" + ope2 + "(C" + ope3 + "D)";

		if(ope2.equals("/"))
			expLatex = "A" + ope1 + "B" + ope2 + "C" + ope3 + "D";
		else
			expLatex = exp;

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[2].equals(coeficientes[3]))
			coeficientes[3] = new Racional(1 + rand.nextInt(20));

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
		{
			new Expressao3(1);
		}
	}
}
