package matematica.intermediario.potenciacao.nivel2package;

import matematica.Calc;
import matematica.GeradorExercicio;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int potenciaMaxima = 7;

		int a = 1 + rand.nextInt(10);
		if(rand.nextBoolean())
			a *= -1;

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(Math.abs(a))), potenciaMaxima);
		int p = 1 + rand.nextInt(maxBase);
		if(p == 1)
			p = 1 + rand.nextInt(maxBase);

		String texto = "" + a + "^{-" + p + "}=";

		String resultadoCorreto;
		String resolucao;
		if(a < 0)
		{
			resultadoCorreto = "" + Calc.fatoracao(1, (int) -Math.pow(Math.abs(a), p), 2);
			resolucao = ResolucaoPotencia.resolucaoPotenciaNegativaBaseNegativa(a, p);
		}
		else
		{
			resultadoCorreto = "" + Calc.fatoracao(1, (int) Math.pow(a, p), 2);
			resolucao = ResolucaoPotencia.resolucaoPotenciaNegativa(a, p);
		}

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
