package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Racionais8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Fração mista n r/d  →  imprópria (n*d + r)/d
		int d = 2 + rand.nextInt(7);      // denominador 2..8
		int n = 1 + rand.nextInt(5);      // parte inteira 1..5
		int r = 1 + rand.nextInt(d - 1); // resto 1..(d-1), garante fração própria

		int num = n * d + r;
		Racional resultado = new Racional(num, d);

		addParagrafo("Converta a fração mista em fração imprópria:");
		addParagrafo("\\(" + n + "\\dfrac{" + r + "}{" + d + "}\\)");
		gerarAlternativas(resultado.toString());

		addResolucao("Multiplicamos a parte inteira pelo denominador e somamos o numerador.");
		addResolucao("\\(" + n + "\\dfrac{" + r + "}{" + d + "} = \\dfrac{" + n + " \\times " + d
			 + " + " + r + "}{" + d + "} = \\dfrac{" + (n * d) + " + " + r + "}{" + d
			 + "} = \\dfrac{" + num + "}{" + d + "}\\)");
	}
}
