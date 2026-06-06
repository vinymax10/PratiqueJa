package matematica.basico.multiplicacaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class MultiplicacaoNaturalNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if(rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 1 + rand.nextInt(9);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(90);
		}
		if(a < b)
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
