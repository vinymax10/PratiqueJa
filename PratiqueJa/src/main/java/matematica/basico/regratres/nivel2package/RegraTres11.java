package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

// Reverso percentual: encontrar o valor original a partir do valor com aumento
public class RegraTres11 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Após um aumento de $P%, um salário passou a ser R$$F,00. Qual era o salário anterior?",
		"Com um reajuste de $P%, um aluguel ficou em R$$F,00. Qual era o valor antes do reajuste?",
		"Depois de subir $P%, uma tarifa passou a custar R$$F,00. Qual era a tarifa original?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(15); // 2..16
		int original = k * fator;
		int valorFinal = original + k; // aumento de p% = k

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$P", "" + p).replace("$F", "" + valorFinal);
		addParagrafo(texto);
		gerarAlternativas("" + original);

		String res = "O valor com aumento corresponde a \\((100 + " + p + ")\\% = " + (100 + p) + "\\%\\) do original. \\(\\\\\\)";
		res += "Logo, isolando o valor original: \\(\\\\\\)";
		res += "\\(V = \\dfrac{" + valorFinal + " \\times 100}{" + (100 + p) + "} = \\dfrac{" + (valorFinal * 100) + "}{" + (100 + p) + "} = \\mathbf{" + original + "}\\)";
		setResolucao(res);
	}
}
