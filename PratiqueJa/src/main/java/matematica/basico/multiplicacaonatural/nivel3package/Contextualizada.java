package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(900);
		int b = 10 + rand.nextInt(90);
		int produto = a * b;

		int tipo = rand.nextInt(5);
		String enunciado, acao;
		switch (tipo)
		{
			case 0:
				enunciado = "Um estádio tem \\(" + a + "\\) fileiras de assentos com \\(" + b
					+ "\\) assentos cada. Quantos assentos há no estádio?";
				acao = "Multiplicamos o número de fileiras pela quantidade de assentos por fileira";
				break;
			case 1:
				enunciado = "Um terreno retangular tem \\(" + a + "\\) metros de comprimento"
					+ " e \\(" + b + "\\) metros de largura. Qual é a área do terreno em metros quadrados?";
				acao = "Área = comprimento \\(\\times\\) largura";
				break;
			case 2:
				enunciado = "Uma transportadora faz \\(" + b + "\\) viagens por mês,"
					+ " percorrendo \\(" + a + "\\) km em cada viagem. Quantos km são percorridos por mês?";
				acao = "Multiplicamos a distância pelo número de viagens";
				break;
			case 3:
				enunciado = "Uma gráfica imprime \\(" + a + "\\) revistas por hora e trabalha"
					+ " \\(" + b + "\\) horas por semana. Quantas revistas são impressas por semana?";
				acao = "Multiplicamos a produção horária pelo número de horas";
				break;
			default:
				enunciado = "Um condomínio tem \\(" + b + "\\) apartamentos, cada um com"
					+ " \\(" + a + "\\) metros quadrados. Qual é a área total de todos os apartamentos?";
				acao = "Multiplicamos a área de cada apartamento pelo número de apartamentos";
				break;
		}

		addParagrafo(enunciado);
		gerarAlternativasInteiras(produto);

		String res = acao + ": \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
