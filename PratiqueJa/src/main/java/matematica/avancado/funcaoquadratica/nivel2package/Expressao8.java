package matematica.avancado.funcaoquadratica.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = -(2 + rand.nextInt(4));   // -5..-2
		int x2 = 1 + rand.nextInt(4);      // 1..4
		int a  = rand.nextBoolean() ? 1 : -1;
		int b  = -a * (x1 + x2);
		int c  = a * x1 * x2;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		boolean pedirPositivo = rand.nextBoolean();
		String sinal = pedirPositivo ? ">" : "<";

		addParagrafo("Dada \\(f(x) = " + funcao + "\\) com raízes \\(x_1 = " + x1
			+ "\\) e \\(x_2 = " + x2 + "\\), determine os valores de \\(x\\) tais que \\(f(x) "
			+ sinal + " 0\\).");

		// entre as raízes quando: (a>0 e pede <0) ou (a<0 e pede >0)
		boolean entreRaizes = (a > 0 && !pedirPositivo) || (a < 0 && pedirPositivo);

		String correto = entreRaizes
			? "\\(" + x1 + " < x < " + x2 + "\\)"
			: "\\(x < " + x1 + "\\) ou \\(x > " + x2 + "\\)";

		String errado = entreRaizes
			? "\\(x < " + x1 + "\\) ou \\(x > " + x2 + "\\)"
			: "\\(" + x1 + " < x < " + x2 + "\\)";

		String corretaFechado = entreRaizes
			? "\\(" + x1 + " \\leq x \\leq " + x2 + "\\)"
			: "\\(x \\leq " + x1 + "\\) ou \\(x \\geq " + x2 + "\\)";

		String erradaFechado = entreRaizes
			? "\\(x \\leq " + x1 + "\\) ou \\(x \\geq " + x2 + "\\)"
			: "\\(" + x1 + " \\leq x \\leq " + x2 + "\\)";

		List<String> distratores = new ArrayList<>();
		distratores.add(errado);
		distratores.add(corretaFechado);
		distratores.add(erradaFechado);

		embaralharEAdicionarAlternativas(correto, distratores);

		String concav = a > 0 ? "cima (\\(a > 0\\))" : "baixo (\\(a < 0\\))";
		String posicao = pedirPositivo ? "acima" : "abaixo";
		String regiao  = entreRaizes ? "\\(\\mathbf{entre}\\) as raízes" : "\\(\\mathbf{fora}\\) das raízes";

		addResolucao("A parábola abre para " + concav + ".");
		addResolucao("A função fica " + posicao + " do eixo \\(x\\) " + regiao + ":");
		if(entreRaizes)
			addResolucao("\\(\\mathbf{" + x1 + " < x < " + x2 + "}\\)");
		else
			addResolucao("\\(\\mathbf{x < " + x1 + "}\\) ou \\(\\mathbf{x > " + x2 + "}\\)");
	}
}
