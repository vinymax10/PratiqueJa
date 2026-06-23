package matematica.basico.numerosprimos.nivel3package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N? (N com 3 fatores primos distintos — fatorar e aplicar τ)
public class Primo11 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> fatores;
		do
		{
			n = 60 + rand.nextInt(441); // 60..500
			fatores = fatorar(n);
		}
		while(fatores.size() != 3);

		int resultado = numDivisores(fatores);

		StringBuilder formula = new StringBuilder();
		StringBuilder produto = new StringBuilder();
		for(int e : fatores.values())
		{
			if(produto.length() > 0) produto.append(" \\times ");
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
