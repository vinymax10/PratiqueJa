package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// r²h é divisível por 3 para todas as triplas abaixo
		// {3,4,5}: 9*4=36   → V=12π
		// {5,12,13}: 25*12=300 → V=100π
		// {6,8,10}: 36*8=288  → V=96π
		// {8,15,17}: 64*15=960 → V=320π
		// {9,12,15}: 81*12=972 → V=324π
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {6, 8, 10}, {8, 15, 17}, {9, 12, 15}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int r = t[0], h = t[1], g = t[2];
		int r2    = r * r;
		int r2h   = r2 * h;
		int vCoef = r2h / 3;

		addParagrafo("Calcule o volume de um cone com raio \\(r = " + r + "\\,\\text{cm}\\) e altura \\(h = " + h + "\\,\\text{cm}\\).");

		String correta = "\\(" + vCoef + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + r2h + "\\pi\\,\\text{cm}^3\\)");             // esqueceu o 1/3
		distratores.add("\\(" + (r * g * h / 3) + "\\pi\\,\\text{cm}^3\\)"); // usou g em vez de r
		distratores.add("\\(" + (r2h / 2) + "\\pi\\,\\text{cm}^3\\)");       // dividiu por 2 em vez de 3
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(V = \\dfrac{\\pi r^2 h}{3} = \\dfrac{\\pi \\cdot " + r + "^2 \\cdot " + h
				+ "}{3} = \\dfrac{" + r2 + " \\cdot " + h + " \\cdot \\pi}{3} = \\dfrac{"
				+ r2h + "\\pi}{3} = \\mathbf{" + vCoef + "\\pi}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
