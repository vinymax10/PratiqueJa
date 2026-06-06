package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;

public class DivisaoNaturalNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		int dividendo = a * b;

		addParagrafo("Calcule o valor da seguinte divisão:");
		addParagrafo("\\(" + dividendo + " \\div " + b + " = \\,?\\)");
		gerarAlternativasInteiras(a);
		setResolucao("\\(" + dividendo + " \\div " + b + " = " + a + "\\)");
	}
}
