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
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		int pontoAx = (int) config.pontoAx;
		int pontoAy = (int) config.pontoAy;

		String resolucao = "";
		resolucao += "\\text{O coeficiente linear } b \\text{ pode ser calculado por: }\\\\";
		resolucao += "y=" + a + "x + b\\\\";
		resolucao += "\\text{Considerando o ponto } (" + pontoAx + "," + pontoAy + "), \\text{ temos: }\\\\";

		MyExpression expressao = new MyExpression(pontoAy + " = " + a + "*" + pontoAx + "+b");
		resolucao += expressao.resolverLatex();

		addParagrafo("Encontre o valor de b: \\( f(x)=" + a + "x+b \\)");
		addParagrafoImagem(image);
		gerarAlternativas("" + b);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
