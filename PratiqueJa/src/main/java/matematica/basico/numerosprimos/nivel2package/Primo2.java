package matematica.basico.numerosprimos.nivel2package;

import java.util.Arrays;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Teste de primalidade: N de 40 a 120, mostrar o teste pelos primos até √N
public class Primo2 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean escolherPrimo = rand.nextBoolean();
		int n;
		do { n = 40 + rand.nextInt(81); } // 40..120
		while(ehPrimo(n) != escolherPrimo);

		boolean ePrimo = ehPrimo(n);
		String correta = ePrimo ? "Primo" : "Composto";
		String errada  = ePrimo ? "Composto" : "Primo";

		int sqrtFloor = (int) Math.sqrt(n);
		double sqrtExact = Math.sqrt(n);
		int sqrtInt = (int) sqrtExact;
		int sqrtDec = (int) ((sqrtExact - sqrtInt) * 10);

		StringBuilder primos = new StringBuilder();
		int primoDivisor = 0;
		for(int p : LISTA_PRIMOS)
		{
			if(p > sqrtFloor) break;
			if(primos.length() > 0) primos.append(",\\, ");
			primos.append(p);
			if(!ePrimo && primoDivisor == 0 && n % p == 0)
				primoDivisor = p;
		}

		addParagrafo("Pelo teste de primalidade, o número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta,
			Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));

		addResolucao("\\(\\sqrt{" + n + "} \\approx " + sqrtInt + "{,}" + sqrtDec
			+ " \\Rightarrow\\) testar: \\(" + primos + "\\).");
		if(ePrimo)
		{
			addResolucao("\\(" + n + "\\) não é divisível por nenhum.");
			addResolucao("\\(\\therefore " + n + "\\) é primo.");
		}
		else
		{
			addResolucao("\\(" + n + " \\div " + primoDivisor + " = " + (n / primoDivisor)
				+ " \\Rightarrow\\) divisível por \\(" + primoDivisor + "\\).");
			addResolucao("\\(\\therefore " + n + "\\) é composto.");
		}
	}
}
