package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

// p·a² + q·b² — dois termos quadráticos em variáveis distintas
public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int p  = 1 + rand.nextInt(5); // 1..5
		int q  = 1 + rand.nextInt(5); // 1..5
		int va = 2 + rand.nextInt(5); // 2..6
		int vb = 2 + rand.nextInt(5); // 2..6

		int va2      = va * va;
		int vb2      = vb * vb;
		int pva2     = p * va2;
		int qvb2     = q * vb2;
		int resultado = pva2 + qvb2;

		String pStr = p == 1 ? "" : p + "";
		String qStr = q == 1 ? "" : q + "";
		String exprLatex = pStr + "a^2 + " + qStr + "b^2";

		addParagrafo("Calcule o valor da expressão para \\(a = " + va + "\\) e \\(b = " + vb + "\\):");
		addParagrafo("\\(" + exprLatex + "\\)");
		gerarAlternativasInteiras(resultado);

		String resSubst = p + " \\cdot " + va + "^{2} + " + q + " \\cdot " + vb + "^{2}";
		String resCalc1 = p + " \\cdot " + va2 + " + " + q + " \\cdot " + vb2;
		String resCalc2 = pva2 + " + " + qvb2;

		addResolucao("Substituindo \\(a = " + va + "\\) e \\(b = " + vb + "\\):");
		addResolucao("\\(" + resSubst + " =\\)");
		addResolucao("\\(" + resCalc1 + " =\\)");
		addResolucao("\\(" + resCalc2 + " = \\mathbf{" + resultado + "}\\)");
	}
}
