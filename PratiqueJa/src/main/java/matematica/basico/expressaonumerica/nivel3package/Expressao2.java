package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// dfrac{A op1 B}{C} op2 D — fração simples operando com um número
public class Expressao2 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();

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

		int frac1  = p1 / c;
		int d      = 2 + rand.nextInt(18);
		int result = computar(frac1, d, op2);

		String t1 = opTex(op1);
		String t2 = opTex(op2);

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " " + d + " = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\dfrac{" + a + " " + t1 + " " + b + "}{" + c + "} " + t2 + " " + d + " = \\\\" +
			"& \\dfrac{" + p1 + "}{" + c + "} " + t2 + " " + d + " = \\\\" +
			"& " + frac1 + " " + t2 + " " + d + " = \\mathbf{" + result + "}\\end{aligned}\\)"
		);
	}
}
