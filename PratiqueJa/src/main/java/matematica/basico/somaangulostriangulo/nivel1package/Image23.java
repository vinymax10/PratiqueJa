package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.nivel3package.ConfigTriangulo4;

public class Image23 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + 2 * rand.nextInt(35); // ângulo do vértice, par: 10..78
		int a = (180 - b) / 2;             // ângulo da base: inteiro, 51..85

		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.b.mostrar();
		config.a.mostrar();
		config.a.nome = "x";

		BufferedImage image = config.criarImagem();

		addParagrafo("O triângulo isósceles tem os dois lados iguais marcados. Encontre \\(x\\):");
		addParagrafoImagem(image);

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + a + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int errado = 180 - b;
		String alt = "\\(" + errado + "^\\circ\\)";
		if (errado != a && errado > 0 && errado < 180 && usados.add(alt)) distratores.add(alt);

		int[] deltas = { 10, -10, 5, -5, 15, -15, 20, -20 };
		for (int d : deltas)
		{
			if (distratores.size() >= 3) break;
			int cand = a + d;
			if (cand > 0 && cand < 180)
			{
				String s = "\\(" + cand + "^\\circ\\)";
				if (usados.add(s)) distratores.add(s);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + a + "^\\circ\\)", distratores);

		addResolucao("No triângulo isósceles, os dois ângulos da base são iguais. Pela soma dos ângulos internos:");
		addResolucao("\\(x + x + " + b + "^\\circ = 180^\\circ\\)");
		addResolucao("\\(2x = 180^\\circ - " + b + "^\\circ = " + (180 - b) + "^\\circ\\)");
		addResolucao("\\(x = \\dfrac{" + (180 - b) + "^\\circ}{2} = \\mathbf{" + a + "^\\circ}\\)");
	}
}
