package matematica.intermediario.expressaoalgebrica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 3;
		Racional[] coeficientes = new Racional[size];
		String op1 = Algebra.sinal();
		String op2 = Algebra.sinalPlusMinus();
		String exp = "A " + op1 + " ( B " + op2 + " C )";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		while(coeficientes[1].equals(coeficientes[2]))
			coeficientes[2] = new Racional(1 + rand.nextInt(20));

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
		gerarAlternativas(resultado);

		String expSubs = exp;
		for(int i = 0; i < coeficientes.length; i++)
			expSubs = expSubs.replace("" + (char)(65 + i), coeficientes[i].toString());
		expSubs = expSubs.replace("*", " \\times ").replace("/", " \\div ")
				.replace("(", "\\left(").replace(")", "\\right)");
		int g = (int) (op2.equals("+") ? coeficientes[1].numerador + coeficientes[2].numerador
				: coeficientes[1].numerador - coeficientes[2].numerador);
		String gStr = g < 0 ? "\\left(" + g + "\\right)" : "" + g;
		String step2 = coeficientes[0].numerador + " " + Algebra.converter(op1) + " " + gStr;
		addResolucao("Substituindo na expressão:");
		addResolucao("\\(" + expSubs + " =\\)");
		addResolucao("\\(" + step2 + " = " + resultado.toStringLatex() + "\\)");
	}
}
