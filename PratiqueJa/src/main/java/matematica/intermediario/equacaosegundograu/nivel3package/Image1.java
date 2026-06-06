package matematica.intermediario.equacaosegundograu.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.equacaosegundograu.ResolucaoEq2Grau;
import matematica.intermediario.equacaosegundograu.config.ConfigEq2Grau;
import matematica.intermediario.equacaosegundograu.config.DadosEq2Grau;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DadosEq2Grau dadosEq2Grau = new DadosEq2Grau();

		String resultadoCorreto = "" + dadosEq2Grau.x1;
		String texto = dadosEq2Grau.toString();
		String pergunta = "Encontre \\(x_1 \\), dado \\( f(x)=" + dadosEq2Grau.a + "x^2" + get(dadosEq2Grau.b) + "x+c \\)";
		String resolucao = ResolucaoEq2Grau.resolucaoX1(dadosEq2Grau.a, dadosEq2Grau.b, dadosEq2Grau.c);

		ConfigEq2Grau config = new ConfigEq2Grau(dadosEq2Grau);
		config.indice = 1 + rand.nextInt(10);
		config.pontoX1.label = "x_1";
		config.pontoX1.mostrar = true;
		config.pontoC.label = "" + dadosEq2Grau.c;
		config.pontoC.mostrar = true;

		BufferedImage image = config.criarImagem();

		addParagrafo(pergunta);
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}

	private String get(int x)
	{
		if(x >= 0)
			return "+" + x;
		else
			return "" + x;
	}
}
