package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Triângulo retângulo com catetos r e h girado em torno de h → cone
		// Triplas pitagóricas com r²h divisível por 3
		// {3,4,5}: V=12π  {5,12,13}: V=100π  {6,8,10}: V=96π  {8,15,17}: V=320π
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {6, 8, 10}, {8, 15, 17}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int r = t[0], h = t[1], g = t[2];
		int r2    = r * r;
		int r2h   = r2 * h;
		int vCoef = r2h / 3;

		addParagrafo("Um triângulo retângulo com catetos \\(" + r + "\\,\\text{cm}\\) e \\(" + h
				+ "\\,\\text{cm}\\) gira em torno do cateto de \\(" + h
				+ "\\,\\text{cm}\\). Qual é o volume do sólido gerado?");

		String correta = "\\(" + vCoef + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + r2h + "\\pi\\,\\text{cm}^3\\)");             // esqueceu o 1/3 (volume do cilindro)
		distratores.add("\\(" + (h * h * r / 3) + "\\pi\\,\\text{cm}^3\\)"); // girou em torno do cateto r
		distratores.add("\\(" + (r * g) + "\\pi\\,\\text{cm}^3\\)");          // confusão com área lateral πrg
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "O triângulo gira em torno do cateto de \\(" + h
				+ "\\,\\text{cm}\\), gerando um cone com \\(r = " + r
				+ "\\,\\text{cm}\\) e \\(h = " + h + "\\,\\text{cm}\\):"
				+ "\\(\\\\\\)"
				+ "\\(V = \\dfrac{\\pi r^2 h}{3} = \\dfrac{\\pi \\cdot " + r + "^2 \\cdot " + h
				+ "}{3} = \\dfrac{" + r2h + "\\pi}{3} = \\mathbf{" + vCoef + "\\pi}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
