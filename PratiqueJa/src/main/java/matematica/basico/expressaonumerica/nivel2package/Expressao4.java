package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// [A op1 (B op2 C)] op3 (D op4 E) — colchete com parêntese interno
public class Expressao4 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op2 = opPM();
		String op4 = opPM();
		String op1 = opPM();
		String op3 = opPMM();

		int b = 2 + rand.nextInt(8);
		int c = 1 + rand.nextInt(7);
		if(op2.equals("-") && c > b) { int t = b; b = c; c = t; }
		int p1 = computar(b, c, op2);

		int a = op1.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(a, p1, op1);

		int d = 2 + rand.nextInt(8);
		int e = 1 + rand.nextInt(7);
		if(op4.equals("-") && e > d) { int t = d; d = e; e = t; }
		int p3 = computar(d, e, op4);

		int result = computar(p2, p3, op3);

		String t1    = opTex(op1);
		String t2    = opTex(op2);
		String t3    = opTex(op3);
		String t4    = opTex(op4);
		String inner = "\\left(" + b + " " + t2 + " " + c + "\\right)";
		String par2  = "\\left(" + d + " " + t4 + " " + e + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\([" + a + " " + t1 + " " + inner + "] " + t3 + " " + par2 + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& [" + a + " " + t1 + " " + inner + "] " + t3 + " " + par2 + " = \\\\" +
			"& [" + a + " " + t1 + " " + p1 + "] " + t3 + " " + p3 + " = \\\\" +
			"& " + p2 + " " + t3 + " " + p3 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
