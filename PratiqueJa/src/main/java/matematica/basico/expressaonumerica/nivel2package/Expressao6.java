package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// [(A op1 B) op2 C] op3 D — colchete com parêntese, sem segundo grupo
public class Expressao6 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPM();
		String op3 = opPMM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c = op2.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(p1, c, op2);

		int d = op3.equals("*") ? 2 + rand.nextInt(5) : 2 + rand.nextInt(18);
		int result = computar(p2, d, op3);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String par = "\\left(" + a + " " + t1 + " " + b + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\([" + par + " " + t2 + " " + c + "] " + t3 + " " + d + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& [" + par + " " + t2 + " " + c + "] " + t3 + " " + d + " = \\\\" +
			"& [" + p1 + " " + t2 + " " + c + "] " + t3 + " " + d + " = \\\\" +
			"& " + p2 + " " + t3 + " " + d + " = " + result + "\\end{aligned}\\)"
		);
	}
}
