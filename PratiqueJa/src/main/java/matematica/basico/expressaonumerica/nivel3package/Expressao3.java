package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// dfrac{A op1 B}{C} op2 (D op3 E) — fração com parêntese à direita
public class Expressao3 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();
		String op3 = opPM();

		int a, b, c, p1;
		do
		{
			c  = 2 + rand.nextInt(8);
			a  = 2 + rand.nextInt(18);
			b  = 1 + rand.nextInt(17);
			if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
			p1 = computar(a, b, op1);
		}
		while(p1 <= 0 || p1 % c != 0);

		int frac1 = p1 / c;

		int d = 2 + rand.nextInt(8);
		int e = 1 + rand.nextInt(7);
		if(op3.equals("-") && e > d) { int t = d; d = e; e = t; }
		int p2 = computar(d, e, op3);

		int result = computar(frac1, p2, op2);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String par = "\\left(" + d + " " + t3 + " " + e + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " " + par + " = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " " + par + " = \\\\" +
			"& \\dfrac{" + p1 + "}{" + c + "} " + t2 + " " + p2 + " = \\\\" +
			"& " + frac1 + " " + t2 + " " + p2 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
