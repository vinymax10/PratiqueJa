package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Simplificação: √n = k√r (extraindo fator quadrado perfeito)
public class Radiciacao9 extends GeradorExercicio
{
	private static final int[] LIVRES = {2, 3, 5, 6, 7, 10, 11, 13};

	@Override
	protected void construir()
	{
		int r = LIVRES[rand.nextInt(LIVRES.length)];
		int k = 2 + rand.nextInt(7); // 2..8
		int n = k * k * r;

		String correta = "\\(" + k + "\\sqrt{" + r + "}\\)";
		String e1      = "\\(\\sqrt{" + n + "}\\)";                  // não simplificado
		String e2      = "\\(" + k + "\\sqrt{" + (r * 2) + "}\\)";  // radicando errado
		String e3      = "\\(" + (k + 1) + "\\sqrt{" + r + "}\\)"; // coeficiente errado

		addParagrafo("Simplifique:");
		addParagrafo("\\(\\sqrt{" + n + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Identificar o maior fator quadrado perfeito de \\(" + n + "\\):");
		addResolucao("\\(" + n + " = " + (k * k) + " \\times " + r + "\\)");
		addResolucao("\\(\\sqrt{" + n + "} = \\sqrt{" + (k * k) + " \\times " + r + "} = \\sqrt{" + (k * k) + "} \\cdot \\sqrt{" + r + "}\\)");
		addResolucao("\\(= \\mathbf{" + k + "\\sqrt{" + r + "}}\\)");
	}
}
