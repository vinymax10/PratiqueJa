package matematica.avancado.funcaoexponencial.nivel1package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] bases = {2, 3, 4};
		int a = bases[rand.nextInt(3)];
		int n = 1 + rand.nextInt(2); // 1 ou 2
		int negX = -n;
		int aPowN = (int) Math.pow(a, n);
		Racional resultado = new Racional(1, aPowN);

		addParagrafo("Dada \\(f(x) = " + a + "^x\\), calcule \\(f(" + negX + ")\\).");

		String res = "Usando a propriedade \\(a^{-n} = \\dfrac{1}{a^n}\\): \\(\\\\\\)";
		res += "\\(f(" + negX + ") = " + a + "^{" + negX + "} = \\dfrac{1}{" + a + "^{" + n + "}} = \\dfrac{1}{" + aPowN + "}\\) \\(\\\\\\)";
		res += "\\(f(" + negX + ") = \\mathbf{" + resultado.toStringLatex() + "}\\)";

		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
