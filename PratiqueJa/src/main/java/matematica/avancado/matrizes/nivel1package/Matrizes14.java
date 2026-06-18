package matematica.avancado.matrizes.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int lin = 2 + rand.nextInt(2); // 2 ou 3 linhas
		int col = 2 + rand.nextInt(2); // 2 ou 3 colunas
		int[][] a = AuxMatriz.contruirMatriz(lin, col, 15);

		int posI = rand.nextInt(lin);
		int posJ = rand.nextInt(col);
		int correct = a[posI][posJ];

		addParagrafo("Qual o valor de \\(a_{" + (posI + 1) + "," + (posJ + 1) + "}\\)?");
		addParagrafo("\\(A = " + AuxMatriz.matrizStr(a) + "\\)");
		gerarAlternativas("" + correct);

		String res = "\\(a_{i,j}\\) é o elemento da linha \\(i\\), coluna \\(j\\). \\(\\\\\\)";
		res += "Destacando \\(a_{" + (posI + 1) + "," + (posJ + 1) + "}\\): \\(\\\\\\)";
		res += "\\(" + AuxMatriz.matrizStr(a, posI, posJ) + "\\). \\(\\\\\\)";
		res += "\\(a_{" + (posI + 1) + "," + (posJ + 1) + "} = \\mathbf{" + correct + "}\\)";
		setResolucao(res);
	}
}
