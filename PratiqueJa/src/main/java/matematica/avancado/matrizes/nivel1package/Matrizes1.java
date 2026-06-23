package matematica.avancado.matrizes.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int lin = 2 + rand.nextInt(2);
		int col = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(lin, col, 50);
		int[][] b = AuxMatriz.contruirMatriz(lin, col, 50);

		int c[][] = new int[lin][col];
		for(int i = 0; i < lin; i++)
			for(int j = 0; j < col; j++)
				c[i][j] = a[i][j] + b[i][j];

		String resultadoCorreto = AuxMatriz.soma(c) + "";

		String calc = "C=\\begin{bmatrix}";
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(b[i][j] >= 0)
					calc += a[i][j] + "+" + b[i][j];
				else
					calc += a[i][j] + "" + b[i][j];

				if(j < (col - 1))
					calc += "&";
			}
			calc += "\\\\";
		}
		calc += "\\end{bmatrix}";

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Se \\(A+B=C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		addResolucao("\\(" + calc + "\\)");
		addResolucao("\\(C=" + AuxMatriz.matrizStr(c) + "\\)");
		addResolucao("\\(" + AuxMatriz.somaStr(c) + "\\)");
	}
}
