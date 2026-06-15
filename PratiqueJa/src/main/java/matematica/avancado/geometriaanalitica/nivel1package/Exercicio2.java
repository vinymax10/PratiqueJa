package matematica.avancado.geometriaanalitica.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = -8 + 2 * rand.nextInt(9);
		int y1 = -8 + 2 * rand.nextInt(9);
		int x2 = -8 + 2 * rand.nextInt(9);
		int y2 = -8 + 2 * rand.nextInt(9);
		int mx = (x1 + x2) / 2;
		int my = (y1 + y2) / 2;

		addParagrafo("Calcule as coordenadas do ponto médio \\(M\\) do segmento \\(AB\\), "
				+ "onde \\(A(" + x1 + ";\\;" + y1 + ")\\) e \\(B(" + x2 + ";\\;" + y2 + ")\\).");

		String correta = "\\((" + mx + ";\\;" + my + ")\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\((" + x1 + ";\\;" + y1 + ")\\)");
		distratores.add("\\((" + (mx + 1) + ";\\;" + my + ")\\)");
		distratores.add("\\((" + mx + ";\\;" + (my + 1) + ")\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "O ponto médio tem coordenadas iguais às médias aritméticas das extremidades:"
				+ "\\(\\\\\\)"
				+ "\\(M_x = \\dfrac{x_1 + x_2}{2} = \\dfrac{" + x1 + " + (" + x2 + ")}{2} = \\dfrac{" + (x1 + x2) + "}{2} = " + mx + "\\\\"
				+ "M_y = \\dfrac{y_1 + y_2}{2} = \\dfrac{" + y1 + " + (" + y2 + ")}{2} = \\dfrac{" + (y1 + y2) + "}{2} = " + my + "\\\\"
				+ "M = \\mathbf{(" + mx + ";\\;" + my + ")}\\)";
		setResolucao(res);
	}
}
