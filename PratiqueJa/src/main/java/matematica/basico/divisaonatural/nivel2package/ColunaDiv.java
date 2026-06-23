package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class ColunaDiv extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if (rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 2 + rand.nextInt(8);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(40);
		}
		int dividendo = a * b;

		addParagrafo("Calcule a seguinte divisão:");
		addParagrafo("\\(" + ResolucaoNatural.divisao(dividendo, b, false) + "\\)");
		gerarAlternativasInteiras(a);
		addResolucao("\\(" + ResolucaoNatural.divisao(dividendo, b, true) + "\\)");
	}
}
