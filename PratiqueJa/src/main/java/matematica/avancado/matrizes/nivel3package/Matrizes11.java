package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2;

		int[][] a = AuxMatriz.contruirMatriz(size, size, 50);
		int[][] b = AuxMatriz.contruirMatriz(size, size, 50);

		int c[][] = new int[size][size];
		for(int i = 0; i < c.length; i++)
			for(int j = 0; j < c.length; j++)
			{
				c[i][j] = 0;
				for(int k = 0; k < size; k++)
					c[i][j] += a[i][k] * b[k][j];
			}

		String resultadoCorreto = AuxMatriz.soma(c) + "";

		String resolucao = ParCor.formula("c_{i,j}= \\sum_{1}^{k} A_{i,k} \\cdot B_{k,j}") + "\\\\";
		resolucao += "i, j \\in \\{1,2\\}, \\quad k=" + size + "\\\\";

		for(int i = 0; i < size; i++)
			for(int j = 0; j < c.length; j++)
				resolucao += AuxMatriz.parcialMult(a, b, i, j) + "\\\\";

		resolucao += AuxMatriz.somaStr(c);

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Se \\(A \\cdot B=C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
