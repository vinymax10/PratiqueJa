package matematica.intermediario.equacoes.nivel3package;

import matematica.GeradorExercicio;

public class Equacao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// ax + b = cx + d  →  x = (d - b) / (a - c)
		// a > c garante coeficiente resultante positivo; xResult garante resultado inteiro.
		int a = 2 + rand.nextInt(7);           // 2..8
		int c = 1 + rand.nextInt(a - 1);       // 1..(a-1)
		int xResult = 1 + rand.nextInt(9);     // 1..9
		int coef = a - c;
		int b = 1 + rand.nextInt(15);          // 1..15
		int d = b + coef * xResult;            // garante (d-b)/(a-c) = xResult

		String var = "" + (char) (97 + rand.nextInt(25));

		String aStr    = a    > 1 ? a    + "" : "";
		String cStr    = c    > 1 ? c    + "" : "";
		String coefStr = coef > 1 ? coef + "" : "";

		addParagrafo("Encontre \\(" + var + "\\)");
		addParagrafo("\\(" + aStr + var + " + " + b + " = " + cStr + var + " + " + d + "\\)");

		addResolucao("Passando os termos com \\(" + var + "\\) para o lado esquerdo"
				+ " e as constantes para o direito:");
		addResolucao("\\(" + aStr + var + " - " + cStr + var + " = " + d + " - " + b + "\\).");
		addResolucao("\\(" + coefStr + var + " = " + (d - b) + "\\).");
		if(coef > 1)
			addResolucao("\\(" + var + " = \\dfrac{" + (d - b) + "}{" + coef + "} = " + xResult + "\\).");

		gerarAlternativas("" + xResult);
	}
}
