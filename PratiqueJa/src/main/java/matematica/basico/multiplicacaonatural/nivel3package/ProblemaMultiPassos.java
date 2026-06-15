package matematica.basico.multiplicacaonatural.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class ProblemaMultiPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		int a, b, c, passo1, total;
		String enunciado, label1, label2, unidade;

		switch (tipo)
		{
			case 0: // produção: unidades/hora × horas/dia × dias
				a = 100 + rand.nextInt(301);
				b = 4 + rand.nextInt(5);
				c = 4 + rand.nextInt(4);
				passo1 = a * b;
				total = passo1 * c;
				enunciado = "Uma fábrica produz \\(" + a + "\\) peças por hora."
					+ " Trabalhando \\(" + b + "\\) horas por dia, quantas peças são"
					+ " produzidas em \\(" + c + "\\) dias?";
				label1 = "Produção diária (" + b + " horas)";
				label2 = "Total em " + c + " dias";
				unidade = "\\,\\text{peças}";
				break;
			case 1: // área de vários terrenos iguais: área_unit × quantidade
				a = 100 + rand.nextInt(401);
				b = 20 + rand.nextInt(81);
				c = 3 + rand.nextInt(8);
				passo1 = a * b;
				total = passo1 * c;
				enunciado = "Um terreno retangular mede \\(" + a + "\\) metros por \\(" + b
					+ "\\) metros. Qual é a área total de \\(" + c + "\\) terrenos iguais?";
				label1 = "Área de um terreno";
				label2 = "Área de " + c + " terrenos";
				unidade = "\\,\\text{m}^2";
				break;
			default: // distribuição: caixas × unidades/caixa × lojas
				a = 100 + rand.nextInt(401);
				b = 5 + rand.nextInt(16);
				c = 3 + rand.nextInt(8);
				passo1 = a * b;
				total = passo1 * c;
				enunciado = "Um depósito envia \\(" + a + "\\) caixas para cada loja,"
					+ " com \\(" + b + "\\) unidades em cada caixa."
					+ " Quantas unidades são distribuídas para \\(" + c + "\\) lojas?";
				label1 = "Unidades por loja";
				label2 = "Total para " + c + " lojas";
				unidade = "\\,\\text{unidades}";
				break;
		}

		addParagrafo(enunciado);

		Set<Integer> usados = new HashSet<>();
		usados.add(total);
		List<String> distrais = new ArrayList<>();
		int[] candidatos = {a * c, passo1 * (c + 1), passo1 + c};
		for (int w : candidatos)
		{
			if (distrais.size() >= 3) break;
			if (w > 0 && w != total && usados.add(w))
				distrais.add(formatarNumero(w));
		}
		while (distrais.size() < 3)
		{
			int delta = 100 * (1 + rand.nextInt(5));
			int fallback = total + (rand.nextBoolean() ? delta : -delta);
			if (fallback > 0 && usados.add(fallback))
				distrais.add(formatarNumero(fallback));
		}
		embaralharEAdicionarAlternativas(formatarNumero(total), distrais);

		String res = "Passo 1 — " + label1 + ": \\(\\\\\\)";
		res += "\\(" + a + " \\times " + b + " = " + passo1 + unidade + "\\) \\(\\\\\\)";
		res += "Passo 2 — " + label2 + ": \\(\\\\\\)";
		res += "\\(" + passo1 + " \\times " + c + " = \\mathbf{" + total + "}" + unidade + "\\)";
		setResolucao(res);
	}
}
