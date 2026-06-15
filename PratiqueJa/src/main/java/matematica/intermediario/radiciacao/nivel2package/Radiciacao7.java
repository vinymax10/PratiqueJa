package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdfAntigo.util.Convert;

public class Radiciacao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 1 + rand.nextInt(10);
		int x = 1 + rand.nextInt(5);

		while((b * x * x) == b)
			x = 1 + rand.nextInt(5);

		int c = b * x;
		int a = b * x * x;

		String texto = "\\sqrt{" + a + "\\cdot" + b + "}" + "=";

		String resolucao = "\\sqrt{" + a + "\\cdot" + b + "}" + "=";
		resolucao += ResolucaoRadiciacao.resolucao(a * b, 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + c);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
