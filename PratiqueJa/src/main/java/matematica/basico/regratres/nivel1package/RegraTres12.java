package matematica.basico.regratres.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Que porcentagem X representa de Y (segunda variação)
public class RegraTres12 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Em um estacionamento com $Y vagas, $X estão ocupadas. Que porcentagem das vagas está ocupada?",
		"Uma equipe jogou $Y partidas e venceu $X. Qual a porcentagem de vitórias?",
		"De $Y mensagens recebidas, $X eram spam. Que porcentagem era spam?",
		"Uma maratona teve $Y inscritos e $X concluíram a prova. Que porcentagem concluiu?",
		"Em um lote de $Y produtos, $X apresentaram defeito. Que porcentagem teve defeito?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 2 + rand.nextInt(12); // 2..13
		int y = k * fator;
		int x = k;

		String texto = TEXTOS[rand.nextInt(TEXTOS.length)];
		texto = texto.replace("$Y", "" + y).replace("$X", "" + x);
		addParagrafo(texto);

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(p);
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20, -20, 25, -25};
		for(int delta : tentativas)
		{
			if(distratores.size() >= 3) break;
			int alt = p + delta;
			if(alt > 0 && alt < 100 && usados.add(alt))
				distratores.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + p + "\\%\\)", distratores);

		String res = "Fórmula: \\(\\% = \\dfrac{\\text{parte}}{\\text{total}} \\times 100\\). \\(\\\\\\)";
		res += "\\(\\% = \\dfrac{" + x + "}{" + y + "} \\times 100 = \\\\ \\)";
		res += "\\(\\dfrac{" + (x * 100) + "}{" + y + "} = \\mathbf{" + p + "\\%}\\)";
		setResolucao(res);
	}
}
