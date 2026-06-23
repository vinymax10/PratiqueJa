package matematica.avancado.geometriaespacial.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l      = 2 + rand.nextInt(7); // 2..8
		int w      = 2 + rand.nextInt(7); // 2..8
		int h      = 2 + rand.nextInt(7); // 2..8
		int pBase  = 2 * (l + w);
		int aBase  = l * w;
		int aLat   = pBase * h;
		int aTot   = aLat + 2 * aBase;

		addParagrafo("Calcule a área total de uma caixa retangular com dimensões \\(" + l
				+ "\\,\\text{cm} \\times " + w + "\\,\\text{cm} \\times " + h + "\\,\\text{cm}\\).");

		gerarAlternativasInteiras(aTot, 4, true);

		addResolucao("\\(A_{\\text{base}} = " + l + " \\times " + w + " = " + aBase
				+ "\\,\\text{cm}^2 \\quad P_{\\text{base}} = 2(" + l + " + " + w + ") = " + pBase + "\\,\\text{cm}\\)");
		addResolucao("\\(A_{\\text{lateral}} = " + pBase + " \\times " + h + " = " + aLat + "\\,\\text{cm}^2\\)");
		addResolucao("\\(A_{\\text{total}} = " + aLat + " + 2 \\times " + aBase + " = "
				+ aLat + " + " + (2 * aBase) + " = \\mathbf{" + aTot + "}\\,\\text{cm}^2\\)");
	}
}
