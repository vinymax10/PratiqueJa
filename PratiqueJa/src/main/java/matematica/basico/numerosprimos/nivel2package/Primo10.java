package matematica.basico.numerosprimos.nivel2package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N? (fatorar e aplicar a fórmula τ)
public class Primo10 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> fatores;
		do
		{
			n = 40 + rand.nextInt(111); // 40..150
			fatores = fatorar(n);
		}
		while(fatores.size() < 2);

		int resultado = numDivisores(fatores);

		StringBuilder formula = new StringBuilder();
		StringBuilder produto = new StringBuilder();
		for(int e : fatores.values())
		{
			if(formula.length() > 0) { formula.append(""); produto.append(" \\times "); }
			formula.append("(" + e + "+1)");
			produto.append(e + 1);
		}

		addParagrafo("Quantos divisores tem o número \\(" + n + "\\)?");
		gerarAlternativasInteiras(resultado);

		for(String passo : resolucaoFatoracao(n))
			addResolucao(passo);
		addResolucao("\\(\\tau(" + n + ") = " + formula + " = " + produto + " = \\mathbf{" + resultado + "}\\)");
	}
}
