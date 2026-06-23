package matematica.basico.regratres.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

public class RegraTres3 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Em uma turma de $Y alunos, $X tiraram nota máxima. Que porcentagem tirou nota máxima?",
		"Uma loja tem $Y produtos. $X estão em promoção. Que porcentagem dos produtos está em promoção?",
		"Em uma prova com $Y questões, $X foram acertadas. Que porcentagem foi acertada?",
		"Um auditório tem $Y lugares. $X estão ocupados. Que porcentagem das cadeiras está ocupada?",
		"Em uma cidade com $Y habitantes, $X possuem carro. Que porcentagem da população possui carro?",
		"Uma fazenda tem $Y animais. $X são bovinos. Que porcentagem são bovinos?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(10);
		int Y = k * fator;
		int X = k;

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$Y", "" + Y).replace("$X", "" + X);

		addParagrafo(texto);

		List<String> distr = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20, -20, 25, -25, 30};
		for (int d : tentativas)
		{
			if (distr.size() >= 3) break;
			int alt = p + d;
			if (alt > 0 && alt < 100 && usados.add(alt))
				distr.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + p + "\\%\\)", distr);

		addResolucao("Fórmula: \\(\\% = \\dfrac{\\text{parte}}{\\text{total}} \\times 100\\).");
		addResolucao("\\(\\% = \\dfrac{" + X + "}{" + Y + "} \\times 100 =\\)");
		addResolucao("\\(\\dfrac{" + (X * 100) + "}{" + Y + "} = \\mathbf{" + p + "\\%}\\)");
	}
}
