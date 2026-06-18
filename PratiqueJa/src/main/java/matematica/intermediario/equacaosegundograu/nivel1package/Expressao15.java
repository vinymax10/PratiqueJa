package matematica.intermediario.equacaosegundograu.nivel1package;

import matematica.Auxiliar;
import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(5);
		int c = -a * r * r;

		String eq = Auxiliar.getNumber(a, "x^2", true) + Auxiliar.getNumber(c, "", false) + "=0";

		String res = "\\(" + ParCor.formula("ax^2+c=0 \\Rightarrow x^2=-\\dfrac{c}{a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(" + Auxiliar.getNumber(a, "x^2", true) + "=" + (-c) + " = \\\\ \\)";
		res += "\\(x^2=\\dfrac{" + (-c) + "}{" + a + "}=" + (r * r) + "\\)" + "\\(\\\\\\)";
		res += "\\(x=\\pm\\sqrt{" + (r * r) + "}=\\pm " + r + "\\)" + "\\(\\\\\\)";
		res += "A raiz positiva é \\(x=\\mathbf{" + r + "}\\)";

		addParagrafo("Encontre a raiz positiva");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativasInteiras(r);
		setResolucao(res);
	}
}
