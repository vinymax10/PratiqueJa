package matematica.avancado.logaritmo;

import matematica.GeradorExercicio;

public class LogaritmoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(9);
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(c)), 10);
		int x = 1 + rand.nextInt(maxBase + 1);
		int a = (int) Math.pow(c, x);

		addParagrafo("Calcule o valor do logaritmo:");
		addParagrafo("\\(\\log_{" + c + "}" + a + " = \\,?\\)");
		gerarAlternativas("" + x);
		setResolucao("\\(" + ResolucaoLogaritmo.resolucao1(a, c, x) + "\\)");
	}
}
