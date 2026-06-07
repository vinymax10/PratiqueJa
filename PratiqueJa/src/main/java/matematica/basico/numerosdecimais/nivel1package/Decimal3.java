package matematica.basico.numerosdecimais.nivel1package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,bc + d,ef — adição com duas casas decimais
public class Decimal3 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int aH = 111 + rand.nextInt(289);
		int bH = 111 + rand.nextInt(289);
		int sumH = aH + bH;

		String a = fmtH(aH);
		String b = fmtH(bH);
		String result = fmtH(sumH);

		addParagrafo("Calcule a soma dos números decimais:");
		addParagrafo("\\(" + a + " + " + b + " = \\,?\\)");
		gerarAltH(sumH);

		setResolucao(vertical("+", a, b, result));
	}
}
