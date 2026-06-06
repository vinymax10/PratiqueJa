package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;

public class MultiplicacaoNaturalNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		int correto = a * b;

		addParagrafo("Calcule o valor da seguinte multiplicação:");
		addParagrafo("\\(" + a + " \\times " + b + " = \\,?\\)");
		gerarAlternativasInteiras(correto);
		setResolucao("\\(" + a + " \\times " + b + " = " + correto + "\\)");
	}
}
