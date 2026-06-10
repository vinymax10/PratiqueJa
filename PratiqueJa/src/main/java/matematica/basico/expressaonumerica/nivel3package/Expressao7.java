package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// A op1 {B op2 [C op3 (D op4 E)]} — três níveis encadeados à direita
public class Expressao7 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op4 = opPM();
		String op3 = opPM();
		String op2 = opPM();
		String op1 = opPMM();

		int d = 2 + rand.nextInt(8);
		int e = 1 + rand.nextInt(7);
		if(op4.equals("-") && e > d) { int t = d; d = e; e = t; }
		int p1 = computar(d, e, op4);

		int c = op3.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(c, p1, op3);

		int b = op2.equals("-") ? p2 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p3 = computar(b, p2, op2);

		int a;
		if(op1.equals("*"))       a = 2 + rand.nextInt(5);
		else if(op1.equals("-"))  a = p3 + rand.nextInt(8) + 1;
		else                      a = 2 + rand.nextInt(18);
		int result = computar(a, p3, op1);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String t4  = opTex(op4);
		String par = "\\left(" + d + " " + t4 + " " + e + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + a + " " + t1 + " \\{" + b + " " + t2 + " [" + c + " " + t3 + " " + par + "]\\} = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " [" + c + " " + t3 + " " + par + "]\\} = \\\\" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " [" + c + " " + t3 + " " + p1 + "]\\} = \\\\" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " " + p2 + "\\} = \\\\" +
			"& " + a + " " + t1 + " " + p3 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
