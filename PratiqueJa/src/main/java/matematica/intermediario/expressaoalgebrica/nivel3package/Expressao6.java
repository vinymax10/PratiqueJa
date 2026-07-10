package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int p  = 1 + rand.nextInt(5); // 1..5
		int q  = 1 + rand.nextInt(5); // 1..5
		int va = 1 + rand.nextInt(5); // valor de a: 1..5
		int vb = 1 + rand.nextInt(5); // valor de b: 1..5

		boolean comQuadrado = rand.nextBoolean();

		int resultado;
		String exprLatex;
		String resSubst;
		String resCalc;

		String pStr = p == 1 ? "" : p + "";
		String qStr = q == 1 ? "" : q + "";
		if(comQuadrado)
		{
			// E(a, b) = p·a² + q·b
			int va2    = va * va;
			int pva2   = p * va2;
			int qvb    = q * vb;
			resultado  = pva2 + qvb;
			exprLatex  = pStr + "a^2 + " + qStr + "b";
			resSubst   = p + " \\cdot " + va + "^{2} + " + q + " \\cdot " + vb;
			resCalc    = p + " \\cdot " + va2 + " + " + qvb;
		}
		else
		{
			// E(a, b) = p·a + q·b (+ constante opcional)
			int r = -5 + rand.nextInt(11); // -5..5
			int pva = p * va;
			int qvb = q * vb;
			resultado = pva + qvb + r;
			String rPart = r > 0 ? " + " + r : r < 0 ? " - " + Math.abs(r) : "";
			exprLatex  = pStr + "a + " + qStr + "b" + rPart;
			resSubst   = p + " \\cdot " + va + " + " + q + " \\cdot " + vb + rPart;
			resCalc    = pva + " + " + qvb + rPart;
		}

		addParagrafo("Calcule o valor da expressão para \\(a = " + va + "\\) e \\(b = " + vb + "\\):");
		addParagrafo("\\(" + exprLatex + "\\)");
		gerarAlternativasInteiras(resultado);

		addResolucao("Substituindo \\(a = " + va + "\\) e \\(b = " + vb + "\\):");
		addResolucao("\\(" + resSubst + " =\\)");
		addResolucao("\\(" + resCalc + " = \\mathbf{" + resultado + "}\\)");
	}
}
