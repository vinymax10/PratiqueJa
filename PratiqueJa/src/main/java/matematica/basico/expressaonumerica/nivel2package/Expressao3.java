package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// (A op1 B) op2 (C op3 D) — dois pares de parênteses
public class Expressao3 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPMM();
		String op3 = opPM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		int c = 2 + rand.nextInt(8);
		int d = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }

		int p1     = computar(a, b, op1);
		int p2     = computar(c, d, op3);
		int result = computar(p1, p2, op2);

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + c + " " + t3 + " " + d + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + par1 + " " + t2 + " " + par2 + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + par1 + " " + t2 + " " + par2 + " = \\\\" +
			"& " + p1 + " " + t2 + " " + p2 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
