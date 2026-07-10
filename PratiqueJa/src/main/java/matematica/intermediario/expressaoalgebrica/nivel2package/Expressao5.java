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
		String op1 = Algebra.sinalMenosDiv();
		String op2 = Algebra.sinalPlusMinus();
		String op3 = Algebra.sinalMenosDiv();
		String op4 = Algebra.sinalPlusMinus();
		String exp = "A " + op1 + " ( B " + op2 + " C ) " + op3 + " ( D " + op4 + " E )";
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
		gerarAlternativas(resultado);

		String expSubs = exp;
		for(int i = 0; i < coeficientes.length; i++)
			expSubs = expSubs.replace("" + (char)(65 + i), coeficientes[i].toString());
		expSubs = expSubs.replace("*", " \\times ").replace("/", " \\div ")
				.replace("(", "\\left(").replace(")", "\\right)");
		int g1 = (int) (op2.equals("+") ? coeficientes[1].numerador + coeficientes[2].numerador
				: coeficientes[1].numerador - coeficientes[2].numerador);
		int g2 = (int) (op4.equals("+") ? coeficientes[3].numerador + coeficientes[4].numerador
				: coeficientes[3].numerador - coeficientes[4].numerador);
		String g1Str = g1 < 0 ? "\\left(" + g1 + "\\right)" : "" + g1;
		String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
		String step2 = coeficientes[0].numerador + " " + Algebra.converter(op1) + " " + g1Str
				+ " " + Algebra.converter(op3) + " " + g2Str;
		addResolucao("Substituindo na expressão:");
		addResolucao("\\(" + expSubs + " =\\)");
		addResolucao("\\(" + step2 + " = \\mathbf{" + resultado.toStringLatex() + "}\\)");
	}
}
