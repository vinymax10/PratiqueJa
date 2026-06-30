package matematica.avancado.geometriaespacial.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// h múltiplo de 3 garante V_cone inteiro (V = πr²h/3)
		int r     = 2 + rand.nextInt(4); // 2..5
		int k     = 1 + rand.nextInt(4); // 1..4
		int h     = 3 * k;
		int r2    = r * r;
		int vCil  = r2 * h;       // V_cilindro / π
		int vCone = r2 * h / 3;   // V_cone / π

		addParagrafo("Um cone e um cilindro têm a mesma base circular de raio \\(r = " + r
				+ "\\,\\text{cm}\\) e a mesma altura \\(h = " + h
				+ "\\,\\text{cm}\\). Se o volume do cilindro é \\(" + vCil
				+ "\\pi\\,\\text{cm}^3\\), qual é o volume do cone?");

		String correta = "\\(" + vCone + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		// Quando h==6 (k=2): vCil/2 == 3r² == vCone+r² → d2==d3; usar 2*vCone como d3
		distratores.add("\\(" + vCil + "\\pi\\,\\text{cm}^3\\)");          // igual ao cilindro
		distratores.add("\\(" + (vCil / 2) + "\\pi\\,\\text{cm}^3\\)");    // metade (divisão errada)
		distratores.add("\\(" + (2 * vCone) + "\\pi\\,\\text{cm}^3\\)");   // dobro do volume correto
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("O volume do cone é sempre \\(\\dfrac{1}{3}\\) do volume do cilindro de mesma base e altura:");
		addResolucao("\\(V_{\\text{cone}} = \\dfrac{V_{\\text{cilindro}}}{3} = \\dfrac{" + vCil
				+ "\\pi}{3} = \\mathbf{" + vCone + "\\pi}\\,\\text{cm}^3\\)");
	}
}
