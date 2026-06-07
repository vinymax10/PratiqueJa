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

		StringBuilder res = new StringBuilder("\\(\\begin{aligned}");
		res.append("& \\sqrt{").append(n).append("} \\approx ")
		   .append(sqrtInt).append("{,}").append(sqrtDec)
		   .append(" \\Rightarrow \\text{testar: }").append(primos).append("\\\\");
		if(ePrimo)
		{
			res.append("& ").append(n).append("\\text{ não é divisível por nenhum}\\\\");
			res.append("& \\therefore ").append(n).append("\\text{ é }\\mathbf{primo}");
		}
		else
		{
			res.append("& ").append(n).append(" \\div ").append(primoDivisor)
			   .append(" = ").append(n / primoDivisor)
			   .append(" \\Rightarrow \\text{divisível por } ").append(primoDivisor).append("\\\\");
			res.append("& \\therefore ").append(n).append("\\text{ é }\\mathbf{composto}");
		}
		res.append("\\end{aligned}\\)");

		addParagrafo("Pelo teste de primalidade, o número \\(" + n + "\\) é:");
		embaralharEAdicionarAlternativas(correta,
			Arrays.asList(errada, "Primo e composto", "Nem primo nem composto"));
		setResolucao(res.toString());
	}
}
