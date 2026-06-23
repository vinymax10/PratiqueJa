package matematica.intermediario.radiciacao.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.radiciacao.FatoresPrimos;
import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import pdf.util.Convert;

public class Radiciacao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 2 + rand.nextInt(3);
		int y = 2 + rand.nextInt(9);
		int z = 2 + rand.nextInt(9);

		int b = (int) Math.pow(z, x);
		int n = 2;
		int c = n * x;
		int d = z * y;
		int a = y * y * z;

		String texto = " \\sqrt{" + a + "} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";

		String resolucao = "\\sqrt{" + a + "} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucao += "\\sqrt[" + c + "]{" + a + "^" + x + "} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucao += "\\sqrt[" + c + "]{" + a + "^" + x + "\\cdot " + b + "}=";
		FatoresPrimos fatoresPrimosA = ResolucaoRadiciacao.fatoresPrimos(a);
		resolucao += "\\sqrt[" + c + "]{(" + fatoresPrimosA.latex() + ")^" + x + "\\cdot " + b + "}=";

		fatoresPrimosA.multiplicarPotencias(x);
		resolucao += "\\sqrt[" + c + "]{" + fatoresPrimosA.latex() + "\\cdot " + b + "}=";

		FatoresPrimos fatoresPrimosB = ResolucaoRadiciacao.fatoresPrimos(b);

		resolucao += "\\sqrt[" + c + "]{" + fatoresPrimosA.latex() + "\\cdot " + fatoresPrimosB.latex() + "}=";

		fatoresPrimosA.juntar(fatoresPrimosB);

		resolucao += ResolucaoRadiciacao.resolucao(fatoresPrimosA, c);
		resolucao = Convert.includeLineBreak(resolucao, 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + d);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
