package matematica.avancado.matrizes.nivel2package;

import matematica.GeradorExercicio;

public class Matrizes7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int coef = 2 + rand.nextInt(5);  // coeficiente de x: 2..6
		int xVal = 1 + rand.nextInt(12); // resposta: x = 1..12
		int cst  = rand.nextInt(10);     // constante: 0..9
		int dVal = coef * xVal + cst;    // elemento a_{2,1} (valor conhecido)
		int a11  = rand.nextInt(21) - 10;
		int a22  = rand.nextInt(21) - 10;

		// a_{1,2} = coef·x + cst  (expressão com x)
		// a_{2,1} = dVal           (valor numérico)
		String a12expr = (cst == 0) ? coef + "x" : coef + "x + " + cst;

		String matDisplay = "\\begin{bmatrix}"
			+ a11 + " & " + a12expr + "\\\\"
			+ dVal + " & " + a22
			+ "\\end{bmatrix}";

		addParagrafo("Para que valor de \\(x\\) a matriz \\(A\\) é simétrica?");
		addParagrafo("\\(A = " + matDisplay + "\\)");
		gerarAlternativas("" + xVal);

		addResolucao("Matriz simétrica: \\(a_{i,j} = a_{j,i}\\) para todo \\(i, j\\).");
		addResolucao("Condição: \\(a_{1,2} = a_{2,1}\\):");
		addResolucao("\\(" + a12expr + " = " + dVal + "\\).");
		if (cst == 0)
		{
			addResolucao("\\(" + coef + "x = " + dVal
				+ " \\Rightarrow x = \\dfrac{" + dVal + "}{" + coef + "} = \\mathbf{" + xVal + "}\\)");
		}
		else
		{
			int rhs = dVal - cst;
			addResolucao("\\(" + coef + "x = " + dVal + " - " + cst + " = " + rhs
				+ " \\Rightarrow x = \\dfrac{" + rhs + "}{" + coef + "} = \\mathbf{" + xVal + "}\\)");
		}
	}
}
