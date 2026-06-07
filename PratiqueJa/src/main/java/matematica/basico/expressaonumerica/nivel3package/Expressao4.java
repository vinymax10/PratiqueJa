package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// dfrac{A op1 B}{C} op2 dfrac{D op3 E}{F} — duas frações
public class Expressao4 extends AgrupadorExercicio
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

		int d, e, f, p2;
		do
		{
			f  = 2 + rand.nextInt(8);
			d  = 2 + rand.nextInt(18);
			e  = 1 + rand.nextInt(17);
			if(op3.equals("-") && e > d) { int t = d; d = e; e = t; }
			p2 = computar(d, e, op3);
		}
		while(p2 <= 0 || p2 % f != 0);
		int frac2 = p2 / f;

		int result = computar(frac1, frac2, op2);

		String t1 = opTex(op1);
		String t2 = opTex(op2);
		String t3 = opTex(op3);

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " \\dfrac{" + d + " " + t3 + " " + e + "}{" + f + "} = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& \\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " \\dfrac{" + d + " " + t3 + " " + e + "}{" + f + "} = \\\\" +
			"& \\dfrac{" + p1 + "}{" + c + "} " + t2 + " \\dfrac{" + p2 + "}{" + f + "} = \\\\" +
			"& " + frac1 + " " + t2 + " " + frac2 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
