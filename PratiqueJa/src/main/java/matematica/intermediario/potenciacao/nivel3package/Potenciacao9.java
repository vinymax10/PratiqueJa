package matematica.intermediario.potenciacao.nivel3package;

import matematica.GeradorExercicio;

public class Potenciacao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (a^x)^y · a^z / a^w = a^k → encontrar k = x*y + z - w
		int a = 2 + rand.nextInt(8); // 2..9
		int x, y, z, w, k;
		do
		{
			x = 2 + rand.nextInt(4); // 2..5
			y = 2 + rand.nextInt(3); // 2..4
			z = 1 + rand.nextInt(5); // 1..5
			w = 1 + rand.nextInt(5); // 1..5
			k = x * y + z - w;
		}
		while (k <= 0 || k > 20);

		int xy  = x * y;
		int xyz = xy + z;
		String expr =
			"\\dfrac{(" + a + "^{" + x + "})^{" + y + "} \\cdot " + a + "^{" + z + "}}{"
			+ a + "^{" + w + "}} = " + a + "^{x}";

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + expr + "\\)");
		gerarAlternativas("" + k);
		setResolucao(
			"Potência de potência:" +
			"\\(\\\\\\)" +
			"\\((" + a + "^{" + x + "})^{" + y + "} = " + a + "^{" + x + " \\cdot " + y + "} = " + a + "^{" + xy + "}\\)" +
			"\\(\\\\\\)" +
			"Mult. mesma base:" +
			"\\(\\\\\\)" +
			"\\(" + a + "^{" + xy + "} \\cdot " + a + "^{" + z + "} = " + a + "^{" + xyz + "}\\)" +
			"\\(\\\\\\)" +
			"Div. mesma base:" +
			"\\(\\\\\\)" +
			"\\(\\dfrac{" + a + "^{" + xyz + "}}{" + a + "^{" + w + "}} = " + a + "^{" + xyz + "-" + w + "} = \\mathbf{" + a + "^{" + k + "}}\\)" +
			"\\(\\\\\\)" +
			"\\(x = \\mathbf{" + k + "}\\)"
		);
	}
}
