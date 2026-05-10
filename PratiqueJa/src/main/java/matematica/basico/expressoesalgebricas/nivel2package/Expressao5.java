package matematica.basico.expressoesalgebricas.nivel2package;

import util.Algebra;
import matematica.ExpressaoExt;
import matematica.Racional;
import modelo.matematica.Conta;

public class Expressao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao5(int indice)
	{
		super(indice);

		int size = 5;
		Racional[] coeficientes = new Racional[size];
		String exp = "A " + Algebra.sinalMenosDiv() + " ( B " + Algebra.sinalPlusMinus() + " C ) " + Algebra.sinal() + " ( D " + Algebra.sinalPlusMinus()
		+ " E )";
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

//		gerando os coeficientes;
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));
//		-------------------------		

//		gerando posicoes de x;
		int posX[] = new int[2];
		posX[0] = rand.nextInt(3);
		posX[1] = 3 + rand.nextInt(2);
		coeficientes[posX[1]].numerador = coeficientes[posX[0]].numerador;
//		-------------------------		

//		evitando coeficientes iguais

		while(coeficientes[3].equals(coeficientes[4]))
		{
			if(posX[1] == 3)
				coeficientes[4] = new Racional(1 + rand.nextInt(20));
			else
				coeficientes[3] = new Racional(1 + rand.nextInt(20));
		}
//		-------------------------		

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
			new Expressao5(1);
	}
}
