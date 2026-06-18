package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdfAntigo.util.Convert;

public class Radiciacao16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 1 + rand.nextInt(10);
		int c = 2 + rand.nextInt(10);

		int a = b * c * c;

		String texto = " \\sqrt{" + a + "} \\div \\sqrt{" + b + "} " + "=";

		String resolucao = " \\sqrt{" + a + "} \\div \\sqrt{" + b + "} =";
		resolucao += " \\sqrt{" + a + " \\div " + b + "} =";
		resolucao += ResolucaoRadiciacao.resolucao(c * c, 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + c);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
