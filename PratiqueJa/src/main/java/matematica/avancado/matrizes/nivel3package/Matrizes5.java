package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] a  = AuxMatriz.contruirMatriz(2, 2, 9);
		int[][] a2 = new int[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
			{
				a2[i][j] = 0;
				for (int k = 0; k < 2; k++)
					a2[i][j] += a[i][k] * a[k][j];
			}

		int soma = AuxMatriz.soma(a2);

		addParagrafo("Se \\(A^2 = A \\cdot A = C\\), qual a soma dos elementos de \\(C\\)?");
		addParagrafo("\\(A = " + AuxMatriz.matrizStr(a) + "\\)");
		gerarAlternativas("" + soma);

		addResolucao("\\(A^2 = A \\cdot A\\):");
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				addResolucao("\\(" + AuxMatriz.parcialMult(a, a, i, j) + "\\)");

		addResolucao("\\(A^2 = " + AuxMatriz.matrizStr(a2) + "\\).");

		StringBuilder sumParts = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
			{
				if (!first && a2[i][j] >= 0) sumParts.append("+");
				sumParts.append(a2[i][j]);
				first = false;
			}
		addResolucao("Soma dos elementos:");
		addResolucao("\\(" + sumParts + " = \\mathbf{" + soma + "}\\)");
	}
}
