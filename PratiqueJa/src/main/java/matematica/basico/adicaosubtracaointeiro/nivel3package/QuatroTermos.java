package matematica.basico.adicaosubtracaointeiro.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class QuatroTermos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] vals = new int[4];
		for (int i = 0; i < 4; i++)
		{
			vals[i] = 50 + rand.nextInt(300);
			if (rand.nextBoolean()) vals[i] = -vals[i];
		}
		int resultado = vals[0] + vals[1] + vals[2] + vals[3];

		// displayType: 0=raw, 1=+(+v) ou +(-v), 2=-(−v) ou -(+v)
		int[] dt = new int[4];
		dt[0] = 0;
		for (int i = 1; i < 4; i++) dt[i] = rand.nextInt(3);

		// Ensure at least 2 terms (indices 1-3) are parenthesized
		int parens = 0;
		for (int i = 1; i < 4; i++) if (dt[i] > 0) parens++;
		while (parens < 2)
		{
			int i = 1 + rand.nextInt(3);
			if (dt[i] == 0) { dt[i] = 1 + rand.nextInt(2); parens++; }
		}

		// Build expression display and simplified form
		StringBuilder exprSb = new StringBuilder();
		StringBuilder simplSb = new StringBuilder();
		exprSb.append(Auxiliar.getNumber(vals[0], "", true));
		simplSb.append(Auxiliar.getNumber(vals[0], "", true));

		for (int i = 1; i < 4; i++)
		{
			int v = vals[i];
			simplSb.append(Auxiliar.getNumber(v, "", false));
			if (dt[i] == 0)
				exprSb.append(Auxiliar.getNumber(v, "", false));
			else if (dt[i] == 1)
			{
				if (v > 0) exprSb.append(" + (+" + v + ")");
				else       exprSb.append(" + (-" + Math.abs(v) + ")");
			}
			else
			{
				if (v > 0) exprSb.append(" - (-" + v + ")");
				else       exprSb.append(" - (+" + Math.abs(v) + ")");
			}
		}

		String expr = exprSb.toString();
		String simpl = simplSb.toString();

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(" + expr + "\\)");
		gerarAlternativasInteirasComNegativos(resultado);

		int sumPos = 0, sumNeg = 0;
		StringBuilder posStr = new StringBuilder();
		StringBuilder negStr = new StringBuilder();
		boolean firstNeg = true;
		for (int v : vals)
		{
			if (v > 0) { if (posStr.length() > 0) posStr.append(" + "); posStr.append(v); sumPos += v; }
			else { if (!firstNeg) negStr.append(" - "); else { negStr.append("-"); firstNeg = false; } negStr.append(Math.abs(v)); sumNeg += v; }
		}

		addResolucao("Passo 1 — Eliminamos os parênteses aplicando as regras de sinal:");
		addResolucao("\\(" + expr + " = " + simpl + "\\)");
		addResolucao("Passo 2 — Agrupamos os termos positivos e negativos:");
		if (sumPos > 0) addResolucao("Positivos: \\(+" + posStr + " = +" + sumPos + "\\)");
		if (sumNeg < 0) addResolucao("Negativos: \\(" + negStr + " = " + sumNeg + "\\)");
		if (sumPos > 0 && sumNeg < 0)
			addResolucao("\\(" + sumPos + Auxiliar.getNumber(sumNeg, "", false) + " = \\mathbf{" + resultado + "}\\)");
		else if (sumNeg == 0)
			addResolucao("\\(+" + posStr + " = \\mathbf{" + resultado + "}\\)");
		else
			addResolucao("\\(" + negStr + " = \\mathbf{" + resultado + "}\\)");
	}
}
