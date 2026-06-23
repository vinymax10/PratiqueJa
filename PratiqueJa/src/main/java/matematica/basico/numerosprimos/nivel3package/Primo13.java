package matematica.basico.numerosprimos.nivel3package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N²? (N composto qualquer — fatorar e dobrar expoentes)
public class Primo13 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> fatores;
		do
		{
			n = 12 + rand.nextInt(89); // 12..100
			fatores = fatorar(n);
		}
		while(fatores.size() < 2);

		int resultado = 1;
		StringBuilder n2Latex = new StringBuilder();
		StringBuilder formula = new StringBuilder();
		StringBuilder produto = new StringBuilder();
		for(Map.Entry<Integer, Integer> e : fatores.entrySet())
		{
			int exp2 = 2 * e.getValue();
			resultado *= (exp2 + 1);
			if(n2Latex.length() > 0) { n2Latex.append(" \\times "); produto.append(" \\times "); }
			n2Latex.append(e.getKey()).append("^{").append(exp2).append("}");
			formula.append("(").append(exp2).append("{+}1)");
			produto.append(exp2 + 1);
		}

		addParagrafo("Sendo \\(" + n + " = " + fatorLatex(fatores) + "\\), quantos divisores tem \\(" + n + "^2\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& " + n + "^2 = " + n2Latex + "\\\\" +
			"& \\tau = " + formula + "\\\\" +
			"& = " + produto + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
