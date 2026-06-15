package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 1 + rand.nextInt(4); // quadrante 1..4
		int ang;
		String intervalo;
		switch (q) {
			case 1: ang = 10 + rand.nextInt(80);  intervalo = "0° e 90°";   break;
			case 2: ang = 91 + rand.nextInt(89);  intervalo = "90° e 180°"; break;
			case 3: ang = 181 + rand.nextInt(89); intervalo = "180° e 270°"; break;
			default: ang = 271 + rand.nextInt(89); intervalo = "270° e 360°"; break;
		}

		addParagrafo("Em qual quadrante do ciclo trigonométrico está o ângulo de \\(" + ang + "°\\)?");

		String[] quadrantes = {"1.º Quadrante", "2.º Quadrante", "3.º Quadrante", "4.º Quadrante"};
		String correta = quadrantes[q - 1];
		List<String> distratores = new ArrayList<>();
		for (int i = 0; i < 4; i++) if (i != q - 1) distratores.add(quadrantes[i]);
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "O ângulo \\(" + ang + "°\\) está entre " + intervalo
				+ ", portanto pertence ao \\(\\mathbf{" + q + ".°}\\) quadrante.";
		setResolucao(res);
	}
}
