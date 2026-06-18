package matematica.intermediario.expressaoalgebrica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 4;
		Racional[] coeficientes = new Racional[size];
		String op1 = Algebra.sinalPlusMinus();
		String op2 = Algebra.sinal();
		String op3 = Algebra.sinalPlusMinus();
		String exp = "( A " + op1 + " B ) " + op2 + " ( C " + op3 + " D )";
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
		gerarAlternativas(resultado);

		String expSubs = exp;
		for(int i = 0; i < coeficientes.length; i++)
			expSubs = expSubs.replace("" + (char)(65 + i), coeficientes[i].toString());
		expSubs = expSubs.replace("*", " \\times ").replace("/", " \\div ")
				.replace("(", "\\left(").replace(")", "\\right)");
		int g1 = (int) (op1.equals("+") ? coeficientes[0].numerador + coeficientes[1].numerador
				: coeficientes[0].numerador - coeficientes[1].numerador);
		int g2 = (int) (op3.equals("+") ? coeficientes[2].numerador + coeficientes[3].numerador
				: coeficientes[2].numerador - coeficientes[3].numerador);
		String g1Str = g1 < 0 ? "\\left(" + g1 + "\\right)" : "" + g1;
		String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
		String step2 = g1Str + " " + Algebra.converter(op2) + " " + g2Str;
		String res = "Substituindo na expressão: \\(\\\\\\)";
		res += "\\(" + expSubs + " = \\\\ ";
		res += "" + step2 + " = " + resultado.toStringLatex() + "\\)";
		setResolucao(res);
	}
}
