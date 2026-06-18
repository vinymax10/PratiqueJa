package matematica.avancado.funcaologaritmica.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Função inversa: ponto (c, d) em g(x)=a^x ↔ ponto (d, c) em f(x)=log_a(x)
		int[] bases = {2, 3, 4};
		int a = bases[rand.nextInt(bases.length)];
		int c = 1 + rand.nextInt(3); // expoente 1..3
		int d = (int) Math.pow(a, c); // d = a^c

		addParagrafo("O ponto \\((" + c + ",\\," + d + ")\\) pertence ao gráfico de \\(g(x) = " + a + "^x\\).");
		addParagrafo("Sabendo que \\(f(x) = \\log_{" + a + "}(x)\\) é a função inversa de \\(g\\), "
			+ "qual ponto pertence ao gráfico de \\(f\\)?");

		String correto = "\\((" + d + ",\\," + c + ")\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\((" + c + ",\\," + d + ")\\)");
		dist.add("\\((" + d + ",\\,-" + c + ")\\)");
		dist.add("\\((-" + c + ",\\," + d + ")\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "A função inversa troca domínio e imagem, "
			+ "refletindo o gráfico em torno da reta \\(y = x\\): \\(\\\\\\)";
		res += "Ponto \\((c,\\,d)\\) em \\(g\\) \\(\\Rightarrow\\) ponto \\((d,\\,c)\\) em \\(f\\). \\(\\\\\\)";
		res += "Portanto: \\((" + c + ",\\," + d + ") \\to \\mathbf{(" + d + ",\\," + c + ")}\\)";
		setResolucao(res);
	}
}
