package matematica.avancado.funcaologaritmica.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Identificar crescente (a > 1) ou decrescente (0 < a < 1)
		boolean crescente = rand.nextBoolean();

		String funcao;
		String correto;
		String errado;
		String resolucao;

		if (crescente)
		{
			int[] bases = {2, 3, 4, 5, 7, 10};
			int a = bases[rand.nextInt(bases.length)];
			funcao     = "\\log_{" + a + "}(x)";
			correto    = "Crescente";
			errado     = "Decrescente";
			resolucao  = "A base é \\(" + a + " > 1\\), portanto a função é \\(\\mathbf{crescente}\\): "
				+ "quanto maior \\(x\\), maior \\(f(x)\\).";
		}
		else
		{
			int[] dens = {2, 3, 4, 5};
			int den = dens[rand.nextInt(dens.length)];
			funcao    = "\\log_{\\frac{1}{" + den + "}}(x)";
			correto   = "Decrescente";
			errado    = "Crescente";
			resolucao = "A base é \\(\\dfrac{1}{" + den + "} < 1\\), portanto a função é \\(\\mathbf{decrescente}\\): "
				+ "quanto maior \\(x\\), menor \\(f(x)\\).";
		}

		addParagrafo("A função \\(f(x) = " + funcao + "\\) é:");

		List<String> dist = new ArrayList<>();
		dist.add(errado);
		dist.add("Constante");
		dist.add("Nem crescente nem decrescente");
		embaralharEAdicionarAlternativas(correto, dist);
		setResolucao(resolucao);
	}
}
