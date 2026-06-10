package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int coefA = 2 + rand.nextInt(9); // 2..10
		int coefB = 2 + rand.nextInt(9); // 2..10
		int expA = 1 + rand.nextInt(3);  // 1..3
		int expB = 1 + rand.nextInt(3);  // 1..3
		int coefR = coefA * coefB;
		int expR = expA + expB;

		String monomioA = coefA + "x^{" + expA + "}";
		String monomioB = coefB + "x^{" + expB + "}";
		String exprLatex = monomioA + " \\times " + monomioB;

		boolean perguntaCoef = rand.nextBoolean();

		if(perguntaCoef)
		{
			addParagrafo("Ao multiplicar os monômios abaixo, qual é o coeficiente do resultado?");
			addParagrafo("\\(" + exprLatex + "\\)");
			gerarAlternativasInteiras(coefR);

			String res = "Na multiplicação de monômios, multiplicamos os coeficientes e somamos os expoentes. \\(\\\\\\)";
			res += "\\(" + exprLatex + " = \\left(" + coefA + " \\times " + coefB + "\\right) \\cdot x^{" + expA + "+" + expB + "} = \\\\ \\)";
			res += "\\(" + coefR + "x^{" + expR + "} \\\\ \\)";
			res += "O coeficiente é \\(" + coefR + "\\).";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Ao multiplicar os monômios abaixo, qual é o expoente de \\(x\\) no resultado?");
			addParagrafo("\\(" + exprLatex + "\\)");
			gerarAlternativasInteiras(expR);

			String res = "Na multiplicação de monômios, multiplicamos os coeficientes e somamos os expoentes. \\(\\\\\\)";
			res += "\\(" + exprLatex + " = \\left(" + coefA + " \\times " + coefB + "\\right) \\cdot x^{" + expA + "+" + expB + "} = \\\\ \\)";
			res += "\\(" + coefR + "x^{" + expR + "} \\\\ \\)";
			res += "O expoente de \\(x\\) no resultado é \\(" + expR + "\\).";
			setResolucao(res);
		}
	}
}
