package matematica.basico.planocartesiano.nivel1package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matematica.GeradorExercicio;

public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px = 1 + rand.nextInt(7);
		int py = 1 + rand.nextInt(7);
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		String quad;
		if (px > 0 && py > 0)      quad = "I";
		else if (px < 0 && py > 0) quad = "II";
		else if (px < 0 && py < 0) quad = "III";
		else                       quad = "IV";

		addParagrafo("Em qual quadrante está o ponto \\( P(" + px + ",\\;" + py + ") \\)?");

		List<String> outros = new ArrayList<>(Arrays.asList("I", "II", "III", "IV"));
		outros.remove(quad);
		embaralharEAdicionarAlternativas(quad, outros);

		String opX = px > 0 ? ">" : "<";
		String opY = py > 0 ? ">" : "<";
		String ladoX = px > 0 ? "positivo" : "negativo";
		String ladoY = py > 0 ? "positivo" : "negativo";

		String res = "Identificamos os sinais das coordenadas: \\(\\\\\\)";
		res += "\\(x = " + px + "\\), abscissa " + ladoX + ", e ";
		res += "\\(y = " + py + "\\), ordenada " + ladoY + ". \\(\\\\\\)";
		res += "Pela definição dos quadrantes: \\(\\\\\\)";
		res += "Q. I \\(x > 0, y > 0\\); \\(\\\\\\)Q. II \\(x < 0, y > 0\\); \\(\\\\\\)";
		res += "Q. III \\(x < 0, y < 0\\); \\(\\\\\\)Q. IV \\(x > 0, y < 0\\). \\(\\\\\\)";
		res += "Como \\(x " + opX + " 0\\) e \\(y " + opY + " 0\\), ";
		res += "o ponto P está no Quadrante " + quad + ".";

		setResolucao(res);
	}
}
