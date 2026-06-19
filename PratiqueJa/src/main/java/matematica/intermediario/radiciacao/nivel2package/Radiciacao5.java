package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdf.util.Convert;

public class Radiciacao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int x = 2 + rand.nextInt(10);

		int a = b * x * x;

		String texto = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} }" + "=";

		String resolucao = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} }" + "=";
		resolucao += " \\sqrt{ \\dfrac{ " + a + "}{" + b + "} }" + "=";
		resolucao += ResolucaoRadiciacao.resolucao((x * x), 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + x);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
