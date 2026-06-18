package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

// Reverso percentual: encontrar o preço original a partir do preço com desconto
public class RegraTres10 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Após um desconto de $P%, um produto passou a custar R$$F,00. Qual era o preço original?",
		"Com $P% de desconto, uma passagem saiu por R$$F,00. Qual era o preço sem desconto?",
		"Um aparelho foi vendido por R$$F,00 já com $P% de desconto. Qual era o preço de tabela?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(15); // 2..16
		int original = k * fator;
		int precoFinal = original - k; // desconto de p% = k

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$P", "" + p).replace("$F", "" + precoFinal);
		addParagrafo(texto);
		gerarAlternativas("" + original);

		String res = "O preço com desconto corresponde a \\((100 - " + p + ")\\% = " + (100 - p) + "\\%\\) do original. \\(\\\\\\)";
		res += "Logo, isolando o valor original: \\(\\\\\\)";
		res += "\\(V = \\dfrac{" + precoFinal + " \\times 100}{" + (100 - p) + "} = \\dfrac{" + (precoFinal * 100) + "}{" + (100 - p) + "} = \\mathbf{" + original + "}\\)";
		setResolucao(res);
	}
}
