package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// {grau, displayRad, num, den}  rad = num*π/den
		String[][] data = {
			{"30",  "\\dfrac{\\pi}{6}",   "1", "6"},
			{"45",  "\\dfrac{\\pi}{4}",   "1", "4"},
			{"60",  "\\dfrac{\\pi}{3}",   "1", "3"},
			{"90",  "\\dfrac{\\pi}{2}",   "1", "2"},
			{"120", "\\dfrac{2\\pi}{3}",  "2", "3"},
			{"150", "\\dfrac{5\\pi}{6}",  "5", "6"},
			{"180", "\\pi",               "1", "1"},
			{"270", "\\dfrac{3\\pi}{2}",  "3", "2"},
			{"360", "2\\pi",              "2", "1"},
		};

		int idx = rand.nextInt(data.length);
		String grau = data[idx][0];
		String rad  = data[idx][1];

		addParagrafo("Converta \\(" + grau + "°\\) para radianos.");

		String correta = "\\(" + rad + "\\)";
		List<Integer> others = new ArrayList<>();
		for (int i = 0; i < data.length; i++) if (i != idx) others.add(i);
		for (int i = others.size() - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			int tmp = others.get(i); others.set(i, others.get(j)); others.set(j, tmp);
		}
		List<String> distratores = new ArrayList<>();
		for (int i = 0; i < 3; i++) distratores.add("\\(" + data[others.get(i)][1] + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Usando \\(\\text{rad} = \\theta \\times \\dfrac{\\pi}{180}\\):"
				+ "\\(\\\\\\)"
				+ "\\(" + grau + "° \\times \\dfrac{\\pi}{180} = \\dfrac{" + grau + "\\pi}{180} = \\mathbf{" + rad + "}\\)";
		setResolucao(res);
	}
}
