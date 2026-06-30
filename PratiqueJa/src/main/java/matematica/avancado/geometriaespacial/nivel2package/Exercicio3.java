package matematica.avancado.geometriaespacial.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 2 + rand.nextInt(5); // 2..6
		int h    = 2 + rand.nextInt(7); // 2..8
		int coef = 2 * r * h;

		addParagrafo("Calcule a área lateral de um cilindro com raio \\(r = " + r
				+ "\\,\\text{cm}\\) e altura \\(h = " + h + "\\,\\text{cm}\\).");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		int d1 = r * r * h;
		if (d1 == coef) d1 += r;             // r==2: πr²h==2πrh, ajustar
		int d2area = 2 * r * (h + r);
		if (d1 == d2area) d1 += r;           // raro: πr²h==área total (r=3,h=6 etc), ajustar
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\pi\\,\\text{cm}^2\\)");           // πr²h (confundiu com volume)
		distratores.add("\\(" + d2area + "\\pi\\,\\text{cm}^2\\)");       // área total 2πr(h+r)
		distratores.add("\\(" + (r * h) + "\\pi\\,\\text{cm}^2\\)");     // esqueceu o fator 2
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("\\(A_{\\text{lateral}} = 2\\pi r h = 2\\pi \\cdot " + r + " \\cdot " + h
				+ " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)");
	}
}
