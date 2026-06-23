package matematica.intermediario.radiciacao.nivel3package;

import matematica.GeradorExercicio;

// 4ª propriedade: simplificação de índice — √[nc]{a^(nc)} = √[n]{a^n} = a
public class Radiciacao6 extends GeradorExercicio
{
	private static final int[] BASES = {2, 3, 5};

	@Override
	protected void construir()
	{
		int a  = BASES[rand.nextInt(BASES.length)];
		int n  = 2 + rand.nextInt(2); // 2 ou 3 (índice final)
		int c  = 2 + rand.nextInt(2); // 2 ou 3 (fator de simplificação)
		int nc = n * c;
		int V  = (int) Math.pow(a, nc);

		addParagrafo("Calcule simplificando o índice:");
		addParagrafo("\\(\\sqrt[" + nc + "]{" + V + "}\\)");
		gerarAlternativas("" + a);
		addResolucao("Decompor o radicando:");
		addResolucao("\\(" + V + " = " + a + "^{" + nc + "}\\)");
		addResolucao("Simplificar índice e expoente \\((\\div " + c + ")\\):");
		addResolucao("\\(\\sqrt[" + nc + "]{" + a + "^{" + nc + "}} = \\sqrt[" + n + "]{" + a + "^{" + n + "}} = \\mathbf{" + a + "}\\)");
	}
}
