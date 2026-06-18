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
public class Exercicio8 extends GeradorExercicio
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
		distratores.add("\\(\\dfrac{1}{" + potencia + "}\\)");              // P(nenhuma cara): erro de complementar
		distratores.add("\\(\\dfrac{" + n + "}{" + potencia + "}\\)");      // multiplicou P × n
		distratores.add("\\(\\dfrac{" + (favoravel - 1) + "}{" + potencia + "}\\)");  // off-by-one
		embaralharEAdicionarAlternativas(correta, distratores);

		String pNenhuma = "\\dfrac{1}{" + potencia + "}";
		String potStr = n == 2 ? "\\left(\\dfrac{1}{2}\\right)^2"
						: n == 3 ? "\\left(\\dfrac{1}{2}\\right)^3"
						: n == 4 ? "\\left(\\dfrac{1}{2}\\right)^4"
						:          "\\left(\\dfrac{1}{2}\\right)^5";

		String res = "Usando \\(P(A^c) = 1 - P(A)\\):\\(\\\\\\)";
		res += "\\(A =\\) ao menos uma cara em " + n + " lançamentos\\(\\\\\\)";
		res += "\\(P(A^c) = " + potStr + " = " + pNenhuma + " \\\\";
		res += "P(A) = 1 - " + pNenhuma + " = \\dfrac{" + potencia + "-1}{" + potencia + "} = \\mathbf{\\dfrac{" + favoravel + "}{" + potencia + "}}\\)";
		setResolucao(res);
	}
}
