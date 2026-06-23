package matematica.basico.regratres.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

public class RegraTres3 extends GeradorExercicio
{
	private static final String[] TEXTOS_AUMENTO = {
		"Um produto passou de R$$A,00 para R$$B,00. Qual o percentual de aumento?",
		"O salário aumentou de R$$A,00 para R$$B,00. Qual o percentual de aumento?",
		"A produção passou de $A para $B unidades. Qual o percentual de aumento?",
		"O preço de um imóvel subiu de R$$A,00 para R$$B,00. Qual o percentual de aumento?",
	};

	private static final String[] TEXTOS_REDUCAO = {
		"Um produto passou de R$$A,00 para R$$B,00. Qual o percentual de redução?",
		"O estoque caiu de $A para $B unidades. Qual o percentual de redução?",
		"Um salário diminuiu de R$$A,00 para R$$B,00. Qual a redução percentual?",
		"O consumo de energia caiu de $A para $B kWh. Qual o percentual de redução?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(10);
		int A = k * fator;
		int delta = k;
		boolean isAumento = rand.nextBoolean();
		int B = isAumento ? A + delta : A - delta;

		String[] textos = isAumento ? TEXTOS_AUMENTO : TEXTOS_REDUCAO;
		String texto = textos[rand.nextInt(textos.length)];
		texto = texto.replace("$A", "" + A).replace("$B", "" + B);

		addParagrafo(texto);

		List<String> distr = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20, -20, 25, -25, 30};
		for (int d : tentativas)
		{
			if (distr.size() >= 3) break;
			int alt = p + d;
			if (alt > 0 && alt < 100 && usados.add(alt))
				distr.add(alt + "%");
		}
		embaralharEAdicionarAlternativas(p + "%", distr);

		String tipo = isAumento ? "aumento" : "redução";
		addResolucao("Fórmula: \\(\\% = \\dfrac{\\text{variação}}{\\text{valor inicial}} \\times 100\\).");
		addResolucao("\\(\\% = \\dfrac{" + delta + "}{" + A + "} \\times 100 =\\)");
		addResolucao("\\(\\dfrac{" + (delta * 100) + "}{" + A + "} = \\mathbf{" + p + "\\%}\\) de " + tipo);
	}
}
