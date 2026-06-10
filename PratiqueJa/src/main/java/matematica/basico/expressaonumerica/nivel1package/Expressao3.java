package matematica.basico.expressaonumerica.nivel1package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// A op1 (B op2 C) — parênteses à direita
public class Expressao3 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPMM();
		String op2 = opPM();

		int a, b, c;
		if(op1.equals("*"))
		{ a = 2 + rand.nextInt(5); b = 2 + rand.nextInt(5); c = 1 + rand.nextInt(5); }
		else
		{ a = 2 + rand.nextInt(9); b = 2 + rand.nextInt(9); c = 1 + rand.nextInt(9); }

		if(op2.equals("-") && c > b) { int t = b; b = c; c = t; }

		int p1     = computar(b, c, op2);
		int result = computar(a, p1, op1);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String par = "\\left(" + b + " " + t2 + " " + c + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + a + " " + t1 + " " + par + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " " + t1 + " " + par + " = \\\\" +
			"& " + a + " " + t1 + " " + p1 + " = " + result + "\\end{aligned}\\)"
		);
	}
}
