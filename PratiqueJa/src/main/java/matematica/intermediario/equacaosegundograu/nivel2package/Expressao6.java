package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

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

		int b = delta - (2 * a * x);
		int c = (-x * delta) + (a * x * x);

		String texto = "f(x)=";
		texto += Auxiliar.getNumber(a, "x^2", true);
		texto += Auxiliar.getNumber(b, "x", false);
		texto += "+c";

		int num = 1 + rand.nextInt(19);

		int resultado = (a * num * num) + (b * num) + c;

		String resolucao = "f(" + num + ")=" + a + " \\cdot " + num + "^2" + Auxiliar.getNumber(b, "", false) + " \\cdot " + num +
		"+c =" + resultado + "\\\\";

		MyExpression expressao = new MyExpression(a + "*" + (num * num) + Auxiliar.getNumber(b, "", false) + "*" + num +
		"+c=" + resultado);
		resolucao += expressao.resolverLatex();

		addParagrafo("Encontre \\( c \\) tendo \\( f(" + num + ")=" + resultado + " \\)");
		addParagrafo("\\(" + texto + "\\)");
		gerarAlternativas("" + c);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
