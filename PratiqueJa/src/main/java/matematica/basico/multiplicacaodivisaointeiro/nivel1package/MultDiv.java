package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class MultDiv extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		if (rand.nextBoolean()) a *= -1;

		int b = 1 + rand.nextInt(10);
		if (rand.nextBoolean()) b *= -1;

		boolean divisao = rand.nextBoolean();
		int correto;
		String enunciado;
		String[] resolucao;

		if (divisao)
		{
			enunciado = (a * b) + " \\div " + b + " =";
			correto = a;
			resolucao = ResolucaoMDInteiro.divisao(a * b, b);
		}
		else
		{
			enunciado = a + " \\times " + b + " =";
			correto = a * b;
			resolucao = ResolucaoMDInteiro.multiplicacao(a, b);
		}

		addParagrafo("Calcule o valor da seguinte expressão:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativasInteirasComNegativos(correto);
		for(String passo : resolucao)
			addResolucao(passo);
	}
}
