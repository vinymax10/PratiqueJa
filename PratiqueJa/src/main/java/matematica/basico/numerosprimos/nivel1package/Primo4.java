package matematica.basico.numerosprimos.nivel1package;

import java.util.Arrays;
import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// O número N é primo ou composto? (faixa ampliada)
public class Primo4 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean escolherPrimo = rand.nextBoolean();
		int n;
		if(escolherPrimo)
			n = LISTA_PRIMOS[rand.nextInt(LISTA_PRIMOS.length)];
		else
			do { n = 4 + rand.nextInt(76); } while(ehPrimo(n)); // 4..79 composto

		boolean ePrimo = ehPrimo(n);
		String correta = ePrimo ? "Primo" : "Composto";
		String errada = ePrimo ? "Composto" : "Primo";

		addParagrafo("O número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));

		if(ePrimo)
		{
			addResolucao("Divisores de \\(" + n + "\\): apenas \\(1\\) e \\(" + n + "\\).");
			addResolucao("\\(\\therefore " + n + "\\) é primo.");
		}
		else
		{
			Map<Integer, Integer> f = fatorar(n);
			int p = f.keySet().iterator().next();
			addResolucao("\\(" + n + " \\div " + p + " = " + (n / p) + " \\Rightarrow\\) divisível por \\(" + p + "\\).");
			addResolucao("\\(\\therefore " + n + "\\) é composto.");
		}
	}
}
