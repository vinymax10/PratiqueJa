package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Comprimento de arco: l = r * theta
		// cenarios: {r_str, theta_latex, l_latex, d1, d2, d3}
		String[][] cenarios = {
			{"3",  "\\dfrac{\\pi}{3}",  "\\pi",              "\\dfrac{\\pi}{3}", "3\\pi",            "2\\pi"},
			{"6",  "\\dfrac{\\pi}{3}",  "2\\pi",             "6\\pi",            "\\pi",             "3\\pi"},
			{"6",  "\\dfrac{\\pi}{2}",  "3\\pi",             "6\\pi",            "2\\pi",            "\\dfrac{\\pi}{2}"},
			{"12", "\\dfrac{\\pi}{3}",  "4\\pi",             "12\\pi",           "2\\pi",            "\\dfrac{\\pi}{3}"},
			{"9",  "\\dfrac{\\pi}{6}",  "\\dfrac{3\\pi}{2}", "9\\pi",            "3\\pi",            "\\dfrac{\\pi}{2}"},
			{"4",  "\\dfrac{3\\pi}{4}", "3\\pi",             "4\\pi",            "\\dfrac{3\\pi}{4}","\\pi"},
		};
		String[] c = cenarios[rand.nextInt(cenarios.length)];

		addParagrafo("Uma circunferência tem raio \\(" + c[0] + "\\) cm. Qual é o comprimento do arco"
				+ " correspondente ao ângulo central \\(" + c[1] + "\\) rad?");

		String correta = "\\(" + c[2] + "\\)" + " cm";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + c[3] + "\\)" + " cm");
		distratores.add("\\(" + c[4] + "\\)" + " cm");
		distratores.add("\\(" + c[5] + "\\)" + " cm");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Usando a fórmula do comprimento de arco \\(l = r \\cdot \\theta\\):");
		addResolucao("\\(l = " + c[0] + " \\cdot " + c[1] + " = \\mathbf{" + c[2] + "}\\) cm");
	}
}
