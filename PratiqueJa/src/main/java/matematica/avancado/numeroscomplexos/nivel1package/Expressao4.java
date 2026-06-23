package matematica.avancado.numeroscomplexos.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 5 + rand.nextInt(20); // 5..24
		int k = n / 4;
		int r = n % 4;

		// r=0→1, r=1→i, r=2→-1, r=3→-i
		String[] values = {"\\(1\\)", "\\(i\\)", "\\(-1\\)", "\\(-i\\)"};
		String correct = values[r];

		addParagrafo("Calcule \\(i^{" + n + "}\\).");

		List<String> dist = new ArrayList<>();
		for (String v : values)
			if (!v.equals(correct)) dist.add(v);
		embaralharEAdicionarAlternativas(correct, dist);

		addResolucao("Como \\(i^4 = 1\\), dividimos o expoente por 4:");
		if (r == 0)
		{
			addResolucao("\\(" + n + " = 4 \\cdot " + k + "\\).");
			addResolucao("\\(i^{" + n + "} = (i^4)^{" + k + "} = 1^{" + k + "} = \\mathbf{1}\\)");
		}
		else
		{
			String iR = (r == 1) ? "i" : (r == 2) ? "-1" : "-i";
			addResolucao("\\(" + n + " = 4 \\cdot " + k + " + " + r + "\\).");
			addResolucao("\\(i^{" + n + "} = (i^4)^{" + k + "} \\cdot i^{" + r + "} = 1 \\cdot i^{" + r + "}\\).");
			addResolucao("\\(i^{" + n + "} = \\mathbf{" + iR + "}\\)");
		}
	}
}
