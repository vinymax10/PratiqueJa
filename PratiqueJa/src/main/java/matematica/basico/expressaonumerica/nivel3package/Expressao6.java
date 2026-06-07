package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// A op1 dfrac{B op2 C}{D} — número operando com fração
public class Expressao6 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();

		int b, c, d, p1;
		do
		{
			d  = 2 + rand.nextInt(8);
			b  = 2 + rand.nextInt(18);
			c  = 1 + rand.nextInt(17);
			if(op2.equals("-") && c > b) { int t = b; b = c; c = t; }
			p1 = computar(b, c, op2);
		}
		while(p1 <= 0 || p1 % d != 0);
		int frac1 = p1 / d;

		int a      = 2 + rand.nextInt(18);
		int result = computar(a, frac1, op1);

		String t1 = opTex(op1);
		String t2 = opTex(op2);

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + a + " " + t1 + " \\dfrac{" + b + " " + t2 + " " + c + "}{" + d + "} = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " " + t1 + " \\dfrac{" + b + " " + t2 + " " + c + "}{" + d + "} = \\\\" +
			"& " + a + " " + t1 + " \\dfrac{" + p1 + "}{" + d + "} = \\\\" +
			"& " + a + " " + t1 + " " + frac1 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
