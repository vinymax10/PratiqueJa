package matematica.intermediario.radiciacao.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdfAntigo.util.Convert;

public class Radiciacao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(20);

		String texto = "\\sqrt{" + (int) (Math.pow(a, 2)) + "}" + "=";

		String resolucao = ResolucaoRadiciacao.resolucao(a * a, 2);
		resolucao = Convert.includeLineBreak(resolucao, 180);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + a);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
