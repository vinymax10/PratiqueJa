package matematica.intermediario.dizima.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Dizima8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// inteira + period/9 = (9*inteira + period)/9, mostrar fração simplificada
		// e pedir a dízima periódica
		int inteira = 1 + rand.nextInt(5);   // 1..5
		int period  = 1 + rand.nextInt(8);   // 1..8

		int num = 9 * inteira + period;
		Racional frac = new Racional(num, 9);
		frac.fatoracao(2);

		addParagrafo("Escreva a fração como dízima periódica:");
		addParagrafo("\\(" + frac.showDfrac() + "\\)");

		String correta = "\\(" + inteira + "{,}\\overline{" + period + "}\\)";
		List<String> distratores = new ArrayList<>();
		// Varia o período
		for(int delta : new int[]{1, -1, 2, -2, 3})
		{
			int alt = period + delta;
			if(alt >= 1 && alt <= 8)
				distratores.add("\\(" + inteira + "{,}\\overline{" + alt + "}\\)");
			if(distratores.size() >= 3) break;
		}
		// Varia a parte inteira se necessário
		if(distratores.size() < 3)
			distratores.add("\\(" + (inteira + 1) + "{,}\\overline{" + period + "}\\)");

		embaralharEAdicionarAlternativas(correta, distratores.subList(0, 3));

		// Resolução
		long k = 9 / frac.denominador;  // fator de amplificação (1 ou 3)
		String res;
		if(k == 1)
		{
			res = "Separamos a parte inteira e identificamos o período: \\(\\\\\\)";
			res += "\\(" + frac.showDfrac() + " = " + inteira + " + \\dfrac{" + period
				 + "}{9} = " + inteira + "{,}\\overline{" + period + "}\\)";
		}
		else
		{
			res = "Amplificamos para denominador \\(9\\) e separamos a parte inteira: \\(\\\\\\)";
			res += "\\(" + frac.showDfrac() + " = \\dfrac{" + frac.numerador + " \\times " + k
				 + "}{" + frac.denominador + " \\times " + k + "} = \\dfrac{" + num
				 + "}{9}\\). \\(\\\\\\)";
			res += "\\(\\dfrac{" + num + "}{9} = " + inteira + " + \\dfrac{" + period
				 + "}{9} = " + inteira + "{,}\\overline{" + period + "}\\)";
		}
		setResolucao(res);
	}
}
