package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;

// Valor (em reais) do desconto concedido sobre um preço
public class RegraTres16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(20); // 2..21
		int valor = k * fator;

		addParagrafo("Um produto que custa R$" + valor + ",00 está com " + p + "% de desconto. Qual é o valor do desconto, em reais?");
		gerarAlternativas("" + k);

		String res = "O valor do desconto é \\(" + p + "\\%\\) do preço: \\(\\\\\\)";
		res += "\\(\\dfrac{" + p + "}{100} \\times " + valor + " = \\dfrac{" + (p * valor) + "}{100} = \\mathbf{" + k + "}\\)";
		setResolucao(res);
	}
}
