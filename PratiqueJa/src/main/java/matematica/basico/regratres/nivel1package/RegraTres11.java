package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;

// Cálculo de porcentagem de um valor (segunda variação)
public class RegraTres11 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Uma biblioteca tem $V livros. $P% deles são de literatura. Quantos livros são de literatura?",
		"Em uma fábrica, $V peças foram produzidas. $P% passaram no controle de qualidade. Quantas peças passaram?",
		"Um show teve $V ingressos vendidos. $P% foram de meia-entrada. Quantos foram de meia-entrada?",
		"Uma pesquisa ouviu $V pessoas. $P% aprovaram a proposta. Quantas pessoas aprovaram?",
		"Um pomar tem $V árvores. $P% são laranjeiras. Quantas árvores são laranjeiras?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(24); // 2..25
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
