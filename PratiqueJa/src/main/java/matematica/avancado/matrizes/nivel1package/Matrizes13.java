package matematica.avancado.matrizes.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int lin = 2 + rand.nextInt(2);
		int col = 2 + rand.nextInt(2);

		int escalar = 2 + rand.nextInt(10);
		if(rand.nextBoolean())
			escalar *= -1;

		int[][] a = AuxMatriz.contruirMatriz(lin, col, 50);

		int b[][] = new int[lin][col];
		for(int i = 0; i < lin; i++)
			for(int j = 0; j < col; j++)
				b[i][j] = a[i][j] * escalar;

		String resultadoCorreto = AuxMatriz.soma(b) + "";

		String resolucao = "B=\\begin{bmatrix}";
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				resolucao += escalar + " \\cdot " + AuxMatriz.parenteses(a[i][j]);

				if(j < (col - 1))
					resolucao += "&";
			}
			resolucao += "\\\\";
		}
		resolucao += "\\end{bmatrix}" + "\\\\ \\\\";
		resolucao += "B=" + AuxMatriz.matrizStr(b) + "\\\\ \\\\";
		resolucao += AuxMatriz.somaStr(b);

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Se \\(" + escalar + " \\cdot A=B\\), qual a soma dos elementos de \\(B\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
