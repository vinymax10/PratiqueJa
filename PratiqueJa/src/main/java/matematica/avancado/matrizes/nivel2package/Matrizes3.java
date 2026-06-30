package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int size = 2 + rand.nextInt(2);

		int[][] a = AuxMatriz.contruirMatriz(1, size, 50);
		int[][] b = AuxMatriz.contruirMatriz(size, 1, 50);

		int resultado = 0;
		for(int i = 0; i < size; i++)
			resultado += a[0][i] * b[i][0];

		String resultadoCorreto = resultado + "";

		String texto = "A=" + AuxMatriz.matrizStr(a) + ",~B=" + AuxMatriz.matrizStr(b);

		addParagrafo("Qual o resultado de \\(A \\cdot B\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);

		addResolucao("\\(A \\cdot B=" + AuxMatriz.matrizStr(a) + "\\cdot" + AuxMatriz.matrizStr(b) + "=\\)");

		String prod = "";
		for(int i = 0; i < size; i++)
		{
			prod += AuxMatriz.parenteses(a[0][i]) + "\\cdot" + AuxMatriz.parenteses(b[i][0]);

			if(i < (size - 1))
				prod += "+";
		}
		prod += "=";
		addResolucao("\\(" + prod + "\\)");

		String soma = "";
		for(int i = 0; i < size; i++)
		{
			if(i > 0 && a[0][i] * b[i][0] >= 0)
				soma += "+";

			soma += a[0][i] * b[i][0];
		}
		soma += "=\\mathbf{" + resultado + "}";
		addResolucao("\\(" + soma + "\\)");
	}
}
