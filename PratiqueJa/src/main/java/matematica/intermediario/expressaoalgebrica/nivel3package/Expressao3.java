package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 2 + rand.nextInt(4); // 2..5
		int a = 1 + rand.nextInt(4); // 1..4
		int b = 1 + rand.nextInt(9); // 1..9
		int v = 1 + rand.nextInt(8); // 1..8 (valor de x)
		boolean positivo = rand.nextBoolean();

		String sinalStr = positivo ? "+" : "-";
		String termoX = a == 1 ? "x" : a + "x";
		String exprLatex = k + "\\left(" + termoX + " " + sinalStr + " " + b + "\\right)";

		int resultado = k * (a * v + (positivo ? b : -b));

		int coefX = k * a;
		int coefCte = k * b;
		String expandida = (coefX == 1 ? "x" : coefX + "x") + " " + sinalStr + " " + coefCte;

		addParagrafo("Calcule o valor da expressão para \\(x = " + v + "\\):");
		addParagrafo("\\(" + exprLatex + "\\)");
		gerarAlternativasInteiras(resultado);

		int term1 = coefX * v;
		int term2 = coefCte;

		String res = "Aplicamos a propriedade distributiva: \\(\\\\\\)";
		res += "\\(" + exprLatex + " = " + expandida + "\\) \\(\\\\\\)";
		res += "Substituindo \\(x = " + v + "\\): \\(\\\\\\)";
		res += "\\(" + term1 + " " + sinalStr + " " + term2 + " = " + resultado + "\\)";
		setResolucao(res);
	}
}
