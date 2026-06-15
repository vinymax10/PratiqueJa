package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdfAntigo.util.Convert;

public class Radiciacao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(20);
		int b = 1 + rand.nextInt((int) Math.pow(a, 2));

		String texto = "\\sqrt{" + ((a * a) - b) + "+" + b + "}" + "=";

		String resolucao = "\\sqrt{" + ((a * a) - b) + "+" + b + "}" + "=";
		resolucao += ResolucaoRadiciacao.resolucao(a * a, 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + a);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
