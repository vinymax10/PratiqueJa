package matematica.basico.multiplicacaonatural.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class DobradoMetade extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 11 + rand.nextInt(44);
		int b = 11 + rand.nextInt(44);
		int produto = a * b;

		int tipo = rand.nextInt(2);
		int novoPrimeiro, novoSegundo, resultado;
		String enunciado, res;

		if (tipo == 0)
		{
			novoPrimeiro = 2 * a;
			novoSegundo = b;
			resultado = 2 * produto;
			enunciado = "Sabendo que \\(" + a + " \\times " + b + " = " + produto
				+ "\\), calcule \\(" + novoPrimeiro + " \\times " + b + "\\).";
			res = "Como \\(" + novoPrimeiro + " = 2 \\times " + a + "\\), temos: \\(\\\\\\)";
			res += "\\(" + novoPrimeiro + " \\times " + b + " = 2 \\times ("
				+ a + " \\times " + b + ") = 2 \\times " + produto + " = \\mathbf{" + resultado + "}\\)";
		}
		else
		{
			novoPrimeiro = a;
			novoSegundo = 2 * b;
			resultado = 2 * produto;
			enunciado = "Sabendo que \\(" + a + " \\times " + b + " = " + produto
				+ "\\), calcule \\(" + a + " \\times " + novoSegundo + "\\).";
			res = "Como \\(" + novoSegundo + " = 2 \\times " + b + "\\), temos: \\(\\\\\\)";
			res += "\\(" + a + " \\times " + novoSegundo + " = 2 \\times ("
				+ a + " \\times " + b + ") = 2 \\times " + produto + " = \\mathbf{" + resultado + "}\\)";
		}

		addParagrafo(enunciado);

		Set<Integer> usados = new HashSet<>();
		usados.add(resultado);
		List<String> distrais = new ArrayList<>();
		for (int w : new int[]{produto, 3 * produto, resultado + produto / 2})
		{
			if (distrais.size() >= 3) break;
			if (w > 0 && usados.add(w)) distrais.add(formatarNumero(w));
		}
		while (distrais.size() < 3)
		{
			int fallback = resultado + (rand.nextBoolean() ? 10 : -10);
			if (fallback > 0 && usados.add(fallback)) distrais.add(formatarNumero(fallback));
		}
		embaralharEAdicionarAlternativas(formatarNumero(resultado), distrais);

		setResolucao(res);
	}
}
