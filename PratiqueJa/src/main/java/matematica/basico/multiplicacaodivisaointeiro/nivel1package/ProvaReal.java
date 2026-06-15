package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaodivisaointeiro.ResolucaoMDInteiro;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9); if (rand.nextBoolean()) a = -a;
		int b = 1 + rand.nextInt(9); if (rand.nextBoolean()) b = -b;
		int c = a * b;

		String aParens = a < 0 ? "(" + a + ")" : "(+" + a + ")";
		String bParens = b < 0 ? "(" + b + ")" : "(+" + b + ")";
		String cStr = c >= 0 ? "+" + c : "" + c;

		boolean checkFirst = rand.nextBoolean();
		if (checkFirst)
		{
			addParagrafo("Carlos calculou \\(" + aParens + " \\times " + bParens + " = " + cStr + "\\). Para verificar, dividiu \\(" + cStr + " \\div " + bParens + "\\). Qual deve ser o resultado?");
			gerarAlternativasInteirasComNegativos(a);
			setResolucao(ResolucaoMDInteiro.divisao(c, b));
		}
		else
		{
			addParagrafo("Sabendo que \\(" + aParens + " \\times " + bParens + " = " + cStr + "\\), qual é o resultado de \\(" + cStr + " \\div " + aParens + "\\)?");
			gerarAlternativasInteirasComNegativos(b);
			setResolucao(ResolucaoMDInteiro.divisao(c, a));
		}
	}
}
