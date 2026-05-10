package matematica.basico.expressoesnumericas.nivel2package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;


public class Expressao10 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao10(int indice)
	{
		super(indice);

		int size = 6;
		Racional[] coeficientes = new Racional[size];

		String exp = "(( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinalMenosDiv() + " ( C " + Algebra.sinalPlusMinus() + " D )) / ( E "
		+ Algebra.sinalPlusMinus() + " F )";

		String expLatex = "( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinalMenosDiv() + " ( C " + Algebra.sinalPlusMinus() + " D ) / ( E "
		+ Algebra.sinalPlusMinus() + " F )";

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

//		evitando coeficientes iguais

		while(coeficientes[4].equals(coeficientes[5]))
			coeficientes[5] = new Racional(1 + rand.nextInt(20));
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
			new Expressao10(1);
	}
}
