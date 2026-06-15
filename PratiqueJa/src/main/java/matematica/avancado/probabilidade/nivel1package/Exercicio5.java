package matematica.avancado.probabilidade.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.Racional;

// Regra do produto para eventos independentes: P(A∩B) = P(A) × P(B)
// Cenário: lançar moeda e dado; P(cara E face específica) = 1/2 × 1/6 = 1/12
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int face = 1 + rand.nextInt(6);

		addParagrafo("Lança-se uma moeda e um dado de seis faces ao mesmo tempo."
				+ " Qual a probabilidade de sair cara na moeda e o número " + face + " no dado?");

		// P(A∩B) = 1/2 × 1/6 = 1/12
		Racional res = new Racional(1, 12);

		List<String> distratores = new ArrayList<>();
		// 1/6: esqueceu a moeda
		distratores.add("\\(\\dfrac{1}{6}\\)");
		// 1/2: esqueceu o dado
		distratores.add("\\(\\dfrac{1}{2}\\)");
		// 1/8: 1/2 × 1/4
		distratores.add("\\(\\dfrac{1}{8}\\)");
		embaralharEAdicionarAlternativas("\\(" + res.showDfrac() + "\\)", distratores);

		String resolucao = "Como moeda e dado são independentes, usa-se a regra do produto:\\(\\\\\\)";
		resolucao += "\\(A =\\) sair cara na moeda\\(\\\\\\)";
		resolucao += "\\(B =\\) sair " + face + " no dado\\(\\\\\\)";
		resolucao += "\\(P(A) = \\dfrac{1}{2}, \\quad P(B) = \\dfrac{1}{6} \\\\";
		resolucao += "P(A \\cap B) = P(A) \\cdot P(B) = \\dfrac{1}{2} \\cdot \\dfrac{1}{6} = \\mathbf{\\dfrac{1}{12}}\\)";
		setResolucao(resolucao);
	}
}
