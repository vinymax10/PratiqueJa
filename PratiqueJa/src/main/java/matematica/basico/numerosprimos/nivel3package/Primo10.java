package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos números primos existem no intervalo [A, B]? (intervalo maior)
public class Primo10 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int a = 30 + rand.nextInt(40);      // 30..69
		int b = a + 15 + rand.nextInt(21);  // a+15 .. a+35

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

		addParagrafo("Quantos números primos existem no intervalo \\([" + a + ",\\," + b + "]\\)?");
		gerarAlternativasInteiras(count);

		String listaStr = primosStr.length() > 0 ? "\\(" + primosStr + "\\)" : "nenhum";
		setResolucao("Primos em \\([" + a + ",\\," + b + "]\\): " + listaStr + ". \\(\\\\\\) Total: \\(\\mathbf{" + count + "}\\)");
	}
}
