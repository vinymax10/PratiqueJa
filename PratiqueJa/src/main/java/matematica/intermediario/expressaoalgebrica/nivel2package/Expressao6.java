package matematica.intermediario.expressaoalgebrica.nivel2package;

import matematica.ExpressaoExt;
import matematica.GeradorExercicio;
import matematica.Racional;
import util.Algebra;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 6;
		Racional[] coeficientes = new Racional[size];

		String s1 = Algebra.sinalPlusMinus();
		String s2 = Algebra.sinalMenosDiv();
		String s3 = Algebra.sinalPlusMinus();
		String s4 = Algebra.sinalPlusMinus();

		String exp = "(( A " + s1 + " B ) " + s2 + " ( C " + s3 + " D )) / ( E " + s4 + " F )";
		String expLatex = "( A " + s1 + " B ) " + s2 + " ( C " + s3 + " D ) / ( E " + s4 + " F )";

		for(int i = 0; i < size; i++)
			coeficientes[i] = new Racional(1 + rand.nextInt(20));

		int posX[] = new int[2];
		posX[0] = rand.nextInt(4);
		posX[1] = 4 + rand.nextInt(2);
		coeficientes[posX[1]].numerador = coeficientes[posX[0]].numerador;

		while(coeficientes[4].equals(coeficientes[5]))
		{
			if(posX[1] == 4)
				coeficientes[5] = new Racional(1 + rand.nextInt(20));
			else
				coeficientes[4] = new Racional(1 + rand.nextInt(20));
		}

		String texto = Algebra.gerarTextLatex(expLatex, posX, coeficientes);

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

		String expSubs = expLatex;
		for(int i = 0; i < coeficientes.length; i++)
			expSubs = expSubs.replace("" + (char)(65 + i), coeficientes[i].toString());
		expSubs = expSubs.replace("*", " \\times ");
		String[] partes = expSubs.split("/");
		if(partes.length > 1)
			expSubs = "\\dfrac{" + partes[0].trim().replace("(", "\\left(").replace(")", "\\right)")
					+ "}{" + partes[1].trim().replace("(", "\\left(").replace(")", "\\right)") + "}";
		else
			expSubs = expSubs.replace("(", "\\left(").replace(")", "\\right)");
		int g1 = (int) (s1.equals("+") ? coeficientes[0].numerador + coeficientes[1].numerador
				: coeficientes[0].numerador - coeficientes[1].numerador);
		int g2 = (int) (s3.equals("+") ? coeficientes[2].numerador + coeficientes[3].numerador
				: coeficientes[2].numerador - coeficientes[3].numerador);
		int g3 = (int) (s4.equals("+") ? coeficientes[4].numerador + coeficientes[5].numerador
				: coeficientes[4].numerador - coeficientes[5].numerador);
		String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
		String interNum = g1 + " " + Algebra.converter(s2) + " " + g2Str;
		String step2 = "\\dfrac{" + interNum + "}{" + g3 + "}";
		String res = "Substituindo na expressão: \\(\\\\\\)";
		res += "\\(" + expSubs + " = \\\\ ";
		res += "" + step2 + " = " + resultado.toStringLatex() + "\\)";
		setResolucao(res);
	}
}
