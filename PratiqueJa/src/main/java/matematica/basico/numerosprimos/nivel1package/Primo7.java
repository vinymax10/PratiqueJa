package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é o maior número primo menor que N?
public class Primo7 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 10 + rand.nextInt(56); // 10..65
		int anterior = n - 1;
		while(anterior > 1 && !ehPrimo(anterior)) anterior--;

		addParagrafo("Qual é o maior número primo menor que \\(" + n + "\\)?");
		gerarAlternativasInteiras(anterior);

		StringBuilder descartados = new StringBuilder();
		for(int k = n - 1; k > anterior; k--)
		{
			if(descartados.length() > 0) descartados.append(",\\, ");
			descartados.append(k);
		}
		String res = "Testamos os números antes de \\(" + n + "\\) até achar um primo. \\(\\\\\\)";
		if(descartados.length() > 0)
			res += "\\(" + descartados + "\\) são compostos. \\(\\\\\\)";
		res += "O maior primo menor que \\(" + n + "\\) é \\(\\mathbf{" + anterior + "}\\).";
		setResolucao(res);
	}
}
