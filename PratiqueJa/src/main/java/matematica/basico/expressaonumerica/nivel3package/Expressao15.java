package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// {[A op1 (B op2 C)] op3 D} op4 E — três níveis com multiplicação no colchete
public class Expressao15 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op2 = opPM();
		String op1 = opPMM();
		String op3 = opPM();
		String op4 = opPM();

		int d = 2 + rand.nextInt(8);
		int e = 2 + rand.nextInt(9);

		int a, b, c;
		if(op1.equals("*"))
		{ a = 2 + rand.nextInt(4); b = 2 + rand.nextInt(4); c = 1 + rand.nextInt(4); }
		else
		{ a = 2 + rand.nextInt(8); b = 2 + rand.nextInt(8); c = 1 + rand.nextInt(8); }

		if(op2.equals("-") && c > b) { int t = b; b = c; c = t; }

		int p1     = computar(b, c, op2);
		int p2     = computar(a, p1, op1);
		int p3     = computar(p2, d, op3);
		int result = computar(p3, e, op4);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String t4  = opTex(op4);
		String par = "\\left(" + b + " " + t2 + " " + c + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\{[" + a + " " + t1 + " " + par + "] " + t3 + " " + d + "\\} " + t4 + " " + e + " = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\{[" + a + " " + t1 + " " + par + "] " + t3 + " " + d + "\\} " + t4 + " " + e + " = \\\\" +
			"& \\{[" + a + " " + t1 + " " + p1 + "] " + t3 + " " + d + "\\} " + t4 + " " + e + " = \\\\" +
			"& \\{" + p2 + " " + t3 + " " + d + "\\} " + t4 + " " + e + " = \\\\" +
			"& " + p3 + " " + t4 + " " + e + " = \\mathbf{" + result + "}\\end{aligned}\\)"
		);
	}
}
