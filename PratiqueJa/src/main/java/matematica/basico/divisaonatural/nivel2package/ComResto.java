package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;

public class ComResto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 3 + rand.nextInt(8);
		int q = 10 + rand.nextInt(15);
		int r = 1 + rand.nextInt(b - 1);
		int a = b * q + r;

		addParagrafo("Calcule \\(" + a + " \\div " + b + "\\) e indique o quociente e o resto.");

		int[] opcoes = {q, q + 1, q - 1, q + 2};
		String correta = "Quociente \\(" + q + "\\), resto \\(" + r + "\\)";
		java.util.List<String> distratores = new java.util.ArrayList<>();
		distratores.add("Quociente \\(" + (q + 1) + "\\), resto \\(" + (r == 0 ? 1 : r - 1) + "\\)");
		distratores.add("Quociente \\(" + (q - 1) + "\\), resto \\(" + (r + b) + "\\)");
		distratores.add("Quociente \\(" + q + "\\), resto \\(0\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Realizamos a divisão: \\(" + a + " \\div " + b + "\\). \\(\\\\\\)";
		res += "Estimamos o quociente: \\(" + b + " \\times " + q + " = " + (b * q) + "\\). \\(\\\\\\)";
		res += "Calculamos o resto: \\(" + a + " - " + (b * q) + " = " + r + "\\). \\(\\\\\\)";
		res += "Como \\(" + r + " < " + b + "\\), a divisão termina. \\(\\\\\\)";
		res += "Resultado: quociente \\(\\mathbf{" + q + "}\\), resto \\(\\mathbf{" + r + "}\\). \\(\\\\\\)";
		res += "Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
