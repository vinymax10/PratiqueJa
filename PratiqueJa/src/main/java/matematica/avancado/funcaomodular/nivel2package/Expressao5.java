package matematica.avancado.funcaomodular.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Quantas soluções tem |x + a| = k?
		// k < 0 → 0 soluções | k = 0 → 1 solução | k > 0 → 2 soluções
		int tipo = rand.nextInt(3);
		int a    = 1 + rand.nextInt(6);  // 1..6

		int k;
		String correto;
		List<String> passos = new ArrayList<>();

		if (tipo == 0)
		{
			k       = -(1 + rand.nextInt(5));
			correto = "Nenhuma (\\(S = \\emptyset\\))";
			passos.add("O módulo é sempre \\(\\geq 0\\), portanto \\(|x + " + a + "| \\geq 0\\). "
				+ "Como \\(k = " + k + " < 0\\), a equação não tem solução: \\(\\mathbf{S = \\emptyset}\\).");
		}
		else if (tipo == 1)
		{
			k       = 0;
			int x0  = -a;
			correto = "Uma (\\(x = " + x0 + "\\))";
			passos.add("\\(|x + " + a + "| = 0 \\Leftrightarrow x + " + a + " = 0 \\Rightarrow x = \\mathbf{" + x0 + "}\\). "
				+ "Exatamente \\(\\mathbf{1}\\) solução.");
		}
		else
		{
			k       = 1 + rand.nextInt(8);
			int x1  = k - a;
			int x2  = -k - a;
			correto = "Duas (\\(x = " + x1 + "\\) ou \\(x = " + x2 + "\\))";
			passos.add("\\(k = " + k + " > 0\\): dois casos.");
			passos.add("\\(x + " + a + " = " + k + " \\Rightarrow x = " + x1 + "\\)");
			passos.add("\\(x + " + a + " = -" + k + " \\Rightarrow x = " + x2 + "\\). Total: \\(\\mathbf{2}\\) soluções.");
		}

		addParagrafo("Quantas soluções reais tem a equação \\(|x + " + a + "| = " + k + "\\)?");

		List<String> dist = new ArrayList<>();
		switch (tipo)
		{
			case 0 -> { dist.add("Uma"); dist.add("Duas"); dist.add("Infinitas"); }
			case 1 -> { dist.add("Nenhuma (\\(S = \\emptyset\\))"); dist.add("Duas"); dist.add("Infinitas"); }
			default -> { dist.add("Nenhuma (\\(S = \\emptyset\\))"); dist.add("Uma"); dist.add("Três"); }
		}
		embaralharEAdicionarAlternativas(correto, dist);
		for(String passo : passos)
			addResolucao(passo);
	}
}
