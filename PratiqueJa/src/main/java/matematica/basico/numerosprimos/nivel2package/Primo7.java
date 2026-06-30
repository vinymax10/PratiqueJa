package matematica.basico.numerosprimos.nivel2package;

import java.util.Arrays;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Teste de primalidade (faixa 50..150)
public class Primo7 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean escolherPrimo = rand.nextBoolean();
		int n;
		do { n = 50 + rand.nextInt(101); } while(ehPrimo(n) != escolherPrimo);

		boolean ePrimo = ehPrimo(n);
		int sqrtFloor = (int) Math.sqrt(n);

		StringBuilder primos = new StringBuilder();
		int primoDivisor = 0;
		for(int p : LISTA_PRIMOS)
		{
			if(p > sqrtFloor) break;
			if(primos.length() > 0) primos.append(",\\, ");
			primos.append(p);
			if(!ePrimo && primoDivisor == 0 && n % p == 0) primoDivisor = p;
		}

		String correta = ePrimo ? "Primo" : "Composto";
		String errada = ePrimo ? "Composto" : "Primo";

		addParagrafo("Pelo teste de primalidade, o número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));

		addResolucao("\\(\\sqrt{" + n + "} \\approx " + sqrtFloor
			+ " \\Rightarrow\\) testar primos até \\(" + sqrtFloor + "\\): \\(" + primos + "\\).");
		if(ePrimo)
		{
			addResolucao("\\(" + n + "\\) não é divisível por nenhum.");
			addResolucao("\\(\\therefore \\mathbf{" + n + "}\\) é primo.");
		}
		else
		{
			addResolucao("\\(" + n + " \\div " + primoDivisor + " = " + (n / primoDivisor) + "\\) (exato).");
			addResolucao("\\(\\therefore \\mathbf{" + n + "}\\) é composto.");
		}
	}
}
