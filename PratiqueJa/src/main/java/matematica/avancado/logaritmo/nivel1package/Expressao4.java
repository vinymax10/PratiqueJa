package matematica.avancado.logaritmo.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
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

		// Quando b==a: d1=b==correta; usar b+1 para evitar colisão (casos: a=3,b=3 e a=5,b=5)
		int d1 = (b == a) ? b + 1 : b;
		List<String> dist = new ArrayList<>();
		dist.add("" + d1);
		dist.add("" + (a * b));
		dist.add("1");

		embaralharEAdicionarAlternativas("" + a, dist);
		addResolucao("Pela propriedade inversa: potenciação e logaritmo de mesma base se cancelam.");
		addResolucao("\\(" + b + "^{\\log_{" + b + "} " + a + "} = \\mathbf{" + a + "}\\)");
	}
}
