package matematica.avancado.geometriaespacial.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// r=3 → V=36π  |  r=6 → V=288π  (4r³/3 é inteiro)
		int k     = 1 + rand.nextInt(2);
		int r     = 3 * k;
		int r3    = r * r * r;
		int vCoef = 4 * r3 / 3;

		addParagrafo("Calcule o volume de uma esfera de raio \\(r = " + r + "\\,\\text{cm}\\).");

		String correta = "\\(" + vCoef + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (4 * r * r) + "\\pi\\,\\text{cm}^3\\)"); // 4r² (área confundida com volume)
		distratores.add("\\(" + (vCoef + 12 * k) + "\\pi\\,\\text{cm}^3\\)");
		distratores.add("\\(" + (vCoef - 12 * k) + "\\pi\\,\\text{cm}^3\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(V = \\dfrac{4\\pi r^3}{3} = \\dfrac{4\\pi \\cdot " + r3 + "}{3} = \\dfrac{"
				+ (4 * r3) + "\\pi}{3} = \\mathbf{" + vCoef + "\\pi}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
