package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdf.util.Convert;

public class Radiciacao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 1 + rand.nextInt(10);
		int x = 2 + rand.nextInt(10);

		int a = b * x * x;
		int c = b * x;

		String texto = "\\sqrt{" + a + "} \\cdot \\sqrt{" + b + "}" + "=";

		String resolucao = "\\sqrt{" + a + "} \\cdot \\sqrt{" + b + "}" + "=";
		resolucao += "\\sqrt{" + a + "\\cdot" + b + "}" + "=";
		resolucao += ResolucaoRadiciacao.resolucao(a * b, 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + c);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
