package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

public class RegraTres2 extends GeradorExercicio
{
	private static final String[] TEXTOS_DESCONTO = {
		"Uma peça de roupa custa R\\(\\$\\)$V,00 e está com $P% de desconto. Qual o preço final?",
		"Um produto custava R\\(\\$\\)$V,00 e teve $P% de desconto. Quanto custa agora?",
		"Um eletrodoméstico custa R\\(\\$\\)$V,00. O vendedor oferece $P% de desconto. Qual o valor a pagar?",
		"Uma passagem aérea custa R\\(\\$\\)$V,00. Com $P% de desconto antecipado, qual o preço final?",
	};

	private static final String[] TEXTOS_AUMENTO = {
		"Um produto custa R\\(\\$\\)$V,00 e sofreu um reajuste de $P%. Qual o novo preço?",
		"O salário de R\\(\\$\\)$V,00 foi reajustado em $P%. Qual o novo salário?",
		"Um aluguel de R\\(\\$\\)$V,00 sofreu aumento de $P%. Qual o novo valor?",
		"Um produto custava R\\(\\$\\)$V,00 e teve $P% de aumento. Quanto custa agora?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int delta = 1 + rand.nextInt(10);
		int V = delta * fator;

		boolean isDesconto = rand.nextBoolean();
		int resultado = isDesconto ? V - delta : V + delta;

		String texto = isDesconto
			? TEXTOS_DESCONTO[rand.nextInt(TEXTOS_DESCONTO.length)]
			: TEXTOS_AUMENTO[rand.nextInt(TEXTOS_AUMENTO.length)];
		texto = texto.replace("$P", "" + p).replace("$V", "" + V);

		addParagrafo(texto);
		gerarAlternativas("" + resultado);

		String tipoPt = isDesconto ? "desconto" : "aumento";
		String sinal = isDesconto ? "-" : "+";
		String res = "Calcular o " + tipoPt + " de \\(" + p + "\\%\\): \\(\\\\\\)";
		res += "\\(\\dfrac{" + p + "}{100} \\times " + V + " = \\mathbf{" + delta + "}\\). \\(\\\\\\)";
		res += "Preço " + (isDesconto ? "com desconto" : "com aumento") + ": \\(\\\\\\)";
		res += "\\(" + V + " " + sinal + " " + delta + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
