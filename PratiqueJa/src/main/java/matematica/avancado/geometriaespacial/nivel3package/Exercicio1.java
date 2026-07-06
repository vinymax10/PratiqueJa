package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 2 + rand.nextInt(7); // 2..8
		int h    = 2 + rand.nextInt(9); // 2..10
		int rph  = r + h;
		int coef = 2 * r * rph;

		addParagrafo("Calcule a área total de um cilindro com raio \\(r = " + r
				+ "\\,\\text{cm}\\) e altura \\(h = " + h + "\\,\\text{cm}\\).");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		int d1lat = 2 * r * h; // área lateral (falta as bases)
		int d2 = r * r * h;
		// Quando r==2: r²h==2rh → d2==d1lat; quando r²h==coef: d2==correta; ajustar d2
		if (d2 == coef || d2 == d1lat) d2 += 2 * r;
		// Após ajuste, d2 pode colidir com d3=2r(rph+1) — casos r=3/h=6,8; r=4/h=4,5; r=5/h=4; r=6/h=3
		int d3val = 2 * r * (rph + 1);
		if (d2 == d3val) d2 += 2 * r;
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1lat + "\\pi\\,\\text{cm}^2\\)");                 // apenas lateral
		distratores.add("\\(" + d2 + "\\pi\\,\\text{cm}^2\\)");                    // ~volume
		distratores.add("\\(" + (2 * r * (rph + 1)) + "\\pi\\,\\text{cm}^2\\)");  // off-by-1 no raio
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("\\(A_{\\text{total}} = 2\\pi r(h + r) = 2\\pi \\cdot " + r + " \\cdot (" + h + " + " + r
				+ ") = 2\\pi \\cdot " + r + " \\cdot " + rph + " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)");
	}
}
