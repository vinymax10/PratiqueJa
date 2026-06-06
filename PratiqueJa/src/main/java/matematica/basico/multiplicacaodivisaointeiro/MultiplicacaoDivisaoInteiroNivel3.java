package matematica.basico.multiplicacaodivisaointeiro;

import matematica.GeradorExercicio;

public class MultiplicacaoDivisaoInteiroNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(1000);
		if(rand.nextBoolean())
			a *= -1;

		int b = 1 + rand.nextInt(1000);
		if(rand.nextBoolean())
			b *= -1;

		boolean divisao = rand.nextBoolean();
		int correto;
		String enunciado, resolucao;
		if(divisao)
		{
			enunciado = (a * b) + "\\div " + b + " =";
			correto = a;
			resolucao = ResolucaoMDInteiro.divisao(a * b, b);
		}
		else
		{
			enunciado = a + "\\times " + b + " =";
			correto = a * b;
			resolucao = ResolucaoMDInteiro.multiplicacao(a, b);
		}

		addParagrafo("Calcule o valor da seguinte expressão:");
		addParagrafo("\\(" + enunciado + "\\)");
		gerarAlternativasInteirasComNegativos(correto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
