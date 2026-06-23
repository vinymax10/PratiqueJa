package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Produto cruzado: resolver a proporção a/x = c/d (incógnita no denominador esquerdo)
public class RegraTres8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(9);  // 2..10
		int m = 2 + rand.nextInt(9);  // 2..10
		int d = 2 + rand.nextInt(13); // 2..14
		int a = c * m;
		int x = m * d;

		addParagrafo("Resolva a proporção e encontre o valor de \\(x\\):");
		addParagrafo("\\(\\dfrac{" + a + "}{x} = \\dfrac{" + c + "}{" + d + "}\\)");
		gerarAlternativasInteiras(x);

		addResolucao("Aplicando o produto cruzado (produto dos extremos = produto dos meios):");
		addResolucao("\\(" + a + " \\cdot " + d + " = " + c + " \\cdot x\\).");
		addResolucao("\\(x = \\dfrac{" + a + " \\cdot " + d + "}{" + c + "} = \\dfrac{" + (a * d) + "}{" + c + "} = \\mathbf{" + x + "}\\)");
	}
}
