package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.GeradorExercicio;

public class ValorAbsoluto extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(19);
		boolean neg = rand.nextBoolean();
		int x = neg ? -a : a;

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Qual é o valor absoluto (módulo) de \\(" + x + "\\)?");
				break;
			case 1:
				addParagrafo("Qual é o valor de \\(|" + x + "|\\)?");
				break;
			default:
				addParagrafo("Na reta numérica dos inteiros, qual é a distância de \\(" + x + "\\) ao zero?");
				break;
		}

		gerarAlternativasInteiras(a);

		addResolucao("O módulo (valor absoluto) representa a distância ao zero na reta numérica, sempre positivo ou nulo.");
		addResolucao("\\(|" + x + "| = \\mathbf{" + a + "}\\)");
	}
}
