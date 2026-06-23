package matematica.basico.regratres.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

public class RegraTres2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] opcoes = {10, 20, 30, 40, 50};
		int p1 = opcoes[rand.nextInt(opcoes.length)];
		int p2 = opcoes[rand.nextInt(opcoes.length)];
		boolean isAumento = rand.nextBoolean();

		int produto = p1 * p2 / 100;
		int total = isAumento ? p1 + p2 + produto : p1 + p2 - produto;

		String tipo = isAumento ? "aumentos" : "descontos";
		String tipoSing = isAumento ? "aumento" : "desconto";
		String pergunta = "Um produto sofreu dois " + tipo + " sucessivos de \\("
			+ p1 + "\\%\\) e \\(" + p2 + "\\%\\). "
			+ "Qual o percentual equivalente de " + tipoSing + " total?";
		addParagrafo(pergunta);

		List<String> distr = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(total);
		if (usados.add(p1 + p2))
			distr.add("\\(" + (p1 + p2) + "\\%\\)");
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20, -20};
		for (int d : tentativas)
		{
			if (distr.size() >= 3) break;
			int alt = total + d;
			if (alt > 0 && usados.add(alt))
				distr.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + total + "\\%\\)", distr);

		String sinal = isAumento ? "+" : "-";
		String tipoLabel = isAumento ? "Aumento" : "Desconto";
		addResolucao("\\(\\text{" + tipoLabel + " total} = p_1 + p_2 " + sinal
			+ " \\dfrac{p_1 \\cdot p_2}{100}\\).");
		addResolucao("\\(" + p1 + " + " + p2 + " " + sinal
			+ " \\dfrac{" + p1 + " \\cdot " + p2 + "}{100} =\\)");
		addResolucao("\\(" + (p1 + p2) + " " + sinal
			+ " \\dfrac{" + (p1 * p2) + "}{100} =\\)");
		addResolucao("\\(" + (p1 + p2) + " " + sinal + " " + produto
			+ " = \\mathbf{" + total + "\\%}\\)");
	}
}
