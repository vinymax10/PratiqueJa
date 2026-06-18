package matematica.basico.multiplicacaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class Calculo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if (rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 1 + rand.nextInt(9);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(90);
		}
		if (a < b)
		{
			int aux = a;
			a = b;
			b = aux;
		}

		addParagrafo("Calcule a seguinte multiplicação:");
		addParagrafo("\\(" + ResolucaoNatural.multiplicacao(a, b, false) + "\\)");
		gerarAlternativasInteiras(a * b);
		setResolucao("\\(" + ResolucaoNatural.multiplicacao(a, b, true) + "\\)");
	}
}
