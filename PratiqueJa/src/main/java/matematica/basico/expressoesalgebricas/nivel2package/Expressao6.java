package matematica.basico.expressoesalgebricas.nivel2package;

import auxiliar.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao6(int indice)
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

//		gerando posicoes de x;
		int posX[] = new int[2];
		posX[0] = rand.nextInt(4);
		posX[1] = 4 + rand.nextInt(2);
		coeficientes[posX[1]].numerador = coeficientes[posX[0]].numerador;
//		-------------------------		

//		evitando coeficientes iguais

		while(coeficientes[4].equals(coeficientes[5]))
		{
			if(posX[1] == 4)
				coeficientes[5] = new Racional(1 + rand.nextInt(20));
			else
				coeficientes[4] = new Racional(1 + rand.nextInt(20));
		}
//		-------------------------				

		textLatex = Algebra.gerarTextLatex(expLatex, posX, coeficientes);

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
