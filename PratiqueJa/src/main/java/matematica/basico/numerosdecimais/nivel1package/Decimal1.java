package matematica.basico.numerosdecimais.nivel1package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b + c,d — adição com uma casa decimal
public class Decimal1 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int aT = 11 + rand.nextInt(39);
		int bT = 11 + rand.nextInt(39);
		int sumT = aT + bT;

		String a = fmtT(aT);
		String b = fmtT(bT);
		String result = fmtT(sumT);

		addParagrafo("Calcule a soma dos números decimais:");
		addParagrafo("\\(" + a + " + " + b + " = \\,?\\)");
		gerarAltT(sumT);

		addResolucao(vertical("+", a, b, result));
	}
}
