package matematica.basico.multiplicacaonatural.nivel3package;

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
			a = 100 + rand.nextInt(900);
			b = 100 + rand.nextInt(900);
		}
		else
		{
			a = 100 + rand.nextInt(900);
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
		addResolucao("\\(" + ResolucaoNatural.multiplicacao(a, b, true) + "\\)");
	}
}
