package matematica.intermediario.razoestrigonometricas.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.razoestrigonometricas.ResolucaoRazoesTrigonometricas;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// sen(α) = p/r → cos(α) = q/r  (triplas pitagóricas garantem inteiros)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];

		String angle  = LetrasGregas.getLetra();
		Racional senVal = new Racional(p, r);
		senVal.fatoracao(2);
		Racional cosVal = new Racional(q, r);
		cosVal.fatoracao(2);

		addParagrafo("Sabendo que \\(\\text{sen}~" + angle + " = " + senVal.showDfrac()
				+ "\\) e que \\(" + angle + "\\) é um ângulo agudo, calcule \\(\\cos~" + angle + "\\).");

		gerarAlternativas(cosVal.toString());
		String resolucao = ResolucaoRazoesTrigonometricas.identidadePitagorica(angle, p, q, r);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
