package matematica.avancado.probabilidade.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// "Ao menos um" com moeda: P(≥1 cara) = 1 - (1/2)^n
// Para n = 2, 3, 4, 5:
//   n=2: 1 - 1/4  = 3/4
//   n=3: 1 - 1/8  = 7/8
//   n=4: 1 - 1/16 = 15/16
//   n=5: 1 - 1/32 = 31/32
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(4);  // 2, 3, 4 ou 5 lançamentos

		int potencia = 1 << n;  // 2^n
		int favoravel = potencia - 1;  // 2^n - 1

		addParagrafo("Uma moeda honesta é lançada \\(" + n + "\\) vezes."
				+ " Qual a probabilidade de sair cara em ao menos um lançamento?");

		String correta = "\\(\\dfrac{" + favoravel + "}{" + potencia + "}\\)";
		List<String> distratores = new ArrayList<>();
		// Quando n=2: fav-1==n → d2==d3 ("2/4"=="2/4"); usar pot+2 como denominador de d3
		int d3num = favoravel - 1;
		int d3den = (d3num == n) ? potencia + 2 : potencia;
		distratores.add("\\(\\dfrac{1}{" + potencia + "}\\)");              // P(nenhuma cara): erro de complementar
		distratores.add("\\(\\dfrac{" + n + "}{" + potencia + "}\\)");      // multiplicou P × n
		distratores.add("\\(\\dfrac{" + d3num + "}{" + d3den + "}\\)");    // off-by-one
		embaralharEAdicionarAlternativas(correta, distratores);

		String pNenhuma = "\\dfrac{1}{" + potencia + "}";
		String potStr = n == 2 ? "\\left(\\dfrac{1}{2}\\right)^2"
						: n == 3 ? "\\left(\\dfrac{1}{2}\\right)^3"
						: n == 4 ? "\\left(\\dfrac{1}{2}\\right)^4"
						:          "\\left(\\dfrac{1}{2}\\right)^5";

		addResolucao("Usando \\(P(A^c) = 1 - P(A)\\):");
		addResolucao("\\(A =\\) ao menos uma cara em " + n + " lançamentos");
		addResolucao("\\(P(A^c) = " + potStr + " = " + pNenhuma + "\\)");
		addResolucao("\\(P(A) = 1 - " + pNenhuma + " = \\dfrac{" + potencia + "-1}{" + potencia + "} = \\mathbf{\\dfrac{" + favoravel + "}{" + potencia + "}}\\)");
	}
}
