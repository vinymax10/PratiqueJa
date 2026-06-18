package matematica.intermediario.equacaosegundograu.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x2 = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) x2 *= -1;
		int a = 1 + rand.nextInt(5);
		int b = -a * x2;

		String eq = Auxiliar.getNumber(a, "x^2", true) + Auxiliar.getNumber(b, "x", false) + "=0";
		String inner = Auxiliar.getNumber(a, "x", true) + Auxiliar.getNumber(b, "", false);

		String res = "Fatorando \\(x\\) em evidência: \\(\\\\\\)";
		res += "\\(x\\left(" + inner + "\\right)=0\\)" + "\\(\\\\\\)";
		res += "Produto nulo → \\(x_1=0\\) ou \\(" + inner + "=0\\)" + "\\(\\\\\\)";
		res += "\\(" + Auxiliar.getNumber(a, "x_2", true) + Auxiliar.getNumber(b, "", false) + "=0\\)" + "\\(\\\\\\)";
		res += "\\(" + a + "x_2=" + (-b) + "\\)" + "\\(\\\\\\)";
		res += "\\(x_2=\\dfrac{" + (-b) + "}{" + a + "}=\\mathbf{" + x2 + "}\\)";

		addParagrafo("Encontre a raiz não nula");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas("" + x2);
		setResolucao(res);
	}
}
