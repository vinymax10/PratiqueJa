package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Produto cruzado: resolver a proporção a/b = c/x (incógnita no denominador direito)
public class RegraTres6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9);  // 2..10
		int m = 2 + rand.nextInt(9);  // 2..10
		int b = 2 + rand.nextInt(13); // 2..14
		int c = a * m;
		int x = b * m;

		addParagrafo("Resolva a proporção e encontre o valor de \\(x\\):");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{" + c + "}{x}\\)");
		gerarAlternativasInteiras(x);

		addResolucao("Aplicando o produto cruzado (produto dos extremos = produto dos meios):");
		addResolucao("\\(" + a + " \\cdot x = " + b + " \\cdot " + c + "\\).");
		addResolucao("\\(x = \\dfrac{" + b + " \\cdot " + c + "}{" + a + "} = \\dfrac{" + (b * c) + "}{" + a + "} = \\mathbf{" + x + "}\\)");
	}
}
