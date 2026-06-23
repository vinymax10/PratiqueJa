package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// A op1 {B op2 [(C op3 D) op4 E]} — chave com colchete e parêntese interno à esquerda
public class Expressao9 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op3 = opPM();
		String op4 = opPM();
		String op2 = opPM();
		String op1 = opPMM();

		int c = 2 + rand.nextInt(8);
		int d = 1 + rand.nextInt(7);
		if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }
		int p1 = computar(c, d, op3);

		int e = op4.equals("-") ? p1 + rand.nextInt(8) + 1 : 2 + rand.nextInt(8);
		int p2 = computar(p1, e, op4);

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
		String par = "\\left(" + c + " " + t3 + " " + d + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + a + " " + t1 + " \\{" + b + " " + t2 + " [" + par + " " + t4 + " " + e + "]\\} = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " [" + par + " " + t4 + " " + e + "]\\} = \\\\" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " [" + p1 + " " + t4 + " " + e + "]\\} = \\\\" +
			"& " + a + " " + t1 + " \\{" + b + " " + t2 + " " + p2 + "\\} = \\\\" +
			"& " + a + " " + t1 + " " + p3 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
