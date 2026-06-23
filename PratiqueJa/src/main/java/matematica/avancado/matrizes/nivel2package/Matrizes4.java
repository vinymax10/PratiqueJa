package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(size, size, 50);
		int[][] b = AuxMatriz.contruirMatriz(size, size, 50);
		int[][] bT = AuxMatriz.contruirTransposta(b);

		int c[][] = new int[size][size];
		for(int i = 0; i < c.length; i++)
			for(int j = 0; j < c.length; j++)
				c[i][j] = a[i][j] + bT[i][j];

		String resultadoCorreto = AuxMatriz.soma(c) + "";
		int lin = size;
		int col = size;

		String calc = "C=\\begin{bmatrix}";
		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				if(bT[i][j] >= 0)
					calc += a[i][j] + "+" + bT[i][j];
				else
					calc += a[i][j] + "" + bT[i][j];

				if(j < (col - 1))
					calc += "&";
			}
			calc += "\\\\";
		}
		calc += "\\end{bmatrix}";

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Se \\(A+B^T=C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		addResolucao("\\(B^T=" + AuxMatriz.matrizStr(bT) + "\\)");
		addResolucao("\\(" + calc + "\\)");
		addResolucao("\\(C=" + AuxMatriz.matrizStr(c) + "\\)");
		addResolucao("\\(" + AuxMatriz.somaStr(c) + "\\)");
	}
}
