package matematica.basico.regratres.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Conversão de fração para porcentagem
public class RegraTres9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] denominadores = {2, 4, 5, 10, 20, 25, 50};
		int d = denominadores[rand.nextInt(denominadores.length)];
		int n;
		do
			n = 1 + rand.nextInt(2 * d);
		while(n % d == 0); // evita resultados "redondos" de 100% ou 200%

		int p = n * 100 / d;

		addParagrafo("Escreva a fração \\(\\dfrac{" + n + "}{" + d + "}\\) na forma de porcentagem.");

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 20, -20, 25, -25};
		for(int delta : tentativas)
		{
			if(distratores.size() >= 3) break;
			int alt = p + delta;
			if(alt > 0 && usados.add(alt))
				distratores.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + p + "\\%\\)", distratores);

		String res = "Para converter uma fração em porcentagem, multiplicamos por 100: \\(\\\\\\)";
		res += "\\(\\dfrac{" + n + "}{" + d + "} \\times 100 = \\dfrac{" + (n * 100) + "}{" + d + "} = \\mathbf{" + p + "\\%}\\)";
		setResolucao(res);
	}
}
