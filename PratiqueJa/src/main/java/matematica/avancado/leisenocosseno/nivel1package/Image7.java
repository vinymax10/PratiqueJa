package matematica.avancado.leisenocosseno.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.leisenocosseno.Resolucao;
import matematica.intermediario.razoestrigonometricas.dados.LetrasGregas;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] pairs = {{3, 5}, {4, 5}, {5, 13}, {12, 13}, {8, 17}, {15, 17}, {7, 25}, {24, 25}};
		int[] pair = pairs[rand.nextInt(pairs.length)];
		int p = pair[0], q = pair[1];
		int k = 1 + rand.nextInt(5);
		int R = q * k;
		Racional sen = new Racional(p, q);
		int resultado = 2 * p * k;
		String angle = LetrasGregas.getLetra();

		String resolucao = Resolucao.ladoPorRaio(angle, new Racional(R), sen);

		addParagrafo("O raio da circunferência circunscrita a um triângulo é \\(R = " + R + "\\). "
				+ "Sabendo que \\(\\text{sen}~" + angle + " = " + sen.showDfrac() + "\\), "
				+ "qual o valor de \\(x\\)?");
		gerarAlternativas("" + resultado);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
