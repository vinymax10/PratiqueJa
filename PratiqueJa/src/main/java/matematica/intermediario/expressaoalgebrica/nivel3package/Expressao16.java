package matematica.intermediario.expressaoalgebrica.nivel3package;

import matematica.GeradorExercicio;

// p·a·b + q — produto cruzado de duas variáveis mais constante
public class Expressao16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int p  = 2 + rand.nextInt(4); // 2..5
		int q  = 1 + rand.nextInt(15); // 1..15
		if(rand.nextBoolean()) q = -q;
		int va = 2 + rand.nextInt(5); // 2..6
		int vb = 2 + rand.nextInt(5); // 2..6

		int vavb     = va * vb;
		int pvavb    = p * vavb;
		int resultado = pvavb + q;

		String qPart     = q > 0 ? " + " + q : " - " + Math.abs(q);
		String exprLatex = p + "ab" + qPart;

		addParagrafo("Calcule o valor da expressão para \\(a = " + va + "\\) e \\(b = " + vb + "\\):");
		addParagrafo("\\(" + exprLatex + "\\)");
		gerarAlternativasInteiras(resultado, 4, false);

		String resSubst  = p + " \\cdot " + va + " \\cdot " + vb + qPart;
		String resCalc   = p + " \\cdot " + vavb + qPart;
		String resCalc2  = pvavb + qPart;

		String res = "Substituindo \\(a = " + va + "\\) e \\(b = " + vb + "\\): \\(\\\\\\)";
		res += "\\(" + resSubst + " = \\\\ ";
		res += "" + resCalc + " = \\\\ ";
		res += "" + resCalc2 + " = " + resultado + "\\)";
		setResolucao(res);
	}
}
