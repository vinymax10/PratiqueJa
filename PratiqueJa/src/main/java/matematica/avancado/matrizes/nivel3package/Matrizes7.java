package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(size, size, 20);

		String resultadoCorreto = AuxMatriz.determinante(a) + "";

		String resolucao = "";
		if(size == 2)
		{
			resolucao += ParCor.formula("\\det~ A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1}") + "\\\\";
			resolucao += "\\det~ A = " + a[0][0] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) +
			"-" + AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + "\\\\";

			resolucao += "\\det~ A =" + (a[0][0] * a[1][1]) + "-" + AuxMatriz.parenteses(a[0][1] * a[1][0]) + "=" + resultadoCorreto;
		}
		else
		{
			resolucao += ParCor.formula("\\det~ A = sd_p - sd_s") + "\\\\";

			resolucao += "\\text{Soma das diagonais principais } sd_p: \\\\";

			resolucao += "sd_p=" +
			a[0][0] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) + " \\cdot " + AuxMatriz.parenteses(a[2][2]) + "+" +
			AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][2]) + " \\cdot " + AuxMatriz.parenteses(a[2][0]) + "+" +
			AuxMatriz.parenteses(a[0][2]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + " \\cdot " + AuxMatriz.parenteses(a[2][1]) + "\\\\";

			int sdp = ((a[0][0] * a[1][1] * a[2][2]) + (a[0][1] * a[1][2] * a[2][0]) + (a[0][2] * a[1][0] * a[2][1]));

			resolucao += "sd_p=" +
			(a[0][0] * a[1][1] * a[2][2]) +
			AuxMatriz.sinalPos(a[0][1] * a[1][2] * a[2][0]) +
			AuxMatriz.sinalPos(a[0][2] * a[1][0] * a[2][1]) +
			"=" + sdp + "\\\\";

			resolucao += "\\text{Soma das diagonais secundárias } sd_s:\\\\";
			resolucao += "sd_s=" +

			a[0][2] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) + " \\cdot " + AuxMatriz.parenteses(a[2][0])
			+ "+" + AuxMatriz.parenteses(a[0][0]) + " \\cdot " + AuxMatriz.parenteses(a[1][2]) + " \\cdot " + AuxMatriz.parenteses(a[2][1])
			+ "+" + AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + " \\cdot " + AuxMatriz.parenteses(a[2][2]) + "\\\\";

			int sds = ((a[0][2] * a[1][1] * a[2][0]) + (a[0][0] * a[1][2] * a[2][1]) + (a[0][1] * a[1][0] * a[2][2]));

			resolucao += "sd_s=" +
			(a[0][2] * a[1][1] * a[2][0]) +
			AuxMatriz.sinalPos(a[0][0] * a[1][2] * a[2][1]) +
			AuxMatriz.sinalPos(a[0][1] * a[1][0] * a[2][2]) +
			"=" + sds + "\\\\";

			resolucao += "\\det~ A = " + sdp + "-" + AuxMatriz.parenteses(sds) + "=" + resultadoCorreto;
		}

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Qual o \\( \\det~ A \\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
