package matematica.avancado.logaritmo.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// b^(log_b a) = a — propriedade inversa
		int[] bases = {2, 3, 4, 5};
		int b = bases[rand.nextInt(bases.length)];
		// 'a' é um número positivo qualquer (não precisa ser potência de b)
		int[] args = {3, 5, 7, 11, 13, 6, 9, 15};
		int a = args[rand.nextInt(args.length)];

		addParagrafo("Aplique a propriedade \\(b^{\\log_b a} = a\\):");
		addParagrafo("\\(" + b + "^{\\log_{" + b + "} " + a + "} = \\,?\\)");

		List<String> dist = new ArrayList<>();
		dist.add("" + b);
		dist.add("" + (a * b));
		dist.add("1");

		String res = "Pela propriedade inversa: potenciação e logaritmo de mesma base se cancelam. \\(\\\\\\)";
		res += "\\(" + b + "^{\\log_{" + b + "} " + a + "} = \\mathbf{" + a + "}\\)";

		embaralharEAdicionarAlternativas("" + a, dist);
		setResolucao(res);
	}
}
