package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// {ângulo, tg(ângulo) em LaTeX}
		String[] angles = {"0°",  "30°",                        "45°", "60°"};
		String[] vals   = {"0",   "\\dfrac{\\sqrt{3}}{3}",      "1",   "\\sqrt{3}"};

		int idx = rand.nextInt(angles.length);
		String angle = angles[idx];
		String val   = vals[idx];

		addParagrafo("Qual o valor de \\(\\operatorname{tg}(" + angle + ")\\)?");

		String correta = "\\(" + val + "\\)";
		List<String> distratores = new ArrayList<>();
		for (int i = 0; i < vals.length && distratores.size() < 3; i++) {
			if (i != idx) distratores.add("\\(" + vals[i] + "\\)");
		}
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Pela tabela de valores notáveis:"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{tg}(" + angle + ") = \\mathbf{" + val + "}\\)";
		setResolucao(res);
	}
}
