package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int p = rand.nextBoolean() ? 2 : 3;     // base comum
		int m = 2 + rand.nextInt(3);             // expoente esquerdo: 2, 3, 4
		int n = 2 + rand.nextInt(5);             // expoente direito: 2..6
		while (n == m) n = 2 + rand.nextInt(5);

		int leftBase  = (int) Math.pow(p, m);   // p^m: 4, 8, 9, 16...
		int rightVal  = (int) Math.pow(p, n);   // p^n

		Racional resultado = new Racional(n, m);
		resultado.fatoracao(2);

		addParagrafo("Resolva a equação \\(" + leftBase + "^x = " + rightVal + "\\).");

		gerarAlternativas(resultado);
		addResolucao("Reescrevemos ambos os lados com base \\(" + p + "\\):");
		addResolucao("\\(" + leftBase + " = " + p + "^{" + m + "}\\) \\(\\quad\\) e \\(\\quad\\) \\(" + rightVal + " = " + p + "^{" + n + "}\\)");
		addResolucao("Substituindo: \\((" + p + "^{" + m + "})^x = " + p + "^{" + n + "}\\)");
		addResolucao("\\(" + p + "^{" + m + "x} = " + p + "^{" + n + "}\\)");
		addResolucao("Igualando os expoentes: \\(" + m + "x = " + n + "\\)");
		addResolucao("\\(x = \\dfrac{" + n + "}{" + m + "} = \\mathbf{" + resultado.toStringLatex() + "}\\)");
	}
}
