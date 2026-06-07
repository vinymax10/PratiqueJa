package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// {(A op1 B) op2 (C op3 D)} op5 (E op4 F) — chave envolvendo dois pares de parênteses
public class Expressao10 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op3 = opPM();
		String op2 = opPM();
		String op4 = opPM();
		String op5 = opPMM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c = 2 + rand.nextInt(8);
		int d = 1 + rand.nextInt(7);
		if(op3.equals("-") && d > c) { int t = c; c = d; d = t; }
		int p2 = computar(c, d, op3);

		int p3 = computar(p1, p2, op2);

		int e = 2 + rand.nextInt(8);
		int f = 1 + rand.nextInt(7);
		if(op4.equals("-") && f > e) { int t = e; e = f; f = t; }
		int p4 = computar(e, f, op4);

		int result = computar(p3, p4, op5);

		String t1   = opTex(op1);
		String t2   = opTex(op2);
		String t3   = opTex(op3);
		String t4   = opTex(op4);
		String t5   = opTex(op5);
		String par1 = "\\left(" + a + " " + t1 + " " + b + "\\right)";
		String par2 = "\\left(" + c + " " + t3 + " " + d + "\\right)";
		String par3 = "\\left(" + e + " " + t4 + " " + f + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(\\{" + par1 + " " + t2 + " " + par2 + "\\} " + t5 + " " + par3 + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& \\{" + par1 + " " + t2 + " " + par2 + "\\} " + t5 + " " + par3 + " = \\\\" +
			"& \\{" + p1 + " " + t2 + " " + p2 + "\\} " + t5 + " " + p4 + " = \\\\" +
			"& " + p3 + " " + t5 + " " + p4 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
