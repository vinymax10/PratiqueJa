package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String var = "" + (char)(97 + rand.nextInt(5)); // a..e

		int a1 = 1 + rand.nextInt(8);  // coef de x em P1 (positivo)
		int b1 = 1 + rand.nextInt(15); // constante em P1
		int a2 = 1 + rand.nextInt(8);  // coef de x em P2 (positivo)
		int b2 = 1 + rand.nextInt(15); // constante em P2
		if(rand.nextBoolean()) b1 = -b1;
		if(rand.nextBoolean()) b2 = -b2;

		int coefXRes = a1 + a2;
		int constRes = b1 + b2;

		String p1 = buildBinomio(a1, b1, var);
		String p2 = buildBinomio(a2, b2, var);

		boolean perguntaCoef = rand.nextBoolean();
		int resposta = perguntaCoef ? coefXRes : constRes;

		if(perguntaCoef)
			addParagrafo("Simplifique a expressão e informe o coeficiente de \\(" + var + "\\) no resultado:");
		else
			addParagrafo("Simplifique a expressão e informe o termo independente no resultado:");
		addParagrafo("\\(\\left(" + p1 + "\\right) + \\left(" + p2 + "\\right)\\)");
		gerarAlternativasInteiras(resposta);

		// passo intermediário: todos os termos expandidos
		String a1Str = a1 == 1 ? "" : a1 + "";
		String a2Str = a2 == 1 ? "" : a2 + "";
		String inter = a1Str + var + " + " + a2Str + var;
		if(b1 > 0) inter += " + " + b1;
		else if(b1 < 0) inter += " - " + Math.abs(b1);
		if(b2 > 0) inter += " + " + b2;
		else if(b2 < 0) inter += " - " + Math.abs(b2);

		String simplif = buildBinomio(coefXRes, constRes, var);

		addResolucao("Removemos os parênteses e reunimos os termos semelhantes:");
		addResolucao("\\(" + inter + " =\\)");
		addResolucao("\\(" + simplif + "\\)");
		if(perguntaCoef)
			addResolucao("O coeficiente de \\(" + var + "\\) é \\(" + coefXRes + "\\).");
		else
			addResolucao("O termo independente é \\(" + constRes + "\\).");
	}

	private String buildBinomio(int coefVar, int cte, String var)
	{
		StringBuilder sb = new StringBuilder();
		if(coefVar != 0)
		{
			if(coefVar == 1)       sb.append(var);
			else if(coefVar == -1) sb.append("-").append(var);
			else                   sb.append(coefVar).append(var);
		}
		if(cte != 0)
		{
			if(sb.length() > 0)
				sb.append(cte > 0 ? " + " + cte : " - " + Math.abs(cte));
			else
				sb.append(cte);
		}
		return sb.length() > 0 ? sb.toString() : "0";
	}
}
