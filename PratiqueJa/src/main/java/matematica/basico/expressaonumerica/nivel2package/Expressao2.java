package matematica.basico.expressaonumerica.nivel2package;

import matematica.basico.expressaonumerica.AgrupadorExercicio;

// (A op1 B) op2 C — parênteses à esquerda
public class Expressao2 extends AgrupadorExercicio
{
	@Override
	protected void construir()
	{
		String op1 = opPM();
		String op2 = opPMM();

		int a = 2 + rand.nextInt(8);
		int b = 1 + rand.nextInt(7);
		if(op1.equals("-") && b > a) { int t = a; a = b; b = t; }
		int p1 = computar(a, b, op1);

		int c;
		if(op2.equals("*"))       c = 2 + rand.nextInt(5);
		else if(op2.equals("-"))  c = p1 + rand.nextInt(10);
		else                      c = 2 + rand.nextInt(18);

		int result = computar(p1, c, op2);

		String t1  = opTex(op1);
		String t2  = opTex(op2);
		String par = "\\left(" + a + " " + t1 + " " + b + "\\right)";

		addParagrafo("Calcule o valor da expressão numérica:");
		addParagrafo("\\(" + par + " " + t2 + " " + c + " = \\,?\\)");
		gerarAlternativas("" + result);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + par + " " + t2 + " " + c + " = \\\\" +
			"& " + p1 + " " + t2 + " " + c + " = " + result + "\\end{aligned}\\)"
		);
	}
}
