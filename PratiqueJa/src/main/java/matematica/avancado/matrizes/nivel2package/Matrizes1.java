package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(size, size, 50);
		int[][] b = AuxMatriz.contruirMatriz(size, size, 50);
		int posI = rand.nextInt(size);
		int posJ = rand.nextInt(size);

		int c[][] = new int[size][size];
		for(int i = 0; i < c.length; i++)
			for(int j = 0; j < c.length; j++)
			{
				c[i][j] = 0;
				for(int k = 0; k < size; k++)
					c[i][j] += a[i][k] * b[k][j];
			}

		String resultadoCorreto = c[posI][posJ] + "";

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Se \\(A \\cdot B=C\\), qual o valor de \\(c_{" + (posI + 1) + "," + (posJ + 1) + "}\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		// Resolução: uma linha (parágrafo) por passo — sem montar string única com "\\".
		addResolucao("\\(" + ParCor.formula("c_{i,j}= \\sum_{1}^{k} A_{i,k} \\cdot B_{k,j}") + "\\)");
		addResolucao("\\(i=" + (posI + 1) + ", \\quad j=" + (posJ + 1) + ", \\quad k=" + size + "\\)");

		String produto = "c_{" + (posI + 1) + "," + (posJ + 1) + "}=";
		for(int i = 0; i < size; i++)
		{
			produto += AuxMatriz.parenteses(a[posI][i]) + "\\cdot" + AuxMatriz.parenteses(b[i][posJ]);
			if(i < (size - 1))
				produto += "+";
		}
		addResolucao("\\(" + produto + "\\)");

		String soma = "c_{" + (posI + 1) + "," + (posJ + 1) + "}=";
		for(int i = 0; i < size; i++)
		{
			if(i > 0 && (a[posI][i] * b[i][posJ]) >= 0)
				soma += "+";
			soma += a[posI][i] * b[i][posJ];
		}
		soma += "=\\mathbf{" + resultadoCorreto + "}";
		addResolucao("\\(" + soma + "\\)");
	}
}
