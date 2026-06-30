package matematica.intermediario.equacoes.nivel2package;

import matematica.GeradorExercicio;

public class Equacao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a(bx + c) = d  →  x = (d/a - c) / b
		int a = 2 + rand.nextInt(4);        // 2..5
		int b = 2 + rand.nextInt(4);        // 2..5
		int c = 1 + rand.nextInt(9);        // 1..9
		int xResult = 1 + rand.nextInt(8);  // 1..8
		int d = a * (b * xResult + c);      // garante resultado inteiro

		String var = "" + (char) (97 + rand.nextInt(25));

		int ab = a * b;
		int ac = a * c;
		String bStr = b > 1 ? b + "" : "";

		addParagrafo("Encontre \\(" + var + "\\)");
		addParagrafo("\\(" + a + "(" + bStr + var + " + " + c + ") = " + d + "\\)");

		addResolucao("Aplicando a propriedade distributiva:");
		addResolucao("\\(" + ab + var + " + " + ac + " = " + d + "\\).");
		addResolucao("Isolando o termo com \\(" + var + "\\):");
		addResolucao("\\(" + ab + var + " = " + d + " - " + ac + " = " + (d - ac) + "\\).");
		addResolucao("\\(" + var + " = \\dfrac{" + (d - ac) + "}{" + ab + "} = \\mathbf{" + xResult + "}\\).");

		gerarAlternativas("" + xResult);
	}
}
