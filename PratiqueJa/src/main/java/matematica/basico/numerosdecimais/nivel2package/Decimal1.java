package matematica.basico.numerosdecimais.nivel2package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,bc - d,ef — subtração com duas casas decimais
public class Decimal1 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int bH = 111 + rand.nextInt(189);
		int diffH = 11 + rand.nextInt(100);
		int aH = bH + diffH;

		String a = fmtH(aH);
		String b = fmtH(bH);
		String result = fmtH(diffH);

		addParagrafo("Calcule a subtração dos números decimais:");
		addParagrafo("\\(" + a + " - " + b + " = \\,?\\)");
		gerarAltH(diffH);

		addResolucao(vertical("-", a, b, result));
	}
}
