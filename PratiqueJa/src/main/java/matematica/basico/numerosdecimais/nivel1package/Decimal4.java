package matematica.basico.numerosdecimais.nivel1package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,bc × 10 ou × 100 — deslocamento da vírgula
public class Decimal4 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// aH centésimos com parte decimal não nula (b≠0, c≠0)
		int a = 1 + rand.nextInt(7);
		int b = 1 + rand.nextInt(9);
		int c = 1 + rand.nextInt(9);
		int aH = a * 100 + b * 10 + c;

		boolean por10 = rand.nextBoolean();
		int fator = por10 ? 10 : 100;
		String strA = fmtH(aH);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + strA + " \\times " + fator + " = \\,?\\)");

		if(por10)
		{
			// a,bc × 10 = ab,c  →  fmtT(aH) pois aH décimos = ab,c
			gerarAltT(aH);
			addResolucao("Multiplicar por 10 desloca a vírgula 1 casa para a direita:");
			addResolucao("\\(" + strA + " \\times 10 = \\mathbf{" + fmtT(aH) + "}\\)");
		}
		else
		{
			// a,bc × 100 = abc (inteiro = aH)
			gerarAlternativasInteiras(aH);
			addResolucao("Multiplicar por 100 desloca a vírgula 2 casas para a direita:");
			addResolucao("\\(" + strA + " \\times 100 = \\mathbf{" + aH + "}\\)");
		}
	}
}
