package matematica.basico.multiplicacaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 12 + rand.nextInt(79);
		int b = 12 + rand.nextInt(79);
		int produto = a * b;

		int tipo = rand.nextInt(6);
		String enunciado;
		switch (tipo)
		{
			case 0:
				enunciado = "Uma gráfica imprime \\(" + a + "\\) folhetos por hora."
					+ " Em um dia de \\(" + b + "\\) horas de trabalho, quantos folhetos são impressos?";
				break;
			case 1:
				enunciado = "Um depósito tem \\(" + a + "\\) prateleiras, cada uma com"
					+ " \\(" + b + "\\) caixas. Quantas caixas há no depósito?";
				break;
			case 2:
				enunciado = "Um campo de futebol tem \\(" + a + "\\) metros de comprimento"
					+ " e \\(" + b + "\\) metros de largura. Qual é a área do campo em metros quadrados?";
				break;
			case 3:
				enunciado = "Uma fábrica produz \\(" + a + "\\) peças por minuto."
					+ " Quantas peças ela produz em \\(" + b + "\\) minutos?";
				break;
			case 4:
				enunciado = "Um auditório tem \\(" + a + "\\) fileiras com \\(" + b
					+ "\\) cadeiras cada. Quantas cadeiras há no auditório?";
				break;
			default:
				enunciado = "Uma fazenda colheu \\(" + a + "\\) caixas de laranja por dia"
					+ " durante \\(" + b + "\\) dias. Quantas caixas foram colhidas no total?";
				break;
		}

		addParagrafo(enunciado);
		gerarAlternativasInteiras(produto);

		String acao = (tipo == 2) ? "Área = comprimento \\(\\times\\) largura" : "Multiplicamos as duas quantidades";
		String res = acao + ": \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
