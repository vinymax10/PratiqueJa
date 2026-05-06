package matematica.basico.expressoesalgebricas.nivel2package;

import auxiliar.Algebra;
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
		String exp = "( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinal() + " ( C " + Algebra.sinalPlusMinus() + " D )";
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[2].equals(coeficientes[3]))
			coeficientes[3] = new Racional(1 + rand.nextInt(20));

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
			new Expressao3(1);
		}
	}
}
