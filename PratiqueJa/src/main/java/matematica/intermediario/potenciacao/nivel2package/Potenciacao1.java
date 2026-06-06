package matematica.intermediario.potenciacao.nivel2package;

import matematica.Calc;
import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int potenciaMaxima = 7;

		int a = 1 + rand.nextInt(10);
		if(rand.nextBoolean())
			a *= -1;

		int b = 2 + rand.nextInt(9);

		while(a % b == 0)
			b = 2 + rand.nextInt(9);

		if(rand.nextBoolean())
			b *= -1;

		Racional racional = new Racional(a, b);
		racional.fatoracao(2);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log((Math.abs(a) + Math.abs(b) / 2))), potenciaMaxima);

		int p = rand.nextInt(maxBase + 1);
		if(p == 0 || p == 1)
			p = rand.nextInt(maxBase + 1);

		String texto = "\\left(" + racional.showDfrac() + "\\right)^{" + p + "}" + "=";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + Calc.fatoracao((int) Math.pow(a, p), (int) Math.pow(b, p), 2));
		setResolucao("\\(" + ResolucaoPotencia.resolucaoFracao((int) racional.numerador, (int) racional.denominador, p) + "\\)");
	}
}
