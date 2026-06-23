package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class SubtracaoOposto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		if (rand.nextBoolean()) a = -a;
		int b = 1 + rand.nextInt(10);
		boolean subtrairNeg = rand.nextBoolean();

		int finalB = subtrairNeg ? b : -b;
		int resultado = a + finalB;

		String aStr = Auxiliar.getNumber(a, "", true);
		String expr, simplExpr, regraTxt;

		if (subtrairNeg)
		{
			expr = aStr + " - (-" + b + ")";
			simplExpr = Auxiliar.getNumber(a, "", true) + Auxiliar.getNumber(b, "", false);
			regraTxt = "\\(-(-" + b + ") = +" + b + "\\): sinal negativo inverte o sinal";
		}
		else
		{
			expr = aStr + " - (+" + b + ")";
			simplExpr = Auxiliar.getNumber(a, "", true) + Auxiliar.getNumber(-b, "", false);
			regraTxt = "\\(-(+" + b + ") = -" + b + "\\): sinal negativo mantém o sinal negativo";
		}

		addParagrafo("Calcule: \\(" + expr + "\\)");
		gerarAlternativasInteirasComNegativos(resultado);

		addResolucao("Aplicamos a regra do sinal antes do parêntese — " + regraTxt + ".");
		addResolucao("\\(" + expr + " = " + simplExpr + " = \\mathbf{" + resultado + "}\\)");
	}
}
