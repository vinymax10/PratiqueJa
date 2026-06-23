package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Sinal da tangente dado o quadrante
		// Q1: tg>0; Q2: tg<0; Q3: tg>0; Q4: tg<0
		int q = 1 + rand.nextInt(4);
		String qNome = q + ".º";
		boolean positivo = (q == 1 || q == 3);

		addParagrafo("No " + qNome + " quadrante do ciclo trigonométrico, a tangente é:");

		String correta = positivo ? "positiva" : "negativa";
		List<String> distratores = new ArrayList<>();
		distratores.add(positivo ? "negativa" : "positiva");
		distratores.add("zero");
		distratores.add("não definida");
		embaralharEAdicionarAlternativas(correta, distratores);

		String senCos;
		if (q == 1)      senCos = "No 1.º quadrante, \\(\\operatorname{sen}>0\\) e \\(\\cos>0\\)";
		else if (q == 2) senCos = "No 2.º quadrante, \\(\\operatorname{sen}>0\\) e \\(\\cos<0\\)";
		else if (q == 3) senCos = "No 3.º quadrante, \\(\\operatorname{sen}<0\\) e \\(\\cos<0\\)";
		else             senCos = "No 4.º quadrante, \\(\\operatorname{sen}<0\\) e \\(\\cos>0\\)";

		addResolucao(senCos + ".");
		addResolucao("Como \\(\\operatorname{tg}\\,\\theta = \\dfrac{\\operatorname{sen}\\,\\theta}{\\cos\\theta}\\), a divisão de dois valores "
				+ (positivo ? "de mesmo sinal resulta em valor \\(\\mathbf{positivo}\\)."
				           : "de sinais opostos resulta em valor \\(\\mathbf{negativo}\\)."));
	}
}
