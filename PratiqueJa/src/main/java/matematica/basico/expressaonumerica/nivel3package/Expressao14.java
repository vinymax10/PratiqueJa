package matematica.basico.expressaonumerica.nivel3package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// {A op1 [B op2 (C op3 D)]} op4 E — três níveis: padrão da teoria
public class Expressao14 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op3 = opPM();
		String op2 = opPMM();
		String op1 = opPM();
		String op4 = opPM();

		int a = 2 + rand.nextInt(8);
		int e = 2 + rand.nextInt(9);

		int b, c, d;
		if(op2.equals("*"))
		{ b = 2 + rand.nextInt(4); c = 2 + rand.nextInt(4); d = 1 + rand.nextInt(4); }
		else
		{ b = 2 + rand.nextInt(8); c = 2 + rand.nextInt(8); d = 1 + rand.nextInt(8); }

		if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }

		int p1     = computar(c, d, op3);
		int p2     = computar(b, p1, op2);
		int p3     = computar(a, p2, op1);
		int result = computar(p3, e, op4);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String t3  = opTex(op3);
		String t4  = opTex(op4);
		String par = "\\left(" + c + " " + t3 + " " + d + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\{" + a + " " + t1 + " [" + b + " " + t2 + " " + par + "]\\} " + t4 + " " + e + " = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\{" + a + " " + t1 + " [" + b + " " + t2 + " " + par + "]\\} " + t4 + " " + e + " = \\\\" +
			"& \\{" + a + " " + t1 + " [" + b + " " + t2 + " " + p1 + "]\\} " + t4 + " " + e + " = \\\\" +
			"& \\{" + a + " " + t1 + " " + p2 + "\\} " + t4 + " " + e + " = \\\\" +
			"& " + p3 + " " + t4 + " " + e + " = " + result + "\\end{aligned}\\)"
		);
	}
}
