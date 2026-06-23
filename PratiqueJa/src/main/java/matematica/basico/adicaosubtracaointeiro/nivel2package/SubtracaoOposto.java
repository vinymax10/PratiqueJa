package matematica.basico.adicaosubtracaointeiro.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class SubtracaoOposto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(90);
		if (rand.nextBoolean()) a = -a;
		int b = 10 + rand.nextInt(90);
		boolean subtrairNeg = rand.nextBoolean();

		int finalB = subtrairNeg ? b : -b;
		int resultado = a + finalB;

		String aStr = Auxiliar.getNumber(a, "", true);
		String expr, simplExpr, regraTxt;

		if (subtrairNeg)
		{
			expr = aStr + " - (-" + b + ")";
			simplExpr = Auxiliar.getNumber(a, "", true) + Auxiliar.getNumber(b, "", false);
			regraTxt = "\\(-(-" + b + ") = +" + b + "\\)";
		}
		else
		{
			expr = aStr + " - (+" + b + ")";
			simplExpr = Auxiliar.getNumber(a, "", true) + Auxiliar.getNumber(-b, "", false);
			regraTxt = "\\(-(+" + b + ") = -" + b + "\\)";
		}

		addParagrafo("Calcule: \\(" + expr + "\\)");
		gerarAlternativasInteirasComNegativos(resultado);

		addResolucao("Eliminamos o parêntese aplicando a regra de sinal — " + regraTxt + ":");
		addResolucao("\\(" + expr + " = " + simplExpr + " = \\mathbf{" + resultado + "}\\)");
	}
}
