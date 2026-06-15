package matematica.intermediario.razoestrigonometricas.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;

public class Image18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// sen(alpha°) = cos(beta°) onde alpha + beta = 90°
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int p = t[0], r = t[2];

		int alpha = 20 + rand.nextInt(50); // 20°..69°
		int beta  = 90 - alpha;

		Racional senVal = new Racional(p, r);
		senVal.fatoracao(2);

		addParagrafo("Sabendo que \\(\\text{sen}~" + alpha + "° = " + senVal.showDfrac()
				+ "\\), calcule \\(\\cos~" + beta + "°\\).");

		gerarAlternativas(senVal.toString());
		String resolucao = ResolucaoRazoesTrigonometricas.angulosComplementares(alpha, beta, p, r);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
