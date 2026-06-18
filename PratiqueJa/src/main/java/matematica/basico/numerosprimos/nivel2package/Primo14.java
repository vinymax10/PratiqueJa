package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Os números A e B são coprimos (primos entre si)?
public class Primo14 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean querCoprimo = rand.nextBoolean();
		int a, b;
		do
		{
			a = 4 + rand.nextInt(40); // 4..43
			b = 4 + rand.nextInt(40);
		}
		while(a == b || (mdc(a, b) == 1) != querCoprimo);

		boolean coprimos = mdc(a, b) == 1;

		addParagrafo("Os números \\(" + a + "\\) e \\(" + b + "\\) são coprimos (primos entre si)?");
		gerarAlternativasBoolean(coprimos);

		if(coprimos)
			setResolucao("Dois números são coprimos quando seu MDC é \\(1\\). \\(\\\\\\) \\(\\text{MDC}(" + a + ",\\," + b + ") = 1\\): não compartilham fatores primos. \\(\\\\\\) Logo, são coprimos: \\(\\mathbf{Sim}\\).");
		else
		{
			int d = mdc(a, b);
			setResolucao("Dois números são coprimos quando seu MDC é \\(1\\). \\(\\\\\\) \\(\\text{MDC}(" + a + ",\\," + b + ") = " + d + " > 1\\): compartilham fator. \\(\\\\\\) Logo, não são coprimos: \\(\\mathbf{Não}\\).");
		}
	}
}
