package matematica.basico.numerosdecimais.nivel3package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b - c,de — subtração com precisões diferentes (completar com zero)
public class Decimal4 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// aT em décimos; convertido para aH centésimos (× 10)
		int aT = 31 + rand.nextInt(59);
		int aH = aT * 10;

		// bH em centésimos, menor que aH, com centésimo ≠ 0
		int bH;
		do
		{
			bH = 11 + rand.nextInt(aH - 21);
		}
		while(bH % 10 == 0);

		int diffH = aH - bH;

		String a = fmtT(aT);
		String a0 = fmtH(aH);
		String b = fmtH(bH);
		String result = fmtH(diffH);

		addParagrafo("Calcule a subtração dos números decimais:");
		addParagrafo("\\(" + a + " - " + b + " = \\,?\\)");
		gerarAltH(diffH);

		// mostra a conversão + conta vertical
		addResolucao("\\(" + a + " = " + a0 + "\\)");
		addResolucao(vertical("-", a0, b, result));
	}
}
