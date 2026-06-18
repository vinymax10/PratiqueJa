package matematica.intermediario.potenciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int potenciaMaxima = 7;
		int a = -1 - rand.nextInt(10);

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(Math.abs(a))), potenciaMaxima);
		int p = rand.nextInt(maxBase + 1);
		if(p == 0 || p == 1)
			p = rand.nextInt(maxBase + 1);

		String texto = "" + a + "^{" + p + "}" + "=";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + (int) -Math.pow(Math.abs(a), p));
		setResolucao("\\(" + ResolucaoPotencia.resolucaoNegativo(a, p) + "\\)");
	}
}
