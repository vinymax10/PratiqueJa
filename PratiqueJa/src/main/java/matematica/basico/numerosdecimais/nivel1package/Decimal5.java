package matematica.basico.numerosdecimais.nivel1package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// \dfrac{n}{10} ou \dfrac{n}{100} → decimal
public class Decimal5 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		boolean por10 = rand.nextBoolean();

		if(por10)
		{
			// n/10 = 0,n  (n de 1 a 9)
			int n = 1 + rand.nextInt(9);
			// resultado em décimos = n  →  fmtT(n) = "0{,}n"
			addParagrafo("Qual é o número decimal equivalente à fração abaixo?");
			addParagrafo("\\(\\dfrac{" + n + "}{10}\\)");
			gerarAltT(n);

			addResolucao("\\(\\dfrac{" + n + "}{10} = " + n + " \\div 10 = " + fmtT(n) + "\\)");
		}
		else
		{
			// n/100 = 0,0n ou 0,n0  (n de 1 a 99)
			int n = 1 + rand.nextInt(99);
			// resultado em centésimos = n  →  fmtH(n)
			addParagrafo("Qual é o número decimal equivalente à fração abaixo?");
			addParagrafo("\\(\\dfrac{" + n + "}{100}\\)");
			gerarAltH(n);

			addResolucao("\\(\\dfrac{" + n + "}{100} = " + n + " \\div 100 = " + fmtH(n) + "\\)");
		}
	}
}
