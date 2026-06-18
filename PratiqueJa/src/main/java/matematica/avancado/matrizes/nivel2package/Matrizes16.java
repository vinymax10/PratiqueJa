package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(1, size, 50);
		int[][] aT = AuxMatriz.contruirTransposta(a);

		String resolucao = "";
		resolucao += "A \\cdot A^T=";
		resolucao += AuxMatriz.matrizStr(a) + "\\cdot" + AuxMatriz.matrizStr(aT) + "=\\\\";
		int resultado = 0;
		for(int i = 0; i < size; i++)
		{
			if(a[0][i] >= 0)
				resolucao += a[0][i] + "^2";
			else
				resolucao += "(" + a[0][i] + ")^2";
			if(i < (size - 1))
				resolucao += "+";
		}
		resolucao += "=";

		for(int i = 0; i < size; i++)
		{
			resolucao += a[0][i] * a[0][i];
			resultado += a[0][i] * a[0][i];
			if(i < (size - 1))
				resolucao += "+";
		}
		resolucao += "=" + resultado;

		String resultadoCorreto = resultado + "";

		String texto = "A=" + AuxMatriz.matrizStr(a);

		addParagrafo("Qual o resultado de \\(A \\cdot A^T\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
