package matematica.basico.numerosprimos.nivel1package;

import java.util.Arrays;
import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// O número N é primo ou composto?
public class Primo1 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean escolherPrimo = rand.nextBoolean();
		int n;

		if(escolherPrimo)
			n = LISTA_PRIMOS[rand.nextInt(LISTA_PRIMOS.length)];
		else
			do { n = 4 + rand.nextInt(47); } while(ehPrimo(n));

		boolean ePrimo = ehPrimo(n);
		String correta = ePrimo ? "Primo" : "Composto";
		String errada = ePrimo ? "Composto" : "Primo";

		addParagrafo("O número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));

		if(ePrimo)
		{
			setResolucao(
				"\\(\\begin{aligned}" +
				"& \\text{Divisores de } " + n + "\\text{: apenas } 1 \\text{ e } " + n + ".\\\\" +
				"& \\therefore " + n + " \\text{ é } \\mathbf{primo}." +
				"\\end{aligned}\\)"
			);
		}
		else
		{
			Map<Integer, Integer> f = fatorar(n);
			int p = f.keySet().iterator().next();
			setResolucao(
				"\\(\\begin{aligned}" +
				"& " + n + " \\div " + p + " = " + (n / p) +
				" \\Rightarrow \\text{ divisível por } " + p + ".\\\\" +
				"& \\therefore " + n + " \\text{ é } \\mathbf{composto}." +
				"\\end{aligned}\\)"
			);
		}
	}
}
