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
		String d2str = "\\((" + (mx + 1) + ";\\;" + my + ")\\)";
		String d3str = "\\((" + mx + ";\\;" + (my + 1) + ")\\)";
		// d1=(x1;y1) pode colidir com correta (x1=x2,y1=y2), d2 (x1=x2+2,y1=y2)
		// ou d3 (x1=x2,y1=y2-2); usar (mx-1;my-1) nesses casos (sempre único)
		String d1str = "\\((" + x1 + ";\\;" + y1 + ")\\)";
		if (d1str.equals(correta) || d1str.equals(d2str) || d1str.equals(d3str))
			d1str = "\\((" + (mx - 1) + ";\\;" + (my - 1) + ")\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add(d1str);
		distratores.add(d2str);
		distratores.add(d3str);
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("O ponto médio tem coordenadas iguais às médias aritméticas das extremidades:");
		addResolucao("\\(M_x = \\dfrac{x_1 + x_2}{2} = \\dfrac{" + x1 + " + (" + x2 + ")}{2} = \\dfrac{" + (x1 + x2) + "}{2} = " + mx + "\\)");
		addResolucao("\\(M_y = \\dfrac{y_1 + y_2}{2} = \\dfrac{" + y1 + " + (" + y2 + ")}{2} = \\dfrac{" + (y1 + y2) + "}{2} = " + my + "\\)");
		addResolucao("\\(M = \\mathbf{(" + mx + ";\\;" + my + ")}\\)");
	}
}
