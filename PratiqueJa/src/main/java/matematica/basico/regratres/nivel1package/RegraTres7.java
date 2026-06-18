package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Produto cruzado: resolver a proporção x/b = c/d (incógnita no numerador esquerdo)
public class RegraTres7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int d = 2 + rand.nextInt(9);  // 2..10
		int m = 2 + rand.nextInt(9);  // 2..10
		int b = 2 + rand.nextInt(13); // 2..14
		int c = d * m;
		int x = b * m;

		addParagrafo("Resolva a proporção e encontre o valor de \\(x\\):");
		addParagrafo("\\(\\dfrac{x}{" + b + "} = \\dfrac{" + c + "}{" + d + "}\\)");
		gerarAlternativasInteiras(x);

		String res = "Aplicando o produto cruzado (produto dos extremos = produto dos meios): \\(\\\\\\)";
		res += "\\(" + d + " \\cdot x = " + b + " \\cdot " + c + "\\). \\(\\\\\\)";
		res += "\\(x = \\dfrac{" + b + " \\cdot " + c + "}{" + d + "} = \\dfrac{" + (b * c) + "}{" + d + "} = \\mathbf{" + x + "}\\)";
		setResolucao(res);
	}
}
