package matematica.avancado.geometriaespacial.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l  = 2 + rand.nextInt(7); // 2..8
		int w  = 2 + rand.nextInt(7); // 2..8
		int h  = 2 + rand.nextInt(7); // 2..8
		int lw = l * w;
		int V  = lw * h;

		addParagrafo("Uma caixa retangular tem dimensões \\(" + l + "\\,\\text{cm} \\times "
				+ w + "\\,\\text{cm} \\times " + h + "\\,\\text{cm}\\). Calcule o volume.");

		gerarAlternativasInteiras(V, 4, true);

		addResolucao("\\(V = l \\times w \\times h = "
				+ l + " \\times " + w + " \\times " + h + " = "
				+ lw + " \\times " + h + " = \\mathbf{" + V + "}\\,\\text{cm}^3\\)");
	}
}
