package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int lin = 2 + rand.nextInt(2);
		int col = 2 + rand.nextInt(2);
		int k = 2 + rand.nextInt(4); // 2..5
		if (rand.nextBoolean()) k = -k;

		int[][] a  = AuxMatriz.contruirMatriz(lin, col, 8);
		int[][] b  = AuxMatriz.contruirMatriz(lin, col, 8);
		int[][] kA = new int[lin][col];
		int[][] c  = new int[lin][col];
		for (int i = 0; i < lin; i++)
			for (int j = 0; j < col; j++)
			{
				kA[i][j] = k * a[i][j];
				c[i][j]  = kA[i][j] + b[i][j];
			}

		int soma = AuxMatriz.soma(c);

		addParagrafo("Se \\(" + k + " \\cdot A + B = C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b) + "\\)");
		gerarAlternativas("" + soma);

		// Passo 1: k·A
		StringBuilder kAstep = new StringBuilder("\\begin{bmatrix}");
		for (int i = 0; i < lin; i++)
		{
			for (int j = 0; j < col; j++)
			{
				kAstep.append(k).append(" \\cdot ").append(AuxMatriz.parenteses(a[i][j]));
				if (j < col - 1) kAstep.append("&");
			}
			kAstep.append("\\\\");
		}
		kAstep.append("\\end{bmatrix}");

		// Passo 2: kA + B mostrado elemento a elemento
		StringBuilder cStep = new StringBuilder("\\begin{bmatrix}");
		for (int i = 0; i < lin; i++)
		{
			for (int j = 0; j < col; j++)
			{
				if (b[i][j] >= 0)
					cStep.append(kA[i][j]).append("+").append(b[i][j]);
				else
					cStep.append(kA[i][j]).append("").append(b[i][j]);
				if (j < col - 1) cStep.append("&");
			}
			cStep.append("\\\\");
		}
		cStep.append("\\end{bmatrix}");

		// Soma dos elementos
		StringBuilder sumParts = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < lin; i++)
			for (int j = 0; j < col; j++)
			{
				if (!first && c[i][j] >= 0) sumParts.append("+");
				sumParts.append(c[i][j]);
				first = false;
			}

		String res = "Calculando \\(" + k + "A\\): \\(\\\\\\)";
		res += "\\(" + k + "A = " + kAstep + " = " + AuxMatriz.matrizStr(kA) + "\\). \\(\\\\\\)";
		res += "Somando \\(B\\): \\(\\\\\\)";
		res += "\\(C = " + cStep + " = " + AuxMatriz.matrizStr(c) + "\\). \\(\\\\\\)";
		res += "Soma dos elementos de \\(C\\): \\(\\\\\\)";
		res += "\\(" + sumParts + " = \\mathbf{" + soma + "}\\)";
		setResolucao(res);
	}
}
