	package matematica.basico.expressaonumerica.nivel1package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// (A op1 B) op2 C — parênteses à esquerda
public class Expressao2 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPMM();

		int a, b, c;
		if(op2.equals("*"))
		{ a = 2 + rand.nextInt(5); b = 1 + rand.nextInt(5); c = 2 + rand.nextInt(6); }
		else
		{ a = 2 + rand.nextInt(9); b = 1 + rand.nextInt(9); c = 2 + rand.nextInt(9); }

		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }

		int p1     = computar(a, b, op1);
		int result = computar(p1, c, op2);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String par = "\\left(" + a + " " + t1 + " " + b + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + par + " " + t2 + " " + c + " = \\,?\\)");
		gerarAlternativas("" + result);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + par + " " + t2 + " " + c + " = \\\\" +
			"& " + p1 + " " + t2 + " " + c + " = " + result + "\\end{aligned}\\)"
		);
	}
}
