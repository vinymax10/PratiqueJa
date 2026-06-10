package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// [(A op1 B) op2 C] op3 (D op4 E) — colchete com parêntese interno à esquerda
public class Expressao5 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();
		String op3 = opPMM();
		String op4 = opPM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c = op2.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(p1, c, op2);

		int d = 2 + rand.nextInt(8);
		int e = 1 + rand.nextInt(7);
		if(op4.equals("-") && e > d) { int t = d; d = e; e = t; }
		int p3 = computar(d, e, op4);

		int result = computar(p2, p3, op3);

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String t4   = opTex(op4);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + d + " " + t4 + " " + e + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\([" + par1 + " " + t2 + " " + c + "] " + t3 + " " + par2 + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& [" + par1 + " " + t2 + " " + c + "] " + t3 + " " + par2 + " = \\\\" +
			"& [" + p1 + " " + t2 + " " + c + "] " + t3 + " " + p3 + " = \\\\" +
			"& " + p2 + " " + t3 + " " + p3 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
