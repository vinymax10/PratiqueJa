package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class MissingFator extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9); if (rand.nextBoolean()) a = -a;
		int b = 1 + rand.nextInt(9); if (rand.nextBoolean()) b = -b;
		int c = a * b;

		String bFmt = b < 0 ? "(" + b + ")" : "" + b;
		String cStr = c >= 0 ? "+" + c : "" + c;

		if (rand.nextBoolean())
			addParagrafo("Qual é o valor de \\(\\square\\) em \\(\\square \\times " + bFmt + " = " + cStr + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) em \\(" + bFmt + " \\times \\square = " + cStr + "\\)?");

		gerarAlternativasInteirasComNegativos(a);

		addResolucao("Para encontrar o fator desconhecido, dividimos o produto pelo fator conhecido:");
		for(String passo : ResolucaoMDInteiro.divisao(c, b))
			addResolucao(passo);
	}
}
