package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Produto cruzado: proporção a/b = c/x com faixa de valores maior
public class RegraTres14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 3 + rand.nextInt(12); // 3..14
		int m = 2 + rand.nextInt(11); // 2..12
		int b = 3 + rand.nextInt(15); // 3..17
		int c = a * m;
		int x = b * m;

		addParagrafo("Resolva a proporção e encontre o valor de \\(x\\):");
		addParagrafo("\\(\\dfrac{" + a + "}{" + b + "} = \\dfrac{" + c + "}{x}\\)");
		gerarAlternativasInteiras(x);

		String res = "Aplicando o produto cruzado (produto dos extremos = produto dos meios): \\(\\\\\\)";
		res += "\\(" + a + " \\cdot x = " + b + " \\cdot " + c + "\\). \\(\\\\\\)";
		res += "\\(x = \\dfrac{" + b + " \\cdot " + c + "}{" + a + "} = \\dfrac{" + (b * c) + "}{" + a + "} = \\mathbf{" + x + "}\\)";
		setResolucao(res);
	}
}
