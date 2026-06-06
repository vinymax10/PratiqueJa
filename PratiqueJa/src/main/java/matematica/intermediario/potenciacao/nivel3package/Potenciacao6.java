package matematica.intermediario.potenciacao.nivel3package;

import matematica.GeradorExercicio;

public class Potenciacao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9);

		int potenciaMaxima = 6;
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(a)), potenciaMaxima);
		int p = 2 + rand.nextInt(maxBase + 1);

		int resultado = (int) Math.pow(a, p);

		double divisor = 2 + 2 * rand.nextDouble();

		int termo1 = (int) ((double) resultado / divisor);
		int termo2 = resultado - termo1;
		String texto = termo1 + "+" + termo2 + "=" + a + "^{x}";

		String resolucao = resultado + "=" + a + "^{x}\\\\";
		resolucao += a + "^{" + (p) + "}=" + a + "^{x}\\\\";
		resolucao += "x=" + (p);

		addParagrafo("Qual o valor de \\(x\\)?");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + p);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
