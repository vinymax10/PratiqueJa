package matematica.intermediario.equacoes.nivel2package;

import matematica.GeradorExercicio;

public class Equacao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a(bx + c) = d  →  x = (d/a - c) / b
		int a = 2 + rand.nextInt(4);        // 2..5
		int b = 2 + rand.nextInt(4);        // 2..5
		int c = 1 + rand.nextInt(9);        // 1..9
		int xResult = 1 + rand.nextInt(8);  // 1..8
		int d = a * (b * xResult + c);      // garante resultado inteiro

		String var = "" + (char) (97 + rand.nextInt(25));

		int ab = a * b;
		int ac = a * c;
		String bStr = b > 1 ? b + "" : "";

		addParagrafo("Encontre \\(" + var + "\\)");
		addParagrafo("\\(" + a + "(" + bStr + var + " + " + c + ") = " + d + "\\)");

		String res = "Aplicando a propriedade distributiva: \\(\\\\\\)";
		res += "\\(" + ab + var + " + " + ac + " = " + d + "\\). \\(\\\\\\)";
		res += "Isolando o termo com \\(" + var + "\\): \\(\\\\\\)";
		res += "\\(" + ab + var + " = " + d + " - " + ac + " = " + (d - ac) + "\\). \\(\\\\\\)";
		res += "\\(" + var + " = \\dfrac{" + (d - ac) + "}{" + ab + "} = " + xResult + "\\).";

		setResolucao(res);
		gerarAlternativas("" + xResult);
	}
}
