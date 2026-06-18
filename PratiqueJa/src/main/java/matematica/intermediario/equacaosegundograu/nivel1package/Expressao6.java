package matematica.intermediario.equacaosegundograu.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean())
			a *= -1;

		int b = -delta - (2 * a * x);
		int c = (+x * delta) + (a * x * x);

		String texto = "";
		texto += Auxiliar.getNumber(a, "x^2", true);
		texto += Auxiliar.getNumber(b, "x", false);
		texto += Auxiliar.getNumber(c, "", false);

		String pergunta;
		if(a < 0)
			pergunta = "Encontre a raiz maior";
		else
			pergunta = "Encontre a raiz menor";

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + x);
		setResolucao("\\(" + ResolucaoEq2Grau.resolucaoX2(a, b, c) + "\\)");
	}
}
