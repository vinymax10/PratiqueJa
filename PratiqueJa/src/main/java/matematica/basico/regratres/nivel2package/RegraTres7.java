package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

// Aumento ou desconto percentual sobre um valor (segunda variação)
public class RegraTres7 extends GeradorExercicio
{
	private static final String[] TEXTOS_DESCONTO = {
		"Uma geladeira custa R$$V,00 e está com $P% de desconto à vista. Qual o preço à vista?",
		"Um curso de R$$V,00 oferece $P% de desconto para ex-alunos. Qual o valor com desconto?",
		"Uma bicicleta de R$$V,00 teve $P% de desconto na liquidação. Qual o preço final?",
	};

	private static final String[] TEXTOS_AUMENTO = {
		"Uma mensalidade de R$$V,00 sofreu reajuste de $P%. Qual o novo valor?",
		"Um plano de R$$V,00 teve aumento de $P%. Quanto passou a custar?",
		"O preço de R$$V,00 de uma matrícula subiu $P%. Qual o novo preço?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int delta = 2 + rand.nextInt(15); // 2..16
		int valor = delta * fator;

		boolean isDesconto = rand.nextBoolean();
		int resultado = isDesconto ? valor - delta : valor + delta;

		String texto = isDesconto
			? TEXTOS_DESCONTO[rand.nextInt(TEXTOS_DESCONTO.length)]
			: TEXTOS_AUMENTO[rand.nextInt(TEXTOS_AUMENTO.length)];
		texto = texto.replace("$P", "" + p).replace("$V", "" + valor);

		addParagrafo(texto);
		gerarAlternativas("" + resultado);

		String tipoPt = isDesconto ? "desconto" : "aumento";
		String sinal = isDesconto ? "-" : "+";
		addResolucao("Calcular o " + tipoPt + " de \\(" + p + "\\%\\):");
		addResolucao("\\(\\dfrac{" + p + "}{100} \\times " + valor + " = \\mathbf{" + delta + "}\\).");
		addResolucao("Valor final: \\(" + valor + " " + sinal + " " + delta + " = \\mathbf{" + resultado + "}\\)");
	}
}
