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

		StringBuilder res = new StringBuilder();
		res.append("\\(\\sqrt{").append(n).append("} \\approx ").append(sqrtFloor)
		   .append(" \\Rightarrow\\) testar primos até \\(").append(sqrtFloor).append("\\): \\(").append(primos).append("\\). \\(\\\\\\) ");
		if(ePrimo)
			res.append("\\(").append(n).append("\\) não é divisível por nenhum. \\(\\\\\\) \\(\\therefore ").append(n).append("\\) é primo.");
		else
			res.append("\\(").append(n).append(" \\div ").append(primoDivisor).append(" = ").append(n / primoDivisor)
			   .append("\\) (exato). \\(\\\\\\) \\(\\therefore ").append(n).append("\\) é composto.");

		addParagrafo("Pelo teste de primalidade, o número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));
		setResolucao(res.toString());
	}
}
