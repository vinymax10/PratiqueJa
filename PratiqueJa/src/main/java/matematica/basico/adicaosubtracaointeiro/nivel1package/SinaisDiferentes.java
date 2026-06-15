package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ResolucaoASInteiro;

public class SinaisDiferentes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		boolean aNeg = rand.nextBoolean();
		if (aNeg) a = -a; else b = -b;

		int resultado = a + b;

		String aStr = a > 0 ? "(+" + a + ")" : "(" + a + ")";
		String bStr = b > 0 ? "(+" + b + ")" : "(" + b + ")";

		int tipo = rand.nextInt(2);
		if (tipo == 0)
		{
			addParagrafo("Calcule: \\(" + aStr + " + " + bStr + "\\)");
		}
		else if (a > 0)
		{
			addParagrafo("Um jogador ganhou \\(" + a + "\\) pontos e depois recebeu uma penalidade de \\(" + Math.abs(b) + "\\) pontos. Qual é o saldo?");
		}
		else
		{
			addParagrafo("Uma conta tinha uma dívida de \\(" + Math.abs(a) + "\\) reais. Foi feito um depósito de \\(" + b + "\\) reais. Qual é o saldo?");
		}

		gerarAlternativasInteirasComNegativos(resultado);
		setResolucao(ResolucaoASInteiro.soma(a, b));
	}
}
