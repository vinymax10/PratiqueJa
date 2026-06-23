package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// dfrac{(A op1 B) op2 C}{D op3 (E op4 F)} — fração: numerador com paren esquerda, denom com paren direita
public class Expressao11 extends AgrupadorExercicio
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
			d = 2 + rand.nextInt(8);
			e = 2 + rand.nextInt(8); f = 1 + rand.nextInt(7);
			if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
			if(op4.equals("-") && f > e) { int t = e; e = f; f = t; }
			p1  = computar(a, b, op1);
			num = computar(p1, c, op2);
			p2  = computar(e, f, op4);
			den = computar(d, p2, op3);
		}
		while(den <= 0 || num % den != 0);

		int result = num / den;

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String t4   = opTex(op4);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + e + " " + t4 + " " + f + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\dfrac{" + par1 + " " + t2 + " " + c + "}{" + d + " " + t3 + " " + par2 + "} = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\dfrac{" + par1 + " " + t2 + " " + c + "}{" + d + " " + t3 + " " + par2 + "} = \\\\" +
			"& \\dfrac{" + p1 + " " + t2 + " " + c + "}{" + d + " " + t3 + " " + p2 + "} = \\\\" +
			"& \\dfrac{" + num + "}{" + den + "} = " + result +
			"\\end{aligned}\\)"
		);
	}
}
