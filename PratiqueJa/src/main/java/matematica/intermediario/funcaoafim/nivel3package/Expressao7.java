package matematica.intermediario.funcaoafim.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.funcaoafim.ResolucaoFuncaoAfim;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1, y1, x2, y2;
		do
		{
			x1 = -(2 + rand.nextInt(4));
			y1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));
			x2 = 2 + rand.nextInt(4);
			y2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));
		}
		while(y1 == y2);

		Racional a = new Racional(y2 - y1).div(new Racional(x2 - x1));
		a.fatoracao(2);

		Racional ax1r = a.mult(new Racional(x1));
		ax1r.fatoracao(2);

		Racional bRac = new Racional(y1).minus(ax1r);
		bRac.fatoracao(2);

		int v = 1 + rand.nextInt(4);
		if(v == x2) v++;

		Racional avr = a.mult(new Racional(v));
		avr.fatoracao(2);

		Racional fvr = avr.add(bRac);
		fvr.fatoracao(2);

		String aStr = a.toStringLatex();
		String bStr = bRac.toStringLatex();
		String avStr = avr.toStringLatex();
		String fvStr = fvr.toStringLatex();
		String x1Disp = x1 < 0 ? "\\left(" + x1 + "\\right)" : "" + x1;

		addParagrafo("A função afim \\(f(x) = ax + b\\) passa pelos pontos "
			+ "\\(A = (" + x1 + ",\\ " + y1 + ")\\) e \\(B = (" + x2 + ",\\ " + y2 + ")\\). "
			+ "Calcule \\(f(" + v + ")\\).");

		String res = "Coeficiente angular \\(a\\): \\(\\\\\\)";
		res += "\\(" + ResolucaoFuncaoAfim.resolucao(x1, y1, x2, y2) + "\\) \\(\\\\\\)";

		res += "Coeficiente linear \\(b\\) usando \\(A = (" + x1 + ",\\ " + y1 + ")\\): \\(\\\\\\)";
		res += "\\(" + y1 + " = " + aStr + " \\cdot " + x1Disp + " + b\\) \\(\\\\\\)";

		if(ax1r.numerador < 0)
		{
			Racional posAx1 = new Racional(-ax1r.numerador, ax1r.denominador);
			res += "\\(b = " + y1 + " + " + posAx1.toStringLatex() + " = \\mathbf{" + bStr + "}\\) \\(\\\\\\)";
		}
		else
		{
			res += "\\(b = " + y1 + " - " + ax1r.toStringLatex() + " = \\mathbf{" + bStr + "}\\) \\(\\\\\\)";
		}

		String aLeading = a.toString().equals("1") ? "" : (a.toString().equals("-1") ? "-" : aStr);
		res += "Logo \\(f(x) = " + aLeading + "x" + bTermStr(bRac) + "\\): \\(\\\\\\)";
		res += "\\(f(" + v + ") = " + aStr + " \\cdot " + v + bTermStr(bRac)
			+ " = " + avStr + bTermStr(bRac) + " = \\mathbf{" + fvStr + "}\\)";

		gerarAlternativas(fvr);
		setResolucao(res);
	}

	private static String bTermStr(Racional b)
	{
		if(b.numerador == 0) return "";
		if(b.numerador < 0)
		{
			Racional abs = new Racional(-b.numerador, b.denominador);
			return " - " + abs.toStringLatex();
		}
		return " + " + b.toStringLatex();
	}
}
