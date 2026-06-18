package matematica.avancado.matrizes.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.matrizes.AuxMatriz;

public class Matrizes14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// A = [[p, b], [c, x]];  det = p·x - b·c = 0  →  x = b·c/p
		// Garante x inteiro: b é múltiplo de p
		int p    = 1 + rand.nextInt(6);          // p = 1..6 (a_{1,1})
		int mult = 1 + rand.nextInt(6);          // b = p·mult → b/p = mult (inteiro)
		int b    = p * mult;
		if (rand.nextBoolean()) b = -b;          // b pode ser negativo
		int c    = 1 + rand.nextInt(9);          // c = 1..9 (a_{2,1})
		if (rand.nextBoolean()) c = -c;
		int xVal = (b / p) * c;                  // x = (b/p)·c = mult·c (inteiro)

		// Exibe a matriz com x simbólico na posição (2,2)
		String matDisplay = "\\begin{bmatrix}"
			+ p + " & " + b + "\\\\"
			+ c + " & x"
			+ "\\end{bmatrix}";

		addParagrafo("Para que valor de \\(x\\) temos \\(\\det A = 0\\)?");
		addParagrafo("\\(A = " + matDisplay + "\\)");
		gerarAlternativas("" + xVal);

		String res = "\\(\\det A = a_{1,1} \\cdot a_{2,2} - a_{1,2} \\cdot a_{2,1} = 0\\). \\(\\\\\\)";
		res += "\\(" + p + " \\cdot x - "
			+ AuxMatriz.parenteses(b) + " \\cdot " + AuxMatriz.parenteses(c) + " = 0\\). \\(\\\\\\)";
		long bc = (long) b * c;
		res += "\\(" + p + "x = " + bc + "\\). \\(\\\\\\)";
		res += "\\(x = \\dfrac{" + bc + "}{" + p + "} = \\mathbf{" + xVal + "}\\)";
		setResolucao(res);
	}
}
