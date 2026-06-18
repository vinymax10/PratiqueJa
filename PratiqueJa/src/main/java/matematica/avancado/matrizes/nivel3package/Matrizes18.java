package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.Racional;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2;

		int[][] a = AuxMatriz.contruirMatriz(size, size, 20);
		int determinanteA = AuxMatriz.determinante(a);

		while(determinanteA == 0)
		{
			a = AuxMatriz.contruirMatriz(size, size, 20);
			determinanteA = AuxMatriz.determinante(a);
		}

		int[][] parcialInversa = { { a[1][1], -a[0][1] },
		{ -a[1][0], a[0][0] } };

		Racional inversaA[][] = new Racional[size][size];
		Racional fator = new Racional(1, determinanteA);
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
			{
				inversaA[i][j] = fator.mult(new Racional(parcialInversa[i][j]));
				inversaA[i][j].fatoracao(2);
			}

		String resultadoCorreto = AuxMatriz.somaRacional(inversaA) + "";

		String resolucao = "";
		resolucao += ParCor.formula(AuxMatriz.formulaInversa()) + " \\\\";
		resolucao += ParCor.formula("\\det~ A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1}") + "\\\\";

		resolucao += "\\det~ A = " + a[0][0] + " \\cdot " + AuxMatriz.parenteses(a[1][1]) +
		"-" + AuxMatriz.parenteses(a[0][1]) + " \\cdot " + AuxMatriz.parenteses(a[1][0]) + "\\\\";
		resolucao += "\\det~ A =" + (a[0][0] * a[1][1]) + "-" + AuxMatriz.parenteses(a[0][1] * a[1][0]) + "=" + determinanteA + "\\\\";

		resolucao += "A^{-1} =" + fator.showDfrac() + "\\cdot " + AuxMatriz.matrizStr(parcialInversa) + "\\\\";

		resolucao += "A^{-1}=\\begin{bmatrix}";

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				resolucao += fator.showDfrac() + " \\cdot " + AuxMatriz.parenteses(parcialInversa[i][j]);

				if(j < (size - 1))
					resolucao += "&";
			}
			resolucao += "\\\\";
		}

		resolucao += "\\end{bmatrix}" + "\\\\ \\\\";

		resolucao += "A^{-1}=" + AuxMatriz.matrizStrRacional(inversaA) + "\\\\ \\\\";
		resolucao += AuxMatriz.somaStrRacional(inversaA);

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Qual a soma dos elementos de \\( A^{-1} \\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
