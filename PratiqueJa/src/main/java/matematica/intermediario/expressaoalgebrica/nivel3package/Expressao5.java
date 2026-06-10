package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		String var = "" + (char)(97 + rand.nextInt(5)); // a..e

		int a1 = 1 + rand.nextInt(8);  // coef de x em P1 (positivo)
		int b1 = 1 + rand.nextInt(15); // constante em P1 (sempre positiva)
		int a2 = 1 + rand.nextInt(8);  // coef de x em P2 (positivo)
		int b2 = 1 + rand.nextInt(15); // constante em P2 (sempre positiva → -b2 no resultado)

		// garantir coeficientes de x distintos para resultado não-trivial
		while(a1 == a2)
			a2 = 1 + rand.nextInt(8);

		// (a1·x + b1) − (a2·x + b2) = (a1−a2)·x + (b1−b2)
		int coefXRes = a1 - a2;
		int constRes = b1 - b2;

		String p1 = buildBinomio(a1, b1, var);
		String p2 = buildBinomio(a2, b2, var);

		boolean perguntaConst = rand.nextBoolean();
		int resposta = perguntaConst ? constRes : coefXRes;

		if(perguntaConst)
			addParagrafo("Simplifique a expressão e informe o termo independente no resultado:");
		else
			addParagrafo("Simplifique a expressão e informe o coeficiente de \\(" + var + "\\) no resultado:");
		addParagrafo("\\(\\left(" + p1 + "\\right) - \\left(" + p2 + "\\right)\\)");
		gerarAlternativasInteiras(resposta, 4, false);

		// passo intermediário: distribuir o negativo
		String inter = buildTermo(a1, var) + " + " + b1
				+ " - " + buildTermo(a2, var) + " - " + b2;

		String simplif = buildBinomio(coefXRes, constRes, var);

		String res = "O sinal negativo distribui-se a todos os termos do segundo parêntese: \\(\\\\\\)";
		res += "\\(" + inter + " = \\\\ ";
		res += "" + simplif + "\\\\ \\)";
		if(perguntaConst)
			res += "O termo independente é \\(" + constRes + "\\).";
		else
			res += "O coeficiente de \\(" + var + "\\) é \\(" + coefXRes + "\\).";
		setResolucao(res);
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

	private String buildTermo(int coef, String var)
	{
		if(coef == 1)  return var;
		if(coef == -1) return "-" + var;
		return coef + var;
	}
}
