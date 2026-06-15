package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 3 + rand.nextInt(11);
		int b = 3 + rand.nextInt(11);
		int produto = a * b;

		int tipo = rand.nextInt(6);
		String enunciado;
		switch (tipo)
		{
			case 0:
				enunciado = "Uma escola comprou \\(" + a + "\\) caixas de lápis com \\(" + b
					+ "\\) lápis em cada caixa. Quantos lápis foram comprados no total?";
				break;
			case 1:
				enunciado = "Um jardim tem \\(" + a + "\\) fileiras com \\(" + b
					+ "\\) flores em cada fileira. Quantas flores há no jardim?";
				break;
			case 2:
				enunciado = "Um ônibus tem \\(" + a + "\\) fileiras de assentos com \\(" + b
					+ "\\) assentos em cada fileira. Quantos assentos há no ônibus?";
				break;
			case 3:
				enunciado = "Pedro treina \\(" + b + "\\) horas por dia. Quantas horas ele treina em \\(" + a
					+ "\\) dias?";
				break;
			case 4:
				enunciado = "Uma prateleira tem \\(" + a + "\\) colunas com \\(" + b
					+ "\\) livros em cada coluna. Quantos livros há na prateleira?";
				break;
			default:
				enunciado = "Uma caixa de chocolates tem \\(" + a + "\\) fileiras com \\(" + b
					+ "\\) chocolates cada. Quantos chocolates há na caixa?";
				break;
		}

		addParagrafo(enunciado);
		gerarAlternativasInteiras(produto);

		String res = "Identificamos uma situação de repetição: \\(" + a + "\\) grupos de \\(" + b
			+ "\\) unidades. \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
