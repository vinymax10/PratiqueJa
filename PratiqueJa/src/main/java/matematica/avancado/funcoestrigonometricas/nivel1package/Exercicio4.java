package matematica.avancado.funcoestrigonometricas.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// {ângulo, cos(ângulo) em LaTeX}
		String[] angles = {"0°",  "30°",                   "45°",                    "60°",           "90°", "180°"};
		String[] vals   = {"1",   "\\dfrac{\\sqrt{3}}{2}", "\\dfrac{\\sqrt{2}}{2}", "\\dfrac{1}{2}", "0",   "-1"};

		int idx = rand.nextInt(angles.length);
		String angle = angles[idx];
		String val   = vals[idx];

		addParagrafo("Qual o valor de \\(\\cos(" + angle + ")\\)?");

		String correta = "\\(" + val + "\\)";
		List<String> distratores = new ArrayList<>();
		for (int i = 0; i < vals.length && distratores.size() < 3; i++) {
			if (i != idx) distratores.add("\\(" + vals[i] + "\\)");
		}
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Pela tabela de valores notáveis:"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(" + angle + ") = \\mathbf{" + val + "}\\)";
		setResolucao(res);
	}
}
