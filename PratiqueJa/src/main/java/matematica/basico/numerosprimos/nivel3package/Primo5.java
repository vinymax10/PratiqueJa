package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos números primos existem no intervalo [A, B]? (Crivo de Eratóstenes)
public class Primo5 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(31); // 10..40
		int b = a + 10 + rand.nextInt(21); // a+10 .. a+30

		int count = 0;
		StringBuilder primosStr = new StringBuilder();
		for(int k = a; k <= b; k++)
		{
			if(ehPrimo(k))
			{
				count++;
				if(primosStr.length() > 0) primosStr.append(", ");
				primosStr.append(k);
			}
		}

		addParagrafo(
			"Quantos números primos existem no intervalo \\([" + a + ",\\," + b + "]\\)?"
		);
		gerarAlternativasInteiras(count);

		String listaStr = primosStr.length() > 0 ? primosStr.toString() : "\\text{nenhum}";
		setResolucao(
			"\\(\\begin{aligned}" +
			"& \\text{Primos em } [" + a + ",\\," + b + "]: \\\\" +
			"& " + listaStr + "\\\\" +
			"& \\text{Total: } \\mathbf{" + count + "}" +
			"\\end{aligned}\\)"
		);
	}
}
