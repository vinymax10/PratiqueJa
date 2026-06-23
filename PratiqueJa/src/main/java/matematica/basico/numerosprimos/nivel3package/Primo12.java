package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Os números A e B são coprimos? (faixa 20..99)
public class Primo12 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		boolean querCoprimo = rand.nextBoolean();
		int a, b;
		do
		{
			a = 20 + rand.nextInt(80); // 20..99
			b = 20 + rand.nextInt(80);
		}
		while(a == b || (mdc(a, b) == 1) != querCoprimo);

		boolean coprimos = mdc(a, b) == 1;

		addParagrafo("Os números \\(" + a + "\\) e \\(" + b + "\\) são coprimos (primos entre si)?");
		gerarAlternativasBoolean(coprimos);

		if(coprimos)
		{
			addResolucao("São coprimos quando o MDC é \\(1\\).");
			addResolucao("\\(\\text{MDC}(" + a + ",\\," + b + ") = 1\\): não compartilham fatores primos.");
			addResolucao("Logo: \\(\\mathbf{Sim}\\).");
		}
		else
		{
			int d = mdc(a, b);
			addResolucao("São coprimos quando o MDC é \\(1\\).");
			addResolucao("\\(\\text{MDC}(" + a + ",\\," + b + ") = " + d + " > 1\\): têm fator comum.");
			addResolucao("Logo: \\(\\mathbf{Não}\\).");
		}
	}
}
