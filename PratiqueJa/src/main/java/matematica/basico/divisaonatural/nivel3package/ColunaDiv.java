package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class ColunaDiv extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(900);
		int b = 10 + rand.nextInt(90);
		int dividendo = a * b;

		addParagrafo("Calcule a seguinte divisão:");
		addParagrafo("\\(" + ResolucaoNatural.divisao(dividendo, b, false) + "\\)");
		gerarAlternativasInteiras(a);
		addResolucao("\\(" + ResolucaoNatural.divisao(dividendo, b, true) + "\\)");
	}
}
