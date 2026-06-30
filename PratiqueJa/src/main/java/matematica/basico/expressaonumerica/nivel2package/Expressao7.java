package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// A op1 [(B op2 C) op3 D] — colchete com parêntese interno à esquerda
public class Expressao7 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op2 = opPM();
		String op3 = opPM();
		String op1 = opPMM();

		int b = 2 + rand.nextInt(8);
		int c = 1 + rand.nextInt(7);
		if(op2.equals("-") && c > b) { int t = b; b = c; c = t; }
		int p1 = computar(b, c, op2);

		int d = op3.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(p1, d, op3);

		int a;
		if(op1.equals("*"))       a = 2 + rand.nextInt(5);
		else if(op1.equals("-"))  a = p2 + rand.nextInt(8) + 1;
		else                      a = 2 + rand.nextInt(18);

		int result = computar(a, p2, op1);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String par = "\\left(" + b + " " + t2 + " " + c + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + a + " " + t1 + " [" + par + " " + t3 + " " + d + "] = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " " + t1 + " [" + par + " " + t3 + " " + d + "] = \\\\" +
			"& " + a + " " + t1 + " [" + p1 + " " + t3 + " " + d + "] = \\\\" +
			"& " + a + " " + t1 + " " + p2 + " = \\mathbf{" + result + "}\\end{aligned}\\)"
		);
	}
}
