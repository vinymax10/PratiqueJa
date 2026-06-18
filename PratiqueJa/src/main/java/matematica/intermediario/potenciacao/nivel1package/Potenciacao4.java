package matematica.intermediario.potenciacao.nivel1package;

import matematica.GeradorExercicio;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);

		int potenciaMaxima = 7;
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(a)), potenciaMaxima);
		int p = rand.nextInt(maxBase + 1);
		if(p == 0 || p == 1)
			p = rand.nextInt(maxBase + 1);

		String texto = "" + a + "^{" + p + "}" + "=";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + Integer.valueOf((int) Math.pow(a, p)));
		setResolucao("\\(" + ResolucaoPotencia.resolucao(a, p) + "\\)");
	}
}
