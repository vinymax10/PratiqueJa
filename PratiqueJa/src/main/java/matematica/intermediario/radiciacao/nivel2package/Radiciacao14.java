package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Simplificação de raiz cúbica: ∛(k³·r) = k·∛r
public class Radiciacao14 extends GeradorExercicio
{
	private static final int[] LIVRES = {2, 3, 5, 6, 7};

	@Override
	protected void construir()
	{
		int r, k, n;
		do
		{
			r = LIVRES[rand.nextInt(LIVRES.length)];
			k = 2 + rand.nextInt(4); // 2..5
			n = k * k * k * r;
		}
		while(n > 1000);

		int k3 = k * k * k;

		String correta = "\\(" + k + "\\sqrt[3]{" + r + "}\\)";
		String e1      = "\\(\\sqrt[3]{" + n + "}\\)";
		String e2      = "\\(" + k + "\\sqrt[3]{" + (r + 1) + "}\\)";
		String e3      = "\\(" + (k + 1) + "\\sqrt[3]{" + r + "}\\)";

		addParagrafo("Simplifique:");
		addParagrafo("\\(\\sqrt[3]{" + n + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Identificar o maior fator cubo perfeito de \\(" + n + "\\):");
		addResolucao("\\(" + n + " = " + k3 + " \\times " + r + " = " + k + "^3 \\times " + r + "\\)");
		addResolucao("\\(\\sqrt[3]{" + n + "} = \\sqrt[3]{" + k + "^3 \\times " + r + "} = \\sqrt[3]{" + k + "^3} \\cdot \\sqrt[3]{" + r + "} =\\)");
		addResolucao("\\(\\mathbf{" + k + "\\sqrt[3]{" + r + "}}\\)");
	}
}
