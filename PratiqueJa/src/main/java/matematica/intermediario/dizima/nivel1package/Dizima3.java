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
		// Gera period/denomBase → mostra a fração simplificada e pede a dízima
		boolean doisDigitos = rand.nextBoolean();
		int period;
		int denomBase;
		if(doisDigitos)
		{
			do
				period = 10 + rand.nextInt(89);  // 10..98 (evita múltiplos de 11: dízima de 1 dígito)
			while(period % 11 == 0);
			denomBase = 99;
		}
		else
		{
			period = 1 + rand.nextInt(8);  // 1..8 (period=9 daria inteiro)
			denomBase = 9;
		}
		Racional frac = new Racional(period, denomBase);
		frac.fatoracao(2);

		addParagrafo("Escreva a fração como dízima periódica:");
		addParagrafo("\\(" + frac.showDfrac() + "\\)");

		String correta = "\\(0{,}\\overline{" + period + "}\\)";
		int minPeriod = doisDigitos ? 10 : 1;
		int maxPeriod = doisDigitos ? 98 : 8;
		List<String> distratores = new ArrayList<>();
		for(int delta : new int[]{1, -1, 2, -2, 3, -3})
		{
			int alt = period + delta;
			if(alt >= minPeriod && alt <= maxPeriod && !distratores.contains("\\(0{,}\\overline{" + alt + "}\\)"))
				distratores.add("\\(0{,}\\overline{" + alt + "}\\)");
			if(distratores.size() >= 3) break;
		}
		// fallback: usar period + (maxPeriod - minPeriod + 1) se necessário
		while(distratores.size() < 3)
			distratores.add("\\(0{,}\\overline{" + (period + maxPeriod - minPeriod + 1) + "}\\)");

		embaralharEAdicionarAlternativas(correta, distratores.subList(0, 3));

		// Resolução
		long k = denomBase / frac.denominador;  // fator de amplificação
		if(k == 1)
		{
			addResolucao("O denominador já é \\(" + denomBase + "\\), portanto o numerador é diretamente o período.");
			addResolucao("\\(" + frac.showDfrac() + " = \\mathbf{0{,}\\overline{" + period + "}}\\)");
		}
		else
		{
			addResolucao("Amplificamos a fração para obter denominador \\(" + denomBase + "\\):");
			addResolucao("\\(" + frac.showDfrac() + " = \\dfrac{" + frac.numerador + " \\times " + k
				 + "}{" + frac.denominador + " \\times " + k + "} = \\dfrac{" + period
				 + "}{" + denomBase + "}\\).");
			addResolucao("O numerador \\(" + period + "\\) é o período da dízima:");
			addResolucao("\\(\\dfrac{" + period + "}{" + denomBase + "} = \\mathbf{0{,}\\overline{" + period + "}}\\)");
		}
	}
}
