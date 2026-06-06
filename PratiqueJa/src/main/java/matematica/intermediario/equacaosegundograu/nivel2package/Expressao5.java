package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Expressao5 extends GeradorExercicio
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
		texto += "+bx";
		texto += Auxiliar.getNumber(c, "", false);

		int num = 1 + rand.nextInt(19);

		int resultado = (a * num * num) + (b * num) + c;

		String resolucao = "f(" + num + ")=" + a + " \\cdot " + num + "^2" + "+b \\cdot " + num +
		Auxiliar.getNumber(c, "", false) + "=" + resultado + "\\\\";

		MyExpression expressao = new MyExpression(a + "*" + (num * num) + "+b *" + num +
		Auxiliar.getNumber(c, "", false) + "=" + resultado);
		resolucao += expressao.resolverLatex();

		addParagrafo("Encontre \\( b \\) tendo \\( f(" + num + ")=" + resultado + " \\)");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + b);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
