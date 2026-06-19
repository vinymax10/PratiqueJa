package matematica.intermediario.radiciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdf.util.Convert;

public class Radiciacao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 1 + rand.nextInt(10);
		int n = 2 + rand.nextInt(2);
		int d = 1 + rand.nextInt(4);
		int c = n * 2;
		int a = (int) Math.pow(d, c) * b;

		String texto = " \\sqrt[" + n + "]{ \\sqrt{" + a + "}} \\div \\sqrt[" + c + "]{" + b + "} " + "=";

		String resolucao = " \\sqrt[" + n + "]{ \\sqrt{" + a + "}} \\div \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucao += " \\sqrt[" + c + "]{ " + a + " } \\div \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucao += " \\sqrt[" + c + "]{ " + a + " \\div " + b + "} =";
		resolucao += ResolucaoRadiciacao.resolucao((int) Math.pow(d, c), c);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + d);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
