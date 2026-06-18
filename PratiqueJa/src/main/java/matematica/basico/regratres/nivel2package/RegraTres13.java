package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

// Aumento ou desconto percentual sobre um valor (terceira variação)
public class RegraTres13 extends GeradorExercicio
{
	private static final String[] TEXTOS_DESCONTO = {
		"Uma conta de luz de R$$V,00 teve $P% de desconto por pagamento adiantado. Qual o valor a pagar?",
		"Um par de tênis de R$$V,00 saiu com $P% de desconto. Qual o preço final?",
		"Uma diária de hotel de R$$V,00 teve $P% de abatimento. Quanto ficou?",
	};

	private static final String[] TEXTOS_AUMENTO = {
		"Uma passagem de ônibus de R$$V,00 sofreu reajuste de $P%. Qual o novo preço?",
		"O preço de R$$V,00 de um ingresso aumentou $P%. Quanto passou a custar?",
		"Uma taxa de R$$V,00 foi acrescida em $P%. Qual o novo valor?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int delta = 3 + rand.nextInt(18); // 3..20
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
		String res = "Calcular o " + tipoPt + " de \\(" + p + "\\%\\): \\(\\\\\\)";
		res += "\\(\\dfrac{" + p + "}{100} \\times " + valor + " = \\mathbf{" + delta + "}\\). \\(\\\\\\)";
		res += "Valor final: \\(" + valor + " " + sinal + " " + delta + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
