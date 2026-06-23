package matematica.basico.numerosdecimais.nivel3package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// (a,b + c,d) × e — expressão com parênteses e decimal
public class Decimal2 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int aT = 11 + rand.nextInt(29);
		int bT = 11 + rand.nextInt(29);
		int e = 2 + rand.nextInt(5);
		int sumT = aT + bT;
		int resT = sumT * e;

		String a = fmtT(aT);
		String b = fmtT(bT);
		String soma = fmtT(sumT);
		String result = fmtT(resT);

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo(
			"\\(\\left(" + a + " + " + b +
			"\\right) \\times " + e + " = \\,?\\)"
		);
		gerarAltT(resT);

		addResolucao("\\(\\left(" + a + " + " + b + "\\right) \\times " + e + " =\\)");
		addResolucao("\\(" + soma + " \\times " + e + " = " + result + "\\)");
	}
}
