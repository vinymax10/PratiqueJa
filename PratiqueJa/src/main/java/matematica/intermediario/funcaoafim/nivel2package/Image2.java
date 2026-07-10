package matematica.intermediario.funcaoafim.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.funcaoafim.config.ConfigRetaInteiro;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = -3 + rand.nextInt(7);
		int b = -3 + rand.nextInt(7);

		while(a == 0 && b == 0)
		{
			a = -3 + rand.nextInt(7);
			b = -3 + rand.nextInt(7);
		}

		ConfigRetaInteiro config = new ConfigRetaInteiro(a, b);
		BufferedImage image = config.criarImagem();

		int pontoAx = (int) config.pontoAx;
		int pontoAy = (int) config.pontoAy;

		String aDisp = a == 1 ? "" : (a == -1 ? "-" : a + "");

		MyExpression expressao = new MyExpression(pontoAy + " = " + a + "*" + pontoAx + "+b");

		addParagrafo("Encontre o valor de b: \\( f(x)=" + aDisp + "x+b \\)");
		addParagrafoImagem(image);
		gerarAlternativas("" + b);

		addResolucao("O coeficiente linear  \\(b\\) pode ser calculado por:");
		addResolucao("\\(y=" + aDisp + "x + b\\)");
		addResolucao("Considerando o ponto (" + pontoAx + "," + pontoAy + "), temos:");
		addResolucao("\\(" + boldLastResult(expressao.resolverLatex()) + "\\)");
	}
}
