package matematica.basico.racionais.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Racionais4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);  // 1..10
		int b = 1 + rand.nextInt(10);  // 1..10
		while(a == b) b = 1 + rand.nextInt(10);
		int d = 2 + rand.nextInt(9);   // denominador 2..10

		String f1 = "\\dfrac{" + a + "}{" + d + "}";
		String f2 = "\\dfrac{" + b + "}{" + d + "}";

		addParagrafo("Qual das frações é maior?");
		addParagrafo("\\(" + f1 + "\\quad\\) e \\(\\quad" + f2 + "\\)");

		boolean f1EhMaior = a > b;
		String correta   = f1EhMaior ? "\\(" + f1 + "\\)" : "\\(" + f2 + "\\)";
		String incorreta = f1EhMaior ? "\\(" + f2 + "\\)" : "\\(" + f1 + "\\)";
		embaralharEAdicionarAlternativas(correta,
			Arrays.asList(incorreta, "São iguais", "Não é possível determinar"));

		String maior = f1EhMaior ? "" + a : "" + b;
		String menor = f1EhMaior ? "" + b : "" + a;
		String fracMaior = f1EhMaior ? f1 : f2;
		addResolucao("Denominadores iguais: basta comparar os numeradores.");
		addResolucao("\\(" + maior + " > " + menor + "\\), portanto \\(" + fracMaior + "\\) é a maior.");
	}
}
