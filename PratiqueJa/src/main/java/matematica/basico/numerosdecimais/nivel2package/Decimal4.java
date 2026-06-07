package matematica.basico.numerosdecimais.nivel2package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b + c,d + e,f — soma de três decimais com uma casa
public class Decimal4 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int aT = 11 + rand.nextInt(29);
		int bT = 11 + rand.nextInt(29);
		int cT = 11 + rand.nextInt(29);
		int step1T = aT + bT;
		int sumT = step1T + cT;

		String a = fmtT(aT);
		String b = fmtT(bT);
		String c = fmtT(cT);
		String p1 = fmtT(step1T);
		String result = fmtT(sumT);

		addParagrafo("Calcule a soma dos números decimais:");
		addParagrafo("\\(" + a + " + " + b + " + " + c + " = \\,?\\)");
		gerarAltT(sumT);

		setResolucao(vertical3("+", a, b, c, result));
	}
}
