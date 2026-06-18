package matematica.basico.regratres.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Que porcentagem X representa de Y (terceira variação)
public class RegraTres18 extends GeradorExercicio
{
	private static final String[] TEXTOS = {
		"Um aluno acertou $X das $Y questões de um simulado. Qual a porcentagem de acertos?",
		"Em uma loja, $X dos $Y clientes pagaram com cartão. Que porcentagem pagou com cartão?",
		"De $Y mudas plantadas, $X brotaram. Que porcentagem das mudas brotou?",
		"Uma pesquisa com $Y eleitores apontou $X favoráveis. Qual a porcentagem de favoráveis?",
		"Em $Y lançamentos, uma moeda caiu $X vezes em cara. Que porcentagem foi cara?",
	};

	@Override
	protected void construir()
	{
		int[] percentuais = {2, 4, 5, 10, 20, 25, 50};
		int p = percentuais[rand.nextInt(percentuais.length)];
		int fator = 100 / p;
		int k = 3 + rand.nextInt(14); // 3..16
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
