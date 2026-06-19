package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdf.util.Convert;

public class Radiciacao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(20);
		int b = 1 + rand.nextInt(10);

		String texto = "\\sqrt{" + (int) (a * a * b) + "\\div" + b + "}" + "=";

		String resolucao = "\\sqrt{" + (a * a * b) + "\\div" + b + "}" + "=";
		resolucao += ResolucaoRadiciacao.resolucao(a * a, 2);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + a);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
