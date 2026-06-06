package matematica.intermediario.radiciacao.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Radiciacao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int x = 1 + rand.nextInt(10);
		int y = 1 + rand.nextInt(10);

		while(b == 4 || b == 9)
			b = 2 + rand.nextInt(9);

		int a = b * x * x;
		int c = b * y * y;
		while(c == b || c == a)
		{
			y = 1 + rand.nextInt(10);
			c = b * y * y;
		}

		String texto = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c + "} }" + "=";

		String resolucao = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c + "} }" +
		"\\cdot \\dfrac{ \\sqrt{" + b + "} + \\sqrt{" + c + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c + "} }=\\\\";

		resolucao += " \\dfrac{ \\sqrt{" + a + "} \\cdot \\sqrt{" + b + "} + \\sqrt{" + a + "} \\cdot \\sqrt{" + c + "}}{ (\\sqrt{" + b + "})^2 - (\\sqrt{" + c + "})^2 }=\\\\";

		resolucao += " \\dfrac{ \\sqrt{" + a + " \\cdot " + b + "} + \\sqrt{" + a + " \\cdot" + c + "}}{ " + b + " - " + c + " }=\\\\";

		resolucao += " \\dfrac{ \\sqrt{" + a * b + "} + \\sqrt{" + a * c + "} }{ " + (b - c) + " }=";

		resolucao += " \\dfrac{ " + (x * b) + " + " + (x * b * y) + " }{ " + (b - c) + " }=\\\\";

		Racional resultado = new Racional((x * b) + (x * b * y), b - c);

		resolucao += resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao += "=" + resultado.showDfrac();

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultado.toString());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
