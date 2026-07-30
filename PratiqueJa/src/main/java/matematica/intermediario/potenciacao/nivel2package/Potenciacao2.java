package matematica.intermediario.potenciacao.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.potenciacao.ResolucaoPotencia;

public class Potenciacao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int potenciaMaxima = 7;
		int a = -1 - rand.nextInt(10);

		int maxBase = Math.max(2, (int) Math.min((Math.log(1000) / Math.log(Math.abs(a))), potenciaMaxima));
		int p = 2 + rand.nextInt(maxBase - 1); // 2..maxBase (evita expoente 0 e 1)

		String texto = "" + a + "^{" + p + "}" + "=";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + (int) -Math.pow(Math.abs(a), p));
		addResolucao("\\(" + ResolucaoPotencia.resolucaoNegativo(a, p) + "\\)");
	}
}
