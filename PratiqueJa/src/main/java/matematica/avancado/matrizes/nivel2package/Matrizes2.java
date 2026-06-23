package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(1, size, 50);
		int[][] aT = AuxMatriz.contruirTransposta(a);

		int resultado = 0;
		for(int i = 0; i < size; i++)
			resultado += a[0][i] * a[0][i];

		String resultadoCorreto = resultado + "";

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Qual o resultado de \\(A \\cdot A^T\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		addResolucao("\\(A \\cdot A^T=" + AuxMatriz.matrizStr(a) + "\\cdot" + AuxMatriz.matrizStr(aT) + "=\\)");

		String passo = "";
		for(int i = 0; i < size; i++)
		{
			if(a[0][i] >= 0)
				passo += a[0][i] + "^2";
			else
				passo += "(" + a[0][i] + ")^2";
			if(i < (size - 1))
				passo += "+";
		}
		passo += "=";
		for(int i = 0; i < size; i++)
		{
			passo += a[0][i] * a[0][i];
			if(i < (size - 1))
				passo += "+";
		}
		passo += "=" + resultado;
		addResolucao("\\(" + passo + "\\)");
	}
}
