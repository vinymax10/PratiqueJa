package matematica.basico.adicaosubtracaointeiro.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class TresTermos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 50 + rand.nextInt(450); if (rand.nextBoolean()) a = -a;
			b = 50 + rand.nextInt(450); if (rand.nextBoolean()) b = -b;
			c = 50 + rand.nextInt(450); if (rand.nextBoolean()) c = -c;
		}
		while ((a > 0 && b > 0 && c > 0) || (a < 0 && b < 0 && c < 0));

		int resultado = a + b + c;

		String expr = Auxiliar.getNumber(a, "", true)
				+ Auxiliar.getNumber(b, "", false)
				+ Auxiliar.getNumber(c, "", false);

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + expr + "\\)");
		gerarAlternativasInteirasComNegativos(resultado);

		int sumPos = 0, sumNeg = 0;
		StringBuilder posStr = new StringBuilder();
		StringBuilder negStr = new StringBuilder();
		boolean firstNeg = true;
		for (int t : new int[]{a, b, c})
		{
			if (t > 0) { if (posStr.length() > 0) posStr.append(" + "); posStr.append(t); sumPos += t; }
			else { if (!firstNeg) negStr.append(" - "); else { negStr.append("-"); firstNeg = false; } negStr.append(Math.abs(t)); sumNeg += t; }
		}

		String res = "Agrupamos os termos positivos e negativos separadamente: \\(\\\\\\)";
		if (sumPos > 0) res += "Positivos: \\(+" + posStr + " = +" + sumPos + "\\) \\(\\\\\\)";
		if (sumNeg < 0) res += "Negativos: \\(" + negStr + " = " + sumNeg + "\\) \\(\\\\\\)";
		if (sumPos > 0 && sumNeg < 0)
			res += "\\(" + sumPos + Auxiliar.getNumber(sumNeg, "", false) + " = \\mathbf{" + resultado + "}\\)";
		else if (sumNeg == 0)
			res += "\\(+" + posStr + " = \\mathbf{" + resultado + "}\\)";
		else
			res += "\\(" + negStr + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
