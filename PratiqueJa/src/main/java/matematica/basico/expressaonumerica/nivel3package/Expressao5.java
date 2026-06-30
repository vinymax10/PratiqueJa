package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// (A op1 B) op2 dfrac{C op3 D}{E} — parêntese operando com fração
public class Expressao5 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();
		String op3 = opPM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c, d, e, p2;
		do
		{
			e  = 2 + rand.nextInt(8);
			c  = 2 + rand.nextInt(18);
			d  = 1 + rand.nextInt(17);
			if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }
			p2 = computar(c, d, op3);
		}
		while(p2 <= 0 || p2 % e != 0);
		int frac2 = p2 / e;

		int result = computar(p1, frac2, op2);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String par = "\\left(" + a + " " + t1 + " " + b + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + par + " " + t2 + " \\dfrac{" + c + " " + t3 + " " + d + "}{" + e + "} = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + par + " " + t2 + " \\dfrac{" + c + " " + t3 + " " + d + "}{" + e + "} = \\\\" +
			"& " + p1 + " " + t2 + " \\dfrac{" + p2 + "}{" + e + "} = \\\\" +
			"& " + p1 + " " + t2 + " " + frac2 + " = \\mathbf{" + result + "}\\end{aligned}\\)"
		);
	}
}
