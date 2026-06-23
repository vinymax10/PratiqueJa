package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.nivel3package.ConfigTriangulo4;

public class Image24 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 25 + rand.nextInt(55); // ângulo da base: 25..79
		int b = 180 - 2 * a;          // ângulo do vértice: 22..130

		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.a.mostrar();
		config.c.mostrar();
		config.b.mostrar();
		config.b.nome = "x";

		BufferedImage image = config.criarImagem();

		addParagrafo("O triângulo isósceles tem os dois lados iguais marcados. Encontre \\(x\\):");
		addParagrafoImagem(image);

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + b + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int errado = 180 - a;
		String alt = "\\(" + errado + "^\\circ\\)";
		if (errado != b && errado > 0 && errado < 180 && usados.add(alt)) distratores.add(alt);

		int[] deltas = { 10, -10, 5, -5, 15, -15, 20, -20 };
		for (int d : deltas)
		{
			if (distratores.size() >= 3) break;
			int cand = b + d;
			if (cand > 0 && cand < 180)
			{
				String s = "\\(" + cand + "^\\circ\\)";
				if (usados.add(s)) distratores.add(s);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + b + "^\\circ\\)", distratores);

		addResolucao("No triângulo isósceles, os dois ângulos da base são iguais:");
		addResolucao("\\(" + a + "^\\circ + " + a + "^\\circ + x = 180^\\circ\\)");
		addResolucao("\\(x = 180^\\circ - " + a + "^\\circ - " + a + "^\\circ = \\mathbf{" + b + "^\\circ}\\)");
	}
}
