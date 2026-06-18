package matematica.avancado.inequacoessegundograu.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.inequacoessegundograu.config.ConfigInequacao;

// Graph: parabola opening UP (a > 0).  Asks where f(x) < 0  →  between the roots.
public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(5);
		int r2 = r1 + 1 + rand.nextInt(5);
		int a = 1 + rand.nextInt(3);
		int b = -a * (r1 + r2);
		int c = a * r1 * r2;

		boolean strict = rand.nextBoolean();
		String sinal = strict ? "<" : "\\leq";
		String abre = strict ? "(" : "[";
		String fecha = strict ? ")" : "]";

		String funcao = "f(x)=" + Auxiliar.getNumber(a, "x^2", true)
				+ Auxiliar.getNumber(b, "x", false)
				+ Auxiliar.getNumber(c, "", false);

		String correta = "\\(x \\in " + abre + r1 + ",\\," + r2 + fecha + "\\)";
		String d1 = "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
		String d2 = strict
				? "\\(x \\in [" + r1 + ",\\," + r2 + "]\\)"
				: "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
		String d3 = "\\(x \\in (" + r1 + ",\\," + (r2 + 1) + ")\\)";

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		ConfigInequacao config = new ConfigInequacao(a, b, c, r1, r2);
		config.indice = 1 + rand.nextInt(10);
		BufferedImage image = config.criarImagem();

		String res = "Do gráfico, as raízes são \\(x_1=" + r1 + "\\) e \\(x_2=" + r2 + "\\)" + "\\(\\\\\\)";
		res += "A parábola abre para cima (\\(a=" + a + ">0\\)): \\(f(x)" + sinal + "0\\) entre as raízes" + (strict ? "." : " (incluindo os zeros).") + " \\(\\\\\\)";
		res += "\\(\\mathbf{x \\in " + abre + r1 + ",\\," + r2 + fecha + "}\\)";

		addParagrafo("Com base no gráfico, determine o conjunto solução de \\(f(x)" + sinal + "0\\)");
		addParagrafo("\\(" + funcao + "\\)");
		addParagrafoImagem(image);
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
