package matematica.basico.adicaosubtracaointeiro.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ResolucaoASInteiro;

public class SomaDoisTermos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(1000);
		if (rand.nextBoolean()) a *= -1;

		int b = 1 + rand.nextInt(1000);
		if (rand.nextBoolean()) b *= -1;

		int correto = a + b;

		addParagrafo("Calcule o valor da seguinte expressão:");
		addParagrafo("\\(" + Auxiliar.getNumber(a, "", true) + Auxiliar.getNumber(b, "", false) + " = \\,?\\)");
		gerarAlternativasInteirasComNegativos(correto);
		for (String passo : ResolucaoASInteiro.soma(a, b))
			addResolucao(passo);
	}
}
