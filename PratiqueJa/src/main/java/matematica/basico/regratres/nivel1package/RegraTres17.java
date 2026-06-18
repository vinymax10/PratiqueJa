package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Cálculo de porcentagem de um valor (terceira variação)
public class RegraTres17 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Uma viagem de $V km já foi percorrida em $P%. Quantos km já foram percorridos?",
		"Um reservatório com capacidade de $V litros está $P% cheio. Quantos litros há nele?",
		"Uma meta de $V pontos foi atingida em $P%. Quantos pontos foram atingidos?",
		"Um curso tem $V horas. O aluno já cumpriu $P% da carga. Quantas horas ele já cumpriu?",
		"Uma campanha quer arrecadar $V doações e já alcançou $P%. Quantas doações já recebeu?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 3 + rand.nextInt(28); // 3..30
		int valor = k * fator;

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$P", "" + p).replace("$V", "" + valor);

		addParagrafo(texto);
		gerarAlternativas("" + k);

		String res = "Calcular \\(" + p + "\\%\\) de \\(" + valor + "\\): \\(\\\\\\)";
		res += "\\(\\dfrac{" + p + "}{100} \\times " + valor + " = \\\\ \\)";
		res += "\\(\\dfrac{" + (p * valor) + "}{100} = \\mathbf{" + k + "}\\)";
		setResolucao(res);
	}
}
