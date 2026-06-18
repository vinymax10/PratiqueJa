package matematica.basico.regratres.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Variação percentual entre dois valores (segunda variação)
public class RegraTres8 extends GeradorExercicio
{
	private static final String[] TEXTOS_AUMENTO = {
		"A audiência de um programa subiu de $A para $B mil espectadores. Qual o percentual de aumento?",
		"O número de assinantes cresceu de $A para $B. Qual o percentual de crescimento?",
		"A temperatura passou de $A°C para $B°C. Qual o percentual de aumento?",
	};

	private static final String[] TEXTOS_REDUCAO = {
		"O preço de um produto caiu de R$$A,00 para R$$B,00. Qual o percentual de redução?",
		"O tempo de espera diminuiu de $A para $B minutos. Qual o percentual de redução?",
		"O número de faltas caiu de $A para $B. Qual o percentual de redução?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(12); // 2..13
		int a = k * fator;
		int delta = k;
		boolean isAumento = rand.nextBoolean();
		int b = isAumento ? a + delta : a - delta;

		String[] textos = isAumento ? TEXTOS_AUMENTO : TEXTOS_REDUCAO;
		String texto = textos[rand.nextInt(textos.length)];
		texto = texto.replace("$A", "" + a).replace("$B", "" + b);
		addParagrafo(texto);

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20, -20, 25, -25};
		for(int delta2 : tentativas)
		{
			if(distratores.size() >= 3) break;
			int alt = p + delta2;
			if(alt > 0 && alt < 100 && usados.add(alt))
				distratores.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + p + "\\%\\)", distratores);

		String tipo = isAumento ? "aumento" : "redução";
		String res = "Fórmula: \\(\\% = \\dfrac{\\text{variação}}{\\text{valor inicial}} \\times 100\\). \\(\\\\\\)";
		res += "\\(\\% = \\dfrac{" + delta + "}{" + a + "} \\times 100 = \\\\ \\)";
		res += "\\(\\dfrac{" + (delta * 100) + "}{" + a + "} = \\mathbf{" + p + "\\%}\\) de " + tipo;
		setResolucao(res);
	}
}
