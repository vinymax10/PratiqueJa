package matematica.intermediario.equacoes.nivel1package;

import matematica.GeradorExercicio;

public class Equacao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// ax + b = c  →  x = (c - b) / a
		int a = 2 + rand.nextInt(8);        // 2..9
		int xResult = 1 + rand.nextInt(10); // 1..10
		int b = 1 + rand.nextInt(20);       // 1..20
		int c = a * xResult + b;            // garante resultado inteiro

		String var = "" + (char) (97 + rand.nextInt(25));
		String aStr = a > 1 ? a + "" : "";

		addParagrafo("Encontre \\(" + var + "\\)");
		addParagrafo("\\(" + aStr + var + " + " + b + " = " + c + "\\)");

		addResolucao("Subtraindo \\(" + b + "\\) dos dois lados:");
		addResolucao("\\(" + aStr + var + " = " + c + " - " + b + " = " + (c - b) + "\\).");
		if(a > 1)
			addResolucao("\\(" + var + " = \\dfrac{" + (c - b) + "}{" + a + "} = " + xResult + "\\).");
		else
			addResolucao("\\(" + var + " = " + xResult + "\\).");

		gerarAlternativas("" + xResult);
	}
}
