package matematica.intermediario.expressaoalgebrica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 4;
		Racional[] coeficientes = new Racional[size];
		String exp = "( A " + Algebra.sinalPlusMinus() + " B ) " + Algebra.sinal() + " ( C " + Algebra.sinalPlusMinus() + " D )";
		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[2].equals(coeficientes[3]))
			coeficientes[3] = new Racional(1 + rand.nextInt(20));

		int posX[] = new int[1];
		posX[0] = rand.nextInt(size);

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
