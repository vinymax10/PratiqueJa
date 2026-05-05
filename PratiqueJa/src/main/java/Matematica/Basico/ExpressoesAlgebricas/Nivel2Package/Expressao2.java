package Matematica.Basico.ExpressoesAlgebricas.Nivel2Package;

import Auxiliar.Algebra;
import Matematica.ExpressaoExt;
import Matematica.Racional;
import Modelo.Matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int indice)
	{
		super(indice);

		int size = 3;
		Racional[] coeficientes = new Racional[size];
		String exp = "( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinal() + " C";
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

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
		new Expressao2(1);
	}
}
