package matematica.intermediario.radiciacao.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Radiciacao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int x = 1 + rand.nextInt(10);
		int y = 1 + rand.nextInt(10);

		while(b == 4 || b == 9)
			b = 2 + rand.nextInt(9);

		Racional bR = new Racional(b);

		Racional a = new Racional(x * x, b);
		a.fatoracao(2);

		Racional c = new Racional(y * y, b);
		c.fatoracao(2);

		while(c.igual(bR) || c.igual(a))
		{
			y = 1 + rand.nextInt(10);
			c = new Racional(y * y, b);
		}

		String texto = " \\dfrac{ \\sqrt{" + a.showDfrac() + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c.showDfrac() + "} }" + "=";

		String passo1 = " \\dfrac{ \\sqrt{" + a.showDfrac() + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c.showDfrac() + "} }" +
		"\\cdot \\dfrac{ \\sqrt{" + b + "} + \\sqrt{" + c.showDfrac() + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c.showDfrac() + "} }=";

		String passo2 = " \\dfrac{ \\sqrt{" + a.showDfrac() + "} \\cdot \\sqrt{" + b + "} + \\sqrt{" + a.showDfrac() + "} \\cdot \\sqrt{" + c.showDfrac() + "}}{ (\\sqrt{" + b + "})^2 - (\\sqrt{" + c.showDfrac() + "})^2 }=";

		String passo3 = " \\dfrac{ \\sqrt{" + a.showDfrac() + "\\cdot" + bR.showDfrac() + "} + \\sqrt{" + a.showDfrac() + "\\cdot" + c.showDfrac() + "} }{ " + b + " - " + c.showDfrac() + " }=";

		Racional parcial = a.mult(c);
		parcial.fatoracao(2);
		Racional denominador = bR.minus(c);
		denominador.fatoracao(2);

		passo3 += " \\dfrac{ \\sqrt{" + (x * x) + "} + \\sqrt{" + parcial.showDfrac() + "} }{ " + denominador.showDfrac() + " }=";

		parcial = new Racional(x * y, b);
		parcial.fatoracao(2);

		String passo4 = " \\dfrac{ " + x + " + " + parcial.showDfrac() + " }{ " + denominador.showDfrac() + " }=";

		parcial = new Racional(x).add(parcial);
		parcial.fatoracao(2);

		passo4 += " \\dfrac{ " + parcial.showDfrac() + " }{ " + denominador.showDfrac() + " }=";

		if(parcial.denominador != 1 || denominador.denominador != 1)
		{
			passo4 += parcial.showDfrac() + " \\cdot " + denominador.inverter().showDfrac() + "=";

			parcial = parcial.mult(denominador.inverter());
			passo4 += parcial.showDfrac();

			parcial.fatoracao(2);
			if(parcial.isSimplificou())
				passo4 += "=" + parcial.showDfrac();
		}
		else
		{
			parcial = parcial.mult(denominador.inverter());
			parcial.fatoracao(2);
			passo4 += parcial.showDfrac();
		}

		passo1 = passo1.replace("(", "\\left(").replace(")", "\\right)");
		passo2 = passo2.replace("(", "\\left(").replace(")", "\\right)");
		passo3 = passo3.replace("(", "\\left(").replace(")", "\\right)");
		passo4 = passo4.replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(parcial.toString());
		addResolucao("\\(" + passo1.trim() + "\\)");
		addResolucao("\\(" + passo2.trim() + "\\)");
		addResolucao("\\(" + passo3.trim() + "\\)");
		addResolucao("\\(" + passo4.trim() + "\\)");
	}
}
