package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(size, size, 20);

		String resultadoCorreto = AuxMatriz.determinante(a) + "";

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Qual o \\( \\det~ A \\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		if(size == 2)
		{
			addResolucao("\\(" + ParCor.formula("\\det~ A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1}") + "\\)");
			addResolucao("\\(\\det~ A = " + a[0][0] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) +
				"-" + AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + "\\)");
			addResolucao("\\(\\det~ A =" + (a[0][0] * a[1][1]) + "-" + AuxMatriz.parenteses(a[0][1] * a[1][0]) + "=\\mathbf{" + resultadoCorreto + "}" + "\\)");
		}
		else
		{
			addResolucao("\\(" + ParCor.formula("\\det~ A = sd_p - sd_s") + "\\)");
			addResolucao("\\(\\text{Soma das diagonais principais } sd_p:\\)");
			addResolucao("\\(sd_p=" +
				a[0][0] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) + " \\cdot " + AuxMatriz.parenteses(a[2][2]) + "+" +
				AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][2]) + " \\cdot " + AuxMatriz.parenteses(a[2][0]) + "+" +
				AuxMatriz.parenteses(a[0][2]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + " \\cdot " + AuxMatriz.parenteses(a[2][1]) + "\\)");

			int sdp = ((a[0][0] * a[1][1] * a[2][2]) + (a[0][1] * a[1][2] * a[2][0]) + (a[0][2] * a[1][0] * a[2][1]));
			addResolucao("\\(sd_p=" +
				(a[0][0] * a[1][1] * a[2][2]) +
				AuxMatriz.sinalPos(a[0][1] * a[1][2] * a[2][0]) +
				AuxMatriz.sinalPos(a[0][2] * a[1][0] * a[2][1]) +
				"=" + sdp + "\\)");

			addResolucao("\\(\\text{Soma das diagonais secundárias } sd_s:\\)");
			addResolucao("\\(sd_s=" +
				a[0][2] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) + " \\cdot " + AuxMatriz.parenteses(a[2][0])
				+ "+" + AuxMatriz.parenteses(a[0][0]) + " \\cdot " + AuxMatriz.parenteses(a[1][2]) + " \\cdot " + AuxMatriz.parenteses(a[2][1])
				+ "+" + AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + " \\cdot " + AuxMatriz.parenteses(a[2][2]) + "\\)");

			int sds = ((a[0][2] * a[1][1] * a[2][0]) + (a[0][0] * a[1][2] * a[2][1]) + (a[0][1] * a[1][0] * a[2][2]));
			addResolucao("\\(sd_s=" +
				(a[0][2] * a[1][1] * a[2][0]) +
				AuxMatriz.sinalPos(a[0][0] * a[1][2] * a[2][1]) +
				AuxMatriz.sinalPos(a[0][1] * a[1][0] * a[2][2]) +
				"=" + sds + "\\)");

			addResolucao("\\(\\det~ A = " + sdp + "-" + AuxMatriz.parenteses(sds) + "=\\mathbf{" + resultadoCorreto + "}" + "\\)");
		}
	}
}
