package matematica.avancado.funcaoexponencial.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean crescente = rand.nextBoolean();

		String funcaoStr, baseDescricao;
		if (crescente)
		{
			int a = 2 + rand.nextInt(3); // 2, 3, 4
			funcaoStr = "" + a + "^x";
			baseDescricao = "\\(a = " + a + " > 1\\)";
		}
		else
		{
			int den = 2 + rand.nextInt(3); // 1/2, 1/3, 1/4
			funcaoStr = "\\left(\\dfrac{1}{" + den + "}\\right)^x";
			baseDescricao = "\\(a = \\dfrac{1}{" + den + "}\\), onde \\(0 < a < 1\\)";
		}

		addParagrafo("A função \\(f(x) = " + funcaoStr + "\\) é:");

		String correta = crescente ? "Crescente" : "Decrescente";
		List<String> dist = new ArrayList<>();
		dist.add(crescente ? "Decrescente" : "Crescente");
		dist.add("Constante");
		dist.add(crescente ? "Crescente apenas para \\(x > 0\\)" : "Decrescente apenas para \\(x < 0\\)");

		embaralharEAdicionarAlternativas(correta, dist);

		addResolucao("A base da função é " + baseDescricao + ".");
		addResolucao("Regra: se \\(a > 1\\) a função é crescente; se \\(0 < a < 1\\) é decrescente.");
		addResolucao("\\(\\mathbf{" + correta + "}\\)");
	}
}
