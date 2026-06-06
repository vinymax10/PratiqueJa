package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean())
			a *= -1;

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		String texto = "f(x)=";
		texto += Auxiliar.getNumber(a, "x^2", true);
		texto += Auxiliar.getNumber(b, "x", false);
		texto += Auxiliar.getNumber(c, "", false);

		int num = 1 + rand.nextInt(19);

		int resultado = (a * num * num) + (b * num) + c;

		addParagrafo("Encontre \\( f(" + num + ") \\)");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + resultado);
		setResolucao("\\(" + ResolucaoEq2Grau.resolucao(a, b, c, num) + "\\)");
	}
}
