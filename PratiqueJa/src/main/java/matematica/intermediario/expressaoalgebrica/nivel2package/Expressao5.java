package matematica.intermediario.expressaoalgebrica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 5;
		Racional[] coeficientes = new Racional[size];
		String exp = "A " + Algebra.sinalMenosDiv() + " ( B " + Algebra.sinalPlusMinus() + " C ) " + Algebra.sinal() + " ( D " + Algebra.sinalPlusMinus()
		+ " E )";
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		int posX[] = new int[2];
		posX[0] = rand.nextInt(3);
		posX[1] = 3 + rand.nextInt(2);
		coeficientes[posX[1]].numerador = coeficientes[posX[0]].numerador;

		while(coeficientes[3].equals(coeficientes[4]))
		{
			if(posX[1] == 3)
				coeficientes[4] = new Racional(1 + rand.nextInt(20));
			else
				coeficientes[3] = new Racional(1 + rand.nextInt(20));
		}

		String texto = Algebra.gerarTextLatex(exp, posX, coeficientes);

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

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
	}
}
