package matematica.intermediario.potenciacao.nivel3package;

import matematica.GeradorExercicio;

public class Potenciacao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(19);

		int p1 = 2 + rand.nextInt(9);
		int p2 = 2 + rand.nextInt(9);

		String texto = a + "^{" + p1 + "} \\div " + a + "^{" + p2 + "}=" + a + "^{x}";

		String resolucao = a + "^{" + p1 + "-" + p2 + "}=" + a + "^{x}\\\\";
		resolucao += a + "^{" + (p1 - p2) + "}=" + a + "^{x}\\\\";
		resolucao += "x=" + (p1 - p2);

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + (p1 - p2));
		setResolucao("\\(" + resolucao + "\\)");
	}
}
