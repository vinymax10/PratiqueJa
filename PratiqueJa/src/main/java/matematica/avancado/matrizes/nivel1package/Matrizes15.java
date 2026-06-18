package matematica.avancado.matrizes.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Força matriz não-quadrada para tornar a transposta mais evidente
		int lin = 2 + rand.nextInt(2); // 2 ou 3 linhas de A
		int col = (lin == 2) ? 3 : 2;  // 3 ou 2 colunas → A^T tem dimensão col×lin
		int[][] a  = AuxMatriz.contruirMatriz(lin, col, 15);
		int[][] aT = AuxMatriz.contruirTransposta(a); // dimensão col×lin

		// Escolher posição (i,j) em A^T
		int posI   = rand.nextInt(col); // linha em A^T ∈ [0, col-1]
		int posJ   = rand.nextInt(lin); // coluna em A^T ∈ [0, lin-1]
		int correct = aT[posI][posJ];   // = a[posJ][posI]

		addParagrafo("Qual o valor de \\((A^T)_{" + (posI + 1) + "," + (posJ + 1) + "}\\)?");
		addParagrafo("\\(A = " + AuxMatriz.matrizStr(a) + "\\)");
		gerarAlternativas("" + correct);

		String res = "A transposta troca linhas por colunas: \\((A^T)_{i,j} = a_{j,i}\\). \\(\\\\\\)";
		res += "\\(A^T = " + AuxMatriz.matrizStr(aT) + "\\). \\(\\\\\\)";
		res += "\\((A^T)_{" + (posI + 1) + "," + (posJ + 1) + "}"
			+ " = a_{" + (posJ + 1) + "," + (posI + 1) + "}"
			+ " = \\mathbf{" + correct + "}\\)";
		setResolucao(res);
	}
}
