package matematica.avancado.logaritmo.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] bases = {2, 3, 4, 5, 7, 10};
		int b = bases[rand.nextInt(bases.length)];

		boolean pedirLogDeUm = rand.nextBoolean();

		List<String> dist = new ArrayList<>();
		String correta, enunciado, res;

		if (pedirLogDeUm)
		{
			// log_b(1) = 0
			correta  = "0";
			enunciado = "\\(\\log_{" + b + "} 1 = \\,?\\)";
			dist.add("1");
			dist.add("" + b);
			dist.add("-1");
			res = "Pela propriedade \\(\\log_b 1 = 0\\) (pois \\(b^0 = 1\\)): \\(\\\\\\)";
			res += "\\(\\log_{" + b + "} 1 = \\mathbf{0}\\)";
		}
		else
		{
			// log_b(b) = 1
			correta  = "1";
			enunciado = "\\(\\log_{" + b + "} " + b + " = \\,?\\)";
			dist.add("0");
			dist.add("" + b);
			dist.add("2");
			res = "Pela propriedade \\(\\log_b b = 1\\) (pois \\(b^1 = b\\)): \\(\\\\\\)";
			res += "\\(\\log_{" + b + "} " + b + " = \\mathbf{1}\\)";
		}

		addParagrafo("Calcule usando logaritmos especiais:");
		addParagrafo(enunciado);
		embaralharEAdicionarAlternativas(correta, dist);
		setResolucao(res);
	}
}
