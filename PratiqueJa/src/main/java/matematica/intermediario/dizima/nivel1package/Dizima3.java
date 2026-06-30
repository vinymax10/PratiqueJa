package matematica.intermediario.dizima.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Dizima3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera period/9 → mostra a fração simplificada e pede a dízima
		int period = 1 + rand.nextInt(8);  // 1..8 (period=9 daria inteiro)
		Racional frac = new Racional(period, 9);
		frac.fatoracao(2);

		addParagrafo("Escreva a fração como dízima periódica:");
		addParagrafo("\\(" + frac.showDfrac() + "\\)");

		String correta = "\\(0{,}\\overline{" + period + "}\\)";
		List<String> distratores = new ArrayList<>();
		for(int delta : new int[]{1, -1, 2, -2, 3, -3})
		{
			int alt = period + delta;
			if(alt >= 1 && alt <= 8 && !distratores.contains("\\(0{,}\\overline{" + alt + "}\\)"))
				distratores.add("\\(0{,}\\overline{" + alt + "}\\)");
			if(distratores.size() >= 3) break;
		}
		// fallback: usar period + 10 se necessário
		while(distratores.size() < 3)
			distratores.add("\\(0{,}\\overline{" + (period + 10) + "}\\)");

		embaralharEAdicionarAlternativas(correta, distratores.subList(0, 3));

		// Resolução
		long k = 9 / frac.denominador;  // fator de amplificação (1 ou 3)
		if(k == 1)
		{
			addResolucao("O denominador já é \\(9\\), portanto o numerador é diretamente o período.");
			addResolucao("\\(" + frac.showDfrac() + " = \\mathbf{0{,}\\overline{" + period + "}}\\)");
		}
		else
		{
			addResolucao("Amplificamos a fração para obter denominador \\(9\\):");
			addResolucao("\\(" + frac.showDfrac() + " = \\dfrac{" + frac.numerador + " \\times " + k
				 + "}{" + frac.denominador + " \\times " + k + "} = \\dfrac{" + period
				 + "}{9}\\).");
			addResolucao("O numerador \\(" + period + "\\) é o período da dízima:");
			addResolucao("\\(\\dfrac{" + period + "}{9} = \\mathbf{0{,}\\overline{" + period + "}}\\)");
		}
	}
}
