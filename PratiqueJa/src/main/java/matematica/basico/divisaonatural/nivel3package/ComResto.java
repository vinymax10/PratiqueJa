package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;

public class ComResto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + rand.nextInt(40);
		int q = 100 + rand.nextInt(200);
		int r = 1 + rand.nextInt(b - 1);
		int a = b * q + r;

		addParagrafo("Calcule \\(" + a + " \\div " + b + "\\) e indique o quociente e o resto.");

		String correta = "Quociente \\(" + q + "\\), resto \\(" + r + "\\)";
		java.util.List<String> distratores = new java.util.ArrayList<>();
		distratores.add("Quociente \\(" + (q + 1) + "\\), resto \\(" + Math.max(0, r - b + 1) + "\\)");
		distratores.add("Quociente \\(" + (q - 1) + "\\), resto \\(" + (r + b) + "\\)");
		distratores.add("Quociente \\(" + q + "\\), resto \\(0\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Realizamos a divisão: \\(" + a + " \\div " + b + "\\).");
		addResolucao("Estimamos o quociente: \\(" + b + " \\times " + q + " = " + (b * q) + "\\).");
		addResolucao("Calculamos o resto: \\(" + a + " - " + (b * q) + " = " + r + "\\).");
		addResolucao("Como \\(" + r + " < " + b + "\\), a divisão termina.");
		addResolucao("Resultado: quociente \\(\\mathbf{" + q + "}\\), resto \\(\\mathbf{" + r + "}\\).");
		addResolucao("Prova real: \\(" + b + " \\times " + q + " + " + r + " = " + (b * q) + " + " + r + " = \\mathbf{" + a + "}\\)");
	}
}
