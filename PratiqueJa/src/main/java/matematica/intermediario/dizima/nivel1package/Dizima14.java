package matematica.intermediario.dizima.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Dizima14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean simples = rand.nextBoolean();

		int inteira = rand.nextInt(4);  // 0..3
		String latexNotation;

		if(simples)
		{
			// Simples: parte decimal começa imediatamente com o período
			int period;
			if(rand.nextBoolean())
				period = 1 + rand.nextInt(8);  // 1 dígito
			else
			{
				do { period = 10 + rand.nextInt(89); } while(period % 11 == 0);
			}
			latexNotation = inteira + "{,}\\overline{" + period + "}";
		}
		else
		{
			// Composta: há algarismo(s) não periódico(s) antes do período
			int naoPer = 1 + rand.nextInt(8);  // 1 algarismo não periódico
			int period  = 1 + rand.nextInt(8);  // 1 algarismo periódico
			while(naoPer == period) naoPer = 1 + rand.nextInt(8);
			latexNotation = inteira + "{,}" + naoPer + "\\overline{" + period + "}";
		}

		addParagrafo("A dízima periódica a seguir é simples ou composta?");
		addParagrafo("\\(" + latexNotation + "\\)");

		String correta  = simples ? "Simples" : "Composta";
		String incorreta = simples ? "Composta" : "Simples";
		embaralharEAdicionarAlternativas(correta,
			Arrays.asList(incorreta, "Não é periódica", "É um número inteiro"));

		String res;
		if(simples)
		{
			res = "Dízima \\(\\textbf{simples}\\): a parte decimal começa imediatamente com o "
				+ "período \\(\\left(\\overline{\\,\\cdot\\,}\\right)\\) — sem algarismos "
				+ "não periódicos entre a vírgula e o início da repetição.";
		}
		else
		{
			res = "Dízima \\(\\textbf{composta}\\): existe pelo menos um algarismo não periódico "
				+ "entre a vírgula e o início do período \\(\\left(\\overline{\\,\\cdot\\,}\\right)\\).";
		}
		setResolucao(res);
	}
}
