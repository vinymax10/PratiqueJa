package matematica.intermediario.funcaoafim.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.expressao.MyExpression;
import matematica.intermediario.funcaoafim.config.ConfigRetaReal;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int pontoAx = -(2 + rand.nextInt(7));
		int pontoAy = 2 + rand.nextInt(7);
		if(rand.nextBoolean())
			pontoAy *= -1;

		int pontoBx = 2 + rand.nextInt(7);
		int pontoBy = 2 + rand.nextInt(7);
		if(rand.nextBoolean())
			pontoBy *= -1;

		double a = (double) (pontoBy - pontoAy) / (pontoBx - pontoAx);
		double b = pontoAy - (a * pontoAx);

		Racional aRacional = new Racional((pontoBy - pontoAy)).div(new Racional(pontoBx - pontoAx));
		Racional bRacional = new Racional(pontoAy).minus(aRacional.mult(new Racional(pontoAx)));

		bRacional.fatoracao(2);

		ConfigRetaReal config = new ConfigRetaReal(a, b, pontoAx, pontoAy, pontoBx, pontoBy);
		BufferedImage image = config.criarImagem();

		String resolucao = "";
		resolucao += "O coeficiente linear  \\(b\\)  pode ser calculado por: \\(\\\\ \\)";
		resolucao += "\\(y=" + aRacional.toStringLatex() + "x + b \\\\ \\)";
		resolucao += "Considerando o ponto (" + pontoAx + "," + pontoAy + "),  temos: \\(\\\\ \\)";

		MyExpression expressao = new MyExpression(pontoAy + " = " + aRacional.toString() + "*" + pontoAx + "+b");
		resolucao += "\\("+expressao.resolverLatex()+"\\)";

		addParagrafo("Encontre o valor de b:  \\( f(x)=" + aRacional.toStringLatex() + "x+b \\)");
		addParagrafoImagem(image);
		gerarAlternativas("" + bRacional);
		setResolucao(resolucao);
	}
}
