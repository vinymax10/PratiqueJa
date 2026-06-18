package matematica.basico.numerosprimos.nivel2package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Soma dos fatores primos distintos de N
public class Primo13 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> fatores;
		do { n = 30 + rand.nextInt(150); fatores = fatorar(n); } while(fatores.size() < 2);

		int soma = 0;
		StringBuilder lista = new StringBuilder();
		for(int p : fatores.keySet())
		{
			soma += p;
			if(lista.length() > 0) lista.append(" + ");
			lista.append(p);
		}

		addParagrafo("Qual é a soma dos fatores primos distintos de \\(" + n + "\\)?");
		gerarAlternativasInteiras(soma);

		setResolucao(resolucaoFatoracao(n) + " \\(\\\\\\) Fatores primos distintos somados: \\(" + lista + " = \\mathbf{" + soma + "}\\)");
	}
}
