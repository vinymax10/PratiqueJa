package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class QuantasVezes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int q = 2 + rand.nextInt(9);
		int a = b * q;

		int tipo = rand.nextInt(4);
		switch (tipo)
		{
			case 0:
				addParagrafo("Quantas vezes o número \\(" + b + "\\) cabe no número \\(" + a + "\\)?");
				break;
			case 1:
				addParagrafo("Um copo comporta \\(" + b + "\\) ml de suco. Quantas vezes precisamos enchê-lo para obter \\(" + a + "\\) ml no total?");
				break;
			case 2:
				addParagrafo("Uma caixa comporta \\(" + b + "\\) livros. Quantas caixas cheias precisamos para guardar \\(" + a + "\\) livros?");
				break;
			default:
				addParagrafo("Cada passo de Lucas mede \\(" + b + "\\) palmos. Quantos passos ele dá para percorrer \\(" + a + "\\) palmos?");
				break;
		}

		gerarAlternativasInteiras(q);

		String res = "\"Quantas vezes \\(" + b + "\\) cabe em \\(" + a + "\\)\" é a ideia central da divisão: \\(\\\\\\)";
		res += "\\(" + a + " \\div " + b + " = \\mathbf{" + q + "}\\) \\(\\\\\\)";
		res += "Verificação: \\(" + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
