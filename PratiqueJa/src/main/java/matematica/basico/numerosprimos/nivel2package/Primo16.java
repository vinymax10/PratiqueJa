package matematica.basico.numerosprimos.nivel2package;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Decomposição em fatores primos de N (faixa 100..200)
public class Primo16 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> fatores;
		do
		{
			n = 100 + rand.nextInt(101); // 100..200
			fatores = fatorar(n);
		}
		while(fatores.size() < 2 || fatores.size() > 3);

		String correta = "\\(" + fatorLatex(fatores) + "\\)";

		List<String> erradas = new ArrayList<>();
		for(int delta = 1; erradas.size() < 3 && delta <= 30; delta++)
			for(int cand : new int[]{n + delta, n - delta})
			{
				if(erradas.size() >= 3) break;
				if(cand >= 4 && !ehPrimo(cand))
				{
					String alt = "\\(" + fatorLatex(fatorar(cand)) + "\\)";
					if(!alt.equals(correta) && !erradas.contains(alt)) erradas.add(alt);
				}
			}
		for(int mul = 2; erradas.size() < 3; mul++)
		{
			String alt = "\\(" + fatorLatex(fatorar(n * mul)) + "\\)";
			if(!erradas.contains(alt)) erradas.add(alt);
		}

		addParagrafo("Qual é a decomposição em fatores primos de \\(" + n + "\\)?");
		embaralharEAdicionarAlternativas(correta, erradas);
		setResolucao(resolucaoFatoracao(n));
	}
}
