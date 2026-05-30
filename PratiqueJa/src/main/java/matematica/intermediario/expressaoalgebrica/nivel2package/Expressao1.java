package matematica.intermediario.expressaoalgebrica.nivel2package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int indice)
	{
		super(indice);

		int size = 3;
		Racional[] coeficientes = new Racional[size];
		String exp = "A " + Algebra.sinal() + " ( B " + Algebra.sinalPlusMinus() + " C )";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[1].equals(coeficientes[2]))
			coeficientes[2] = new Racional(1 + rand.nextInt(20));

		int posX[] = new int[1];
		posX[0] = rand.nextInt(size);

		textLatex = Algebra.gerarTextLatex(exp, posX, coeficientes);

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
			new Expressao1(1);
		}
	}
}
