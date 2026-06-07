package matematica.basico.expressaonumerica;

import matematica.GeradorExercicio;

public abstract class AgrupadorExercicio extends GeradorExercicio
{
	protected int computar(int a, int b, String op)
	{
		switch(op)
		{
			case "+": return a + b;
			case "-": return a - b;
			default:  return a * b;
		}
	}

	protected String opTex(String op)
	{
		return op.equals("*") ? "\\times" : op;
	}

	protected String opPM()
	{
		return rand.nextBoolean() ? "+" : "-";
	}

	protected String opPMM()
	{
		switch(rand.nextInt(3))
		{
			case 0:  return "+";
			case 1:  return "-";
			default: return "*";
		}
	}
}
