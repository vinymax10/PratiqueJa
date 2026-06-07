package matematica.basico.numerosdecimais.nivel1package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b - c,d — subtração com uma casa decimal (a > c)
public class Decimal2 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int bT = 11 + rand.nextInt(39);
		int diffT = 1 + rand.nextInt(30);
		int aT = bT + diffT;

		String a = fmtT(aT);
		String b = fmtT(bT);
		String result = fmtT(diffT);

		addParagrafo("Calcule a subtração dos números decimais:");
		addParagrafo("\\(" + a + " - " + b + " = \\,?\\)");
		gerarAltT(diffT);

		setResolucao(vertical("-", a, b, result));
	}
}
