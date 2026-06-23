package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// (A op1 B) op2 [(C op3 D) op4 E] — parêntese esquerda + colchete com parêntese interno à esquerda
public class Expressao9 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op3 = opPM();
		String op4 = opPM();
		String op2 = opPMM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c = 2 + rand.nextInt(8);
		int d = 1 + rand.nextInt(7);
		if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }
		int p2 = computar(c, d, op3);

		int e = op4.equals("-") ? p2 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p3 = computar(p2, e, op4);

		int result = computar(p1, p3, op2);

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String t4   = opTex(op4);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + c + " " + t3 + " " + d + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + par1 + " " + t2 + " [" + par2 + " " + t4 + " " + e + "] = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + par1 + " " + t2 + " [" + par2 + " " + t4 + " " + e + "] = \\\\" +
			"& " + p1 + " " + t2 + " [" + p2 + " " + t4 + " " + e + "] = \\\\" +
			"& " + p1 + " " + t2 + " " + p3 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
