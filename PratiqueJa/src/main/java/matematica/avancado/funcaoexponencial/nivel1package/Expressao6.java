package matematica.avancado.funcaoexponencial.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = (1/a)^x com expoente positivo => resultado fracionário
		int[] bases = {2, 3, 4};
		int a = bases[rand.nextInt(3)];
		int n = 1 + rand.nextInt(3); // 1, 2 ou 3
		int aPowN = (int) Math.pow(a, n);
		Racional resultado = new Racional(1, aPowN);

		String baseStr = "\\left(\\dfrac{1}{" + a + "}\\right)";

		addParagrafo("Dada \\(f(x) = " + baseStr + "^x\\), calcule \\(f(" + n + ")\\).");

		String res = "Substituindo \\(x = " + n + "\\): \\(\\\\\\)";
		res += "\\(f(" + n + ") = " + baseStr + "^{" + n + "} = \\dfrac{1^{" + n + "}}{" + a + "^{" + n + "}} = \\dfrac{1}{" + aPowN + "}\\\\";
		res += "f(" + n + ") = \\mathbf{" + resultado.toStringLatex() + "}\\)";

		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
