package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(1, size, 50);
		int[][] b = AuxMatriz.contruirMatriz(size, 1, 50);

		String resolucao = "";
		resolucao += "A \\cdot B=";
		resolucao += AuxMatriz.matrizStr(a) + "\\cdot" + AuxMatriz.matrizStr(b) + "=\\\\ \\\\";
		int resultado = 0;
		for(int i = 0; i < size; i++)
		{
			resolucao += AuxMatriz.parenteses(a[0][i]) + "\\cdot" + AuxMatriz.parenteses(b[i][0]);

			if(i < (size - 1))
				resolucao += "+";
		}
		resolucao += "=\\\\";

		for(int i = 0; i < size; i++)
		{
			if(i > 0 && a[0][i] * b[i][0] >= 0)
				resolucao += "+";

			resolucao += a[0][i] * b[i][0];
			resultado += a[0][i] * b[i][0];
		}
		resolucao += "=" + resultado;

		String resultadoCorreto = resultado + "";

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Qual o resultado de \\(A \\cdot B\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
