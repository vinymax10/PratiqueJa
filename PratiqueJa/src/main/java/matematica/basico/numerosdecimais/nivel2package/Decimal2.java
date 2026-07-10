package matematica.basico.numerosdecimais.nivel2package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b × c,d — multiplicação de decimais (1 casa cada, resultado 2 casas)
public class Decimal2 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// aT e bT: valores em décimos (sem vírgula)
		int aT = 11 + rand.nextInt(29);
		int bT = 11 + rand.nextInt(29);
		// produto em centésimos = aT * bT
		int prodH = aT * bT;

		String a = fmtT(aT);
		String b = fmtT(bT);
		String result = fmtH(prodH);

		addParagrafo("Calcule o produto dos números decimais:");
		addParagrafo("\\(" + a + " \\times " + b + " = \\,?\\)");
		gerarAltH(prodH);

		addResolucao("Multiplicamos como se fossem inteiros (ignorando a vírgula):");
		addResolucao("\\(" + aT + " \\times " + bT + " = " + prodH + "\\)");
		addResolucao("Cada fator tem 1 casa decimal, então o resultado tem 2 casas: \\(\\mathbf{" + result + "}\\)");
	}
}
