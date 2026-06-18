package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes12 extends GeradorExercicio
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
				c[i][j] = a[i][j] - bT[i][j];

		String resultadoCorreto = AuxMatriz.soma(c) + "";
		String resolucao = "";
		resolucao += "B^T=" + AuxMatriz.matrizStr(bT) + "\\\\";

		resolucao += "C=\\begin{bmatrix}";
		int lin = size;
		int col = size;

		for(int i = 0; i < lin; i++)
		{
			for(int j = 0; j < col; j++)
			{
				resolucao += a[i][j] + "-" + AuxMatriz.parenteses(bT[i][j]);

				if(j < (col - 1))
					resolucao += "&";
			}
			resolucao += "\\\\";
		}
		resolucao += "\\end{bmatrix}" + "\\\\ \\\\";
		resolucao += "C=" + AuxMatriz.matrizStr(c) + "\\\\ \\\\";
		resolucao += AuxMatriz.somaStr(c);

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Se \\(A-B^T=C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
