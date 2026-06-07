package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// dfrac{(A op1 B) op2 C}{(D op3 E) op4 F} — fração com expressões no numerador e denominador
public class Expressao10 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();
		String op3 = opPM();
		String op4 = opPM();

		int a, b, c, d, e, f, p1, p2, num, den;
		do
		{
			a = 2 + rand.nextInt(8); b = 1 + rand.nextInt(7);
			c = 2 + rand.nextInt(8);
			d = 2 + rand.nextInt(8); e = 1 + rand.nextInt(7);
			f = 2 + rand.nextInt(8);
			if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
			if(op3.equals("-") && e > d) { int t = d; d = e; e = t; }
			p1  = computar(a, b, op1);
			num = computar(p1, c, op2);
			p2  = computar(d, e, op3);
			den = computar(p2, f, op4);
		}
		while(den <= 0 || num % den != 0);

		int result = num / den;

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String t4   = opTex(op4);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + d + " " + t3 + " " + e + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\dfrac{" + par1 + " " + t2 + " " + c + "}{" + par2 + " " + t4 + " " + f + "} = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& \\dfrac{" + par1 + " " + t2 + " " + c + "}{" + par2 + " " + t4 + " " + f + "} = \\\\" +
			"& \\dfrac{" + p1 + " " + t2 + " " + c + "}{" + p2 + " " + t4 + " " + f + "} = \\\\" +
			"& \\dfrac{" + num + "}{" + den + "} = " + result +
			"\\end{aligned}\\)"
		);
	}
}
